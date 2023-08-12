package vds.com.infra.model

import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.Principal
import io.ktor.server.auth.authentication
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(val email: String, val username: String) : Principal

val ApplicationCall.UserDTO get() = authentication.principal<UserDTO>()