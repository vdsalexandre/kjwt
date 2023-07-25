package vds.com.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginUser(val email: String, val password: String)
