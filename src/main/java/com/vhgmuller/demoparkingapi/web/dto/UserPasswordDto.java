package com.vhgmuller.demoparkingapi.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPasswordDto {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
