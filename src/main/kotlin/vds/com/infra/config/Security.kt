package vds.com.infra.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject
import vds.com.infra.model.UserDTO
import vds.com.infra.service.JwkService

fun Application.configureSecurity(environment: ApplicationEnvironment) {
    val jwkService by inject<JwkService>()
    
    val issuer = environment.config.property("jwt.issuer").getString()
    val jwtRealm = environment.config.property("jwt.realm").getString()

    install(Authentication) {
        jwt {
            verifier(jwkService.withVerifier(issuer))
            realm = jwtRealm
            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                val username = credential.payload.getClaim("username").asString()
                if (email != "" && username != "")
                    UserDTO(username, email)
                else
                    null
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}
