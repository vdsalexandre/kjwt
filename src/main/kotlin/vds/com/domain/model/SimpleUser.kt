package vds.com.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SimpleUser(val username: String, val email: String)
