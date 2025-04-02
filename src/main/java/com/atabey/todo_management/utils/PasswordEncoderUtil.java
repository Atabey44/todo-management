package com.atabey.todo_management.utils;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Encoded password for 'gazi': " + passwordEncoder.encode("gazi"));
        System.out.println("Encoded password for 'admin': " + passwordEncoder.encode("admin"));
    }

    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
