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
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).orElse(null);
        return convertToDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setId(generateNewId()); // Generar un nuevo ID
        user = userRepository.save(user);
        return convertToDto(user);
    }

    @Override
    public UserDto update(UserDto userDto, long id) {
        User user = convertToEntity(userDto);
        user.setId(id);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        if (user == null) return null;
        return new UserDto(user.getId(), user.getName(), user.getAge(), user.getTell());
    }

    private User convertToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getAge(), userDto.getTell());
    }

    private Long generateNewId() {
        return userRepository.findAll().stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0L) + 1;
    }
}


