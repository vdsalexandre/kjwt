package vds.com.domain.api

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import vds.com.domain.model.LoginUser
import vds.com.infra.model.UserDTO.Companion.toDto
import vds.com.infra.model.userDTO
import vds.com.infra.service.JwkService
import vds.com.infra.service.JwtUserService

fun Application.loginResource(environment: ApplicationEnvironment) {
    val jwkService by inject<JwkService>()
    val jwtUserService by inject<JwtUserService>()

    val issuer = environment.config.property("jwt.issuer").getString()

    routing {
        post("/login") {
            val loginUser = call.receive<LoginUser>()
            val user = jwtUserService.findUser(loginUser.email, loginUser.password)
            if (user != null) {
                val token = jwkService.generateToken(user.toDto(), issuer)
                call.respond(hashMapOf("token" to token))
            }
            else
                call.respond(HttpStatusCode.NotFound, "wrong email / password")
        }

        authenticate {
            get("/home") {
                call.respond("get authenticated value from token ${call.userDTO?.username}")
            }
        }

    }
}
