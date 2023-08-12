package vds.com.infra.database

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import vds.com.infra.bootstrap.BcryptDataEncoder.bcryptEncode

fun Application.configureDatabase() {

    val dbConnection = Database.connect(
        url = "jdbc:h2:mem:jwt;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    transaction(dbConnection) {
        SchemaUtils.create(Usersdb)

        Usersdb.insert {
            it[username] = "totoLeVigoureux"
            it[password] = String(bcryptEncode("toto123456789"))
            it[email] = String(bcryptEncode("toto@noob.com"))
        }
    }
}
