package com.destrostudios.authtoken;

import com.auth0.jwt.JWT;

public class NoValidateJwtService implements JwtService {
    @Override
    public JwtAuthentication convert(String jwt) {
        return JwtService.convert(JWT.decode(jwt));
    }
}
