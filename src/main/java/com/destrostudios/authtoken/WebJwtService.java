package com.destrostudios.authtoken;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

public class WebJwtService implements JwtService {

    private final String verificationUrl;

    public WebJwtService() {
        this("https://destrostudios.com:8080/authToken/verify");
    }

    public WebJwtService(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }

    @Override
    public JwtAuthentication convert(String jwt) throws JWTVerificationException {
        JwtAuthentication authentication = webVerify(jwt);
        authentication.rawJwt = jwt;
        return authentication;
    }

    private JwtAuthentication webVerify(String jwt) {
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(verificationUrl).toURL().openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("authToken", jwt);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            try (InputStream in = new BufferedInputStream(con.getInputStream())) {
                ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
                return mapper.readValue(in, JwtAuthentication.class);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
