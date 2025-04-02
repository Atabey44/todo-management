package com.atabey.todo_management.service;

import com.atabey.todo_management.dto.LoginDto;
import com.atabey.todo_management.dto.RegisterDto;
import com.atabey.todo_management.exception.TodoAPIException;
import com.atabey.todo_management.model.Role;
import com.atabey.todo_management.model.User;
import com.atabey.todo_management.repository.RoleRepository;
import com.atabey.todo_management.repository.UserRepository;
import com.atabey.todo_management.security.JwtTokenProvider;
import com.atabey.todo_management.utils.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final  UserRepository  userRepository;
    private   final RoleRepository roleRepository;
    protected PasswordEncoderUtil passwordEncoderUtil;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;


    public String register(RegisterDto registerDto){

        if (userRepository.existsByusername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username aldeady exists !");
        }
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, " Email is aldeady exists !");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoderUtil.encode(registerDto.getPassword()));

        Set<Role>  roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return " User Registered Successfully ! ";
    }

    public String login (LoginDto loginDto){

       Authentication authentication=  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               loginDto.getUsernameOrEmail(),
               loginDto.getPassword()
       ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

}
