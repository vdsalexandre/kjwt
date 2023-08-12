package vds.com.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SimpleUser(val email: String, val username: String)
