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
        // Tạo một đối tượng JWSVerifier với thuật toán HMAC SHA-512 để xác minh chữ ký của JWT
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        // Phân tích cú pháp (parse) chuỗi JWT thành đối tượng SignedJWT
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Lấy thời gian hết hạn của JWT từ phần claims (payload)
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        // Xác minh chữ ký của JWT, kiểm tra xem chữ ký có hợp lệ không
        var verified = signedJWT.verify(verifier);
        // Trả về kết quả xác thực:
        return verified && expiryTime.after(new Date());
    }

}
