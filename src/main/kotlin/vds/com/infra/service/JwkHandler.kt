package vds.com.infra.service

import com.auth0.jwt.JWTVerifier
import vds.com.infra.model.UserDTO

interface JwkHandler {

    fun generateToken(user: UserDTO, issuer: String): String

    fun withVerifier(issuer: String): JWTVerifier
}