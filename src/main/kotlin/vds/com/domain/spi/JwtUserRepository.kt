package vds.com.domain.spi

import vds.com.domain.model.SimpleUser

interface JwtUserRepository {

    suspend fun findUser(email: String, password: String): SimpleUser?
}