package com.destrostudios.authtoken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.util.io.pem.PemReader;

public class KeyJwtService implements JwtService {

    private final PublicKey publicKey;

    public KeyJwtService() {
        this(KeyJwtService.class.getClassLoader().getResourceAsStream("public.pem"));
    }

    public KeyJwtService(InputStream in) {
        this(readPublicKey(in));
    }

    public KeyJwtService(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public JwtAuthentication decode(String jwt) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
        return fileVerify(JWT.require(algorithm).build(), jwt);
    }

    private JwtAuthentication fileVerify(JWTVerifier verifier, String jwt) {
        return JwtService.decode(verifier.verify(jwt));
    }

    private static PublicKey readPublicKey(InputStream in) {
        try {
            PemReader pemReader = new PemReader(new InputStreamReader(new BufferedInputStream(in)));
            byte[] publicKeyBytes = pemReader.readPemObject().getContent();
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }
}
