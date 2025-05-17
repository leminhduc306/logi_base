package com.project.logibase.logibase.service;

import com.project.logibase.logibase.dto.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String register(RegisterRequest request);

}
