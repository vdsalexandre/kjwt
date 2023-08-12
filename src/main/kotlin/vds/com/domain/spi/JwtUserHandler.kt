package vds.com.domain.spi

import vds.com.domain.model.SimpleUser

interface JwtUserHandler {

    suspend fun findUser(email: String, password: String): SimpleUser?
}