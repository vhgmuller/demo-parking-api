package com.vhgmuller.demoparkingapi.service;

import com.vhgmuller.demoparkingapi.entity.User;
import com.vhgmuller.demoparkingapi.exception.UniqueUsernameViolationException;
import com.vhgmuller.demoparkingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UniqueUsernameViolationException(String.format("Username %s already in use", user.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found.")
        );
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User setPassword(Long id, String oldPassword, String newPassword, String confirmPassword) {
        //validatePassword(newPassword, confirmPassword);
        User user = getById(id);
        user.setPassword(newPassword);
        return user;
    }

    /*private void validatePassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Confirming password doesn't match new password.");
        }

        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);

        assertTrue();

        if (matcher.matches()) {
            throw new RuntimeException("""
                    New password doesn't follow the pattern.
                    Passwords must follow:
                    Have eight characters or more
                    Include a capital letter
                    Use at least one lowercase letter
                    Consists of at least one digit
                    Need to have one special symbol (i.e., @, #, $, %, etc.)
                    Doesn’t contain space, tab, etc.""");
        }
    }*/
}
