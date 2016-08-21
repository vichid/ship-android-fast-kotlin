package com.example.app.data.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Replaces default user agent by a custom one provided with dagger
 */
class UserAgentInterceptor(private val userAgentHeaderValue: String) : okhttp3.Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithUserAgent = originalRequest
                .newBuilder()
                .removeHeader(USER_AGENT_HEADER_NAME)
                .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                .build()
        return chain.proceed(requestWithUserAgent)
    }

    companion object {
        private val USER_AGENT_HEADER_NAME = "User-Agent"
    }
}
