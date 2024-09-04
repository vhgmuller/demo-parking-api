package com.vhgmuller.demoparkingapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDto {

    @NotBlank
    private String username;
    @Size(min = 6, max = 6)
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
}
