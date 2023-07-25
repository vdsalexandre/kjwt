package vds.com.infra.config

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import vds.com.infra.config.KoinModule.koinModule
import vds.com.infra.service.JwkService
import vds.com.infra.service.JwtUserService

fun Application.configureModules() {
    install(Koin) {
        slf4jLogger()
        modules(koinModule)
    }
}

object KoinModule {
    val koinModule = module {
        single { JwkService() }
        single { JwtUserService() }
    }
}