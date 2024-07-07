package com.api.school.service;

import com.api.school.dto.UserDto;
import java.util.List;

public interface UserServ {
    List<UserDto> getAll();
    UserDto getById(long id);
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto, long id);
    void delete(long id);
}
