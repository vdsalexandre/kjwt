package vds.com.domain.spi

import vds.com.domain.model.User

interface JwtUserRepository {

    suspend fun findUser(email: String, password: String): User?
}