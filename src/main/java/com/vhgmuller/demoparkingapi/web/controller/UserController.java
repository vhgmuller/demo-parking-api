package com.vhgmuller.demoparkingapi.web.controller;

import com.vhgmuller.demoparkingapi.entity.User;
import com.vhgmuller.demoparkingapi.service.UserService;
import com.vhgmuller.demoparkingapi.web.dto.UserCreateDto;
import com.vhgmuller.demoparkingapi.web.dto.UserPasswordDto;
import com.vhgmuller.demoparkingapi.web.dto.UserResponseDto;
import com.vhgmuller.demoparkingapi.web.dto.mapper.UserMapper;
import com.vhgmuller.demoparkingapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Create, edit, delete and read users information")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;


    @Operation(
            summary = "Creates a new User",
            description = "Creates a new User with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User successfully created.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - The request contains invalid parameters or the input data is incorrectly formatted. This may include type mismatches or missing required fields.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict - The request could not be completed due to a conflict with the current state of the resource. This typically occurs when a unique constraint is violated (e.g., username already in use).",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable Entity - The request was well-formed but contains semantic errors. This may happen when input validation fails (e.g., invalid email format, data inconsistency).",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto dto) {
        User savedUser = userService.save(UserMapper.toUser(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponseDto(savedUser));
    }

    @Operation(
            summary = "Returns an existing User",
            description = "Return the User according to the ID informed.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User successfully found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(UserMapper.toUserResponseDto(user));
    }

    @Operation(
            summary = "Updates an User password",
            description = "Updates the User password, according to the body and ID informed.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Password successfully updated.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "New password not OK.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordDto dto) {
        userService.setPassword(id, dto.getOldPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Returns all existing Users",
            description = "Returns all existing Users.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All existing Users are returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(UserMapper.toUserResponseDtoList(users));
    }
}
