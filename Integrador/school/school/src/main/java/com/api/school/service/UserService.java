package com.api.school.service;

import com.api.school.User;
import com.api.school.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServ {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(String id) {
        User user = userRepository.findById(Long.valueOf(id)).orElse(null);
        return convertToDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setId(generateNewId()); // Generate a new ID
        user = userRepository.save(user);
        return convertToDto(user);
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        User user = convertToEntity(userDto);
        user.setId(id);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(Long.valueOf(id));
    }

    private User convertToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getAge(), userDto.getTell(), userDto.getEmail(), userDto.getPassword(), userDto.getRole());
    }

    private UserDto convertToDto(User user) {
        if (user == null) return null;
        return new UserDto(user.getId(), user.getName(), user.getAge(), user.getTell(), user.getEmail(), user.getPassword(), user.getRole());
    }

    private String generateNewId() {
        return String.valueOf(
                userRepository.findAll().stream()
                        .mapToLong(user -> {
                            try {
                                return Long.parseLong(user.getId());
                            } catch (NumberFormatException e) {
                                return 0L; // Handle or log the exception properly in production
                            }
                        })
                        .max()
                        .orElse(0L) + 1
        );
    }
}


