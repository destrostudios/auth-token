package com.destrostudios.authtoken;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {
    JwtAuthentication convert(String jwt) throws JWTVerificationException;


    static JwtAuthentication convert(DecodedJWT decodedJWT) {
        JwtAuthentication result = new JwtAuthentication();
        result.iat = decodedJWT.getIssuedAt().toInstant();
        result.user = decodedJWT.getClaim("user").as(JwtAuthenticationUser.class);
        result.rawJwt = decodedJWT.getToken();
        return result;
    }
}
