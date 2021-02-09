package com.destrostudios.authtoken;

import java.time.Instant;

public class JwtAuthentication {
    public JwtAuthenticationUser user;
    public Instant iat;
    public String rawJwt;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JwtAuthentication)) {
            return false;
        }
        JwtAuthentication that = (JwtAuthentication) o;
        return rawJwt.equals(that.rawJwt);
    }

    @Override
    public int hashCode() {
        return 3 + rawJwt.hashCode();
    }

    @Override
    public String toString() {
        return "JwtAuthentication{" +
                "user=" + user +
                ", iat=" + iat +
                ", rawJwt=<redacted>" +
                '}';
    }
}
