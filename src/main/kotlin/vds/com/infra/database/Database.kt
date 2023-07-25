package vds.com.infra.database

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import vds.com.infra.model.Usersdb

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
            it[password] = "\$2y\$10\$0mFhdro7G1G/yxYuXG5cpOKtfbRq1oMSdOxbl3pb1PHctXLM00j8S"
            it[email] = "\$2y\$10\$V0SBm1doxIdVn3B4lYc9EOG7Z6G7IO5XTHAKSKEDHG/gj/L8DZeZC"
        }
    }
}
