package vds.com.infra.service

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import vds.com.domain.model.SimpleUser
import vds.com.domain.spi.JwtUserHandler
import vds.com.infra.bootstrap.BcryptDataEncoder.bcryptVerify
import vds.com.infra.database.Usersdb

class JwtUserService : JwtUserHandler {
    override suspend fun findUser(email: String, password: String): SimpleUser? {

        return dbQuery {
            Usersdb
                .selectAll()
                .filter {
                    bcryptVerify(email, it[Usersdb.email].toByteArray()) && bcryptVerify(
                        password,
                        it[Usersdb.password].toByteArray()
                    )
                }
                .map { SimpleUser(it[Usersdb.email], it[Usersdb.username]) }
                .singleOrNull()
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}