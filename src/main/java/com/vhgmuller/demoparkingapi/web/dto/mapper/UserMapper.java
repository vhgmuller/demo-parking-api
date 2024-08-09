package com.vhgmuller.demoparkingapi.web.dto.mapper;

import com.vhgmuller.demoparkingapi.entity.User;
import com.vhgmuller.demoparkingapi.web.dto.UserCreateDto;
import com.vhgmuller.demoparkingapi.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserMapper {

    public static User toUser(UserCreateDto dto) {
        return new ModelMapper().map(dto, User.class);
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return new ModelMapper().map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toUserResponseDtoList(List<User> users) {
        return users.stream().map(UserMapper::toUserResponseDto).toList();
    }
}
