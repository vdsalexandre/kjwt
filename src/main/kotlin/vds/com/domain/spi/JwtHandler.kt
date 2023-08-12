package vds.com.domain.spi

import com.auth0.jwt.JWTVerifier
import vds.com.domain.model.SimpleUser

interface JwtHandler {

    fun generateToken(user: SimpleUser, issuer: String): String

    fun withVerifier(issuer: String): JWTVerifier
}