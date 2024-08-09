package com.vhgmuller.demoparkingapi.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDto {

    private String username;
    private String password;
    private String email;
}
