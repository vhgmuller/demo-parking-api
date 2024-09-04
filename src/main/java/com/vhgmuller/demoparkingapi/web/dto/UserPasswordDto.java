package com.vhgmuller.demoparkingapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPasswordDto {

    @Size(min = 6, max = 6)
    @NotBlank
    private String oldPassword;
    @Size(min = 6, max = 6)
    @NotBlank
    private String newPassword;
    @Size(min = 6, max = 6)
    @NotBlank
    private String confirmPassword;
}
