package vds.com.infra.service

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import vds.com.domain.model.User
import vds.com.domain.spi.JwtUserRepository
import vds.com.infra.model.Usersdb

class JwtUserService : JwtUserRepository {
    override suspend fun findUser(email: String, password: String): User? {
        return dbQuery {
            Usersdb
                .selectAll()
                .filter { it[Usersdb.email] == email && it[Usersdb.password] == password }
                .map { User(it[Usersdb.email], it[Usersdb.password], it[Usersdb.username]) }
                .singleOrNull()
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}