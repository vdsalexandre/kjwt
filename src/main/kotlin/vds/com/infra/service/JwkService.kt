package vds.com.infra.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import vds.com.infra.bootstrap.JwtConfig.generateKeyPair
import vds.com.infra.model.UserDTO

class JwkService : JwkHandler {

    override fun generateToken(user: UserDTO, issuer: String): String {
        return JWT
            .create()
            .withSubject("authentication")
            .withIssuer(issuer)
            .withClaim("username", user.username)
            .withClaim("email", user.email)
            .sign(withAlgorithm(generateKeyPair()))
    }

    override fun withVerifier(issuer: String): JWTVerifier {
        return JWT
            .require(withAlgorithm(generateKeyPair()))
            .withSubject("authentication")
            .withIssuer(issuer)
            .withClaimPresence("username")
            .withClaimPresence("email")
            .build()
    }

    private fun withAlgorithm(key: KeyPair) = Algorithm.RSA512(key.public as RSAPublicKey, key.private as RSAPrivateKey)
}