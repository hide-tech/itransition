package com.yazukov.proj.mapper;

import com.yazukov.proj.domain.User;
import com.yazukov.proj.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User projectUser);
}
