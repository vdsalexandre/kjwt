package vds.com

import io.ktor.server.application.Application
import vds.com.domain.api.loginResource
import vds.com.infra.config.configureModules
import vds.com.infra.config.configureSecurity
import vds.com.infra.config.configureSerialization
import vds.com.infra.database.configureDatabase

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureModules()
    configureSerialization()
    configureSecurity(environment)
    loginResource(environment)
    configureDatabase()
}
