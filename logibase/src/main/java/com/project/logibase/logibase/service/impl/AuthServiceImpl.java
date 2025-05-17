package com.project.logibase.logibase.service.impl;

import com.nimbusds.jose.JOSEException;
import com.project.logibase.logibase.constant.ErrorCode;
import com.project.logibase.logibase.constant.Role;
import com.project.logibase.logibase.dto.request.AuthRequest;
import com.project.logibase.logibase.dto.request.IntrospectRequest;
import com.project.logibase.logibase.dto.request.RegisterRequest;
import com.project.logibase.logibase.dto.response.AuthResponse;
import com.project.logibase.logibase.dto.response.IntrospectResponse;
import com.project.logibase.logibase.entity.User;
import com.project.logibase.logibase.entity.UserDetail;
import com.project.logibase.logibase.exception.AppException;
import com.project.logibase.logibase.repository.UserDetailRepository;
import com.project.logibase.logibase.repository.UserRepository;
import com.project.logibase.logibase.service.AuthService;
import com.project.logibase.logibase.util.JWTUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.logibase.logibase.util.PasswordEncoderUtil;

import java.text.ParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final JWTUtil jwtUtil;
    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXIST);
        }

        String encodedPassword = PasswordEncoderUtil.encodePassword(request.getPassword());

        User user = User.builder()
                .username(request.getUsername())
                .passwordHash(encodedPassword)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        // Tạo đối tượng UserDetail và lưu vào database
        UserDetail userDetail = UserDetail.builder()
                .id(user.getId())
                .address(request.getAddress())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .user(user)
                .build();

        userDetailRepository.save(userDetail);

        return "User registered successfully";
    }

    @Override
    public AuthResponse authenticate(AuthRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername());

        if(user == null || !PasswordEncoderUtil.matches(authenticationRequest.getPassword(), user.getPasswordHash())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user))
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        try {
            return IntrospectResponse.builder()
                    .isValid(jwtUtil.verifyJWT(introspectRequest.getToken()))
                    .build();
        }
        catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }
}
