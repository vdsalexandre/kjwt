package vds.com.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val email: String, val password: String, val username: String)
