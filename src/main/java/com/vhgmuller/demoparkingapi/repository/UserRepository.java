package com.vhgmuller.demoparkingapi.repository;

import com.vhgmuller.demoparkingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
