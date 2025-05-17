package com.project.logibase.logibase.service.impl;

import com.project.logibase.logibase.constant.ErrorCode;
import com.project.logibase.logibase.constant.Role;
import com.project.logibase.logibase.dto.request.RegisterRequest;
import com.project.logibase.logibase.entity.User;
import com.project.logibase.logibase.entity.UserDetail;
import com.project.logibase.logibase.exception.AppException;
import com.project.logibase.logibase.repository.UserDetailRepository;
import com.project.logibase.logibase.repository.UserRepository;
import com.project.logibase.logibase.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.project.logibase.logibase.util.PasswordEncoderUtil;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    public String register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
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


}
