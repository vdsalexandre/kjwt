package vds.com.infra.bootstrap

import java.security.KeyPair
import java.security.KeyPairGenerator

object JwtConfig {
    private const val KEY_SIZE = 4096
    private const val KEY_ALGORITHM = "RSA"
    private var keyPair: KeyPair? = null

    fun generateKeyPair(): KeyPair {
        return if (keyPair == null) {
            val generator = KeyPairGenerator.getInstance(KEY_ALGORITHM)
            generator.initialize(KEY_SIZE)
            keyPair = generator.generateKeyPair()
            keyPair!!
        } else {
            keyPair!!
        }

    }
}