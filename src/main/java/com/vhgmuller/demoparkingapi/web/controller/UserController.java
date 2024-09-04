package com.vhgmuller.demoparkingapi.web.controller;

import com.vhgmuller.demoparkingapi.entity.User;
import com.vhgmuller.demoparkingapi.service.UserService;
import com.vhgmuller.demoparkingapi.web.dto.UserCreateDto;
import com.vhgmuller.demoparkingapi.web.dto.UserPasswordDto;
import com.vhgmuller.demoparkingapi.web.dto.UserResponseDto;
import com.vhgmuller.demoparkingapi.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto dto) {
        User savedUser = userService.save(UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponseDto(savedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(UserMapper.toUserResponseDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDto dto) {
        userService.setPassword(id, dto.getOldPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(UserMapper.toUserResponseDtoList(users));
    }
}
