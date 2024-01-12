package es.dpr.marvelsampleapp.network.service

import es.dpr.marvelsampleapp.di.PrivateKey
import es.dpr.marvelsampleapp.di.PublicKey
import es.dpr.marvelsampleapp.network.generateHash
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Date
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    @PrivateKey private val privateKey: String,
    @PublicKey private val publicKey: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, privateKey, publicKey)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}