package com.project.logibase.logibase.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.logibase.logibase.constant.ErrorCode;
import com.project.logibase.logibase.entity.User;
import com.project.logibase.logibase.exception.AppException;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.signerKey}")
    private String signerKey ;

    public String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("logibase")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(60*60, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .claim("role", user.getRole())
                .build();

        com.nimbusds.jose.Payload payload = new com.nimbusds.jose.Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new AppException(ErrorCode.GENERATE_TOKEN_FAIL);
        }
    }

    public boolean verifyJWT(String token)
            throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        return verified && expiryTime.after(new Date());
    }

}
