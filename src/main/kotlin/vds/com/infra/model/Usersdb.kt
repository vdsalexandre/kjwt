package vds.com.infra.model

import org.jetbrains.exposed.dao.id.LongIdTable

object Usersdb : LongIdTable() {
    val username = varchar("username", 255)
    val password = varchar("password", 255)
    val email = varchar("email", 255)
}