package vds.com.infra.model

import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.Principal
import io.ktor.server.auth.authentication
import kotlinx.serialization.Serializable
import vds.com.domain.model.SimpleUser

@Serializable
data class UserDTO(val username: String, val email: String) : Principal {

    companion object {
        fun SimpleUser.toDto() = UserDTO(username, email)
    }
}

val ApplicationCall.userDTO get() = authentication.principal<UserDTO>()
