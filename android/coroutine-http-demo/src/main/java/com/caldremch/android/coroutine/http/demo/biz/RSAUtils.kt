package com.caldremch.android.coroutine.http.demo.biz

import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

/**
 * Created by Leon on 2022/7/5
 */
object RSAUtils {

    private const val RSA_PUB ="xxxxxx"

    @Throws(Exception::class)
    fun encrypt(str: String): String {
        //base64编码的公钥
        val decoded: ByteArray = Base64.getDecoder().decode(RSA_PUB)
        val pubKey = KeyFactory.getInstance("RSA")
            .generatePublic(X509EncodedKeySpec(decoded)) as RSAPublicKey
        //RSA加密
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, pubKey)
        return String(
            Base64.getEncoder().encode(
                cipher.doFinal(str.toByteArray(StandardCharsets.UTF_8))
            )
        )
    }
}