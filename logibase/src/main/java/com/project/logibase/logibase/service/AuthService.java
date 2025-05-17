package com.project.logibase.logibase.service;

import com.nimbusds.jose.JOSEException;
import com.project.logibase.logibase.dto.request.AuthRequest;
import com.project.logibase.logibase.dto.request.IntrospectRequest;
import com.project.logibase.logibase.dto.request.RegisterRequest;
import com.project.logibase.logibase.dto.response.AuthResponse;
import com.project.logibase.logibase.dto.response.IntrospectResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface AuthService {
    String register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest authenticationRequest);

    IntrospectResponse introspect(IntrospectRequest introspectRequest);

}
