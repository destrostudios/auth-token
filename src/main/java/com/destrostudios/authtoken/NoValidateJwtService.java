package com.destrostudios.authtoken;

import com.auth0.jwt.JWT;

public class NoValidateJwtService implements JwtService {
    @Override
    public JwtAuthentication decode(String jwt) {
        return JwtService.decode(JWT.decode(jwt));
    }
}
