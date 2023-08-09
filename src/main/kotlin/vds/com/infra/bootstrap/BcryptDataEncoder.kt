package vds.com.infra.bootstrap

import com.toxicbakery.bcrypt.Bcrypt


object BcryptDataEncoder {

    private const val saltRounds = 14

    fun bcryptEncode(data: String): ByteArray = Bcrypt.hash(data, saltRounds)

    fun bcryptVerify(data: String, hashedData: ByteArray): Boolean = Bcrypt.verify(data, hashedData)
}