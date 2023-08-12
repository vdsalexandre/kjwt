package vds.com.infra.config

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import vds.com.domain.spi.JwtHandler
import vds.com.domain.spi.JwtUserHandler
import vds.com.infra.config.KoinModule.koinModule
import vds.com.infra.service.JwtService
import vds.com.infra.service.JwtUserService

fun Application.configureModules() {
    install(Koin) {
        slf4jLogger()
        modules(koinModule)
    }
}

object KoinModule {
    val koinModule = module {
        single<JwtHandler> { JwtService() } bind JwtHandler::class
        single<JwtUserHandler> { JwtUserService() } bind JwtUserHandler::class
    }
}