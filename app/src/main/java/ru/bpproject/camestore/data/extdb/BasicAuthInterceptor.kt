package ru.bpproject.camestore.data.extdb

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val credentials = Credentials.basic(AUTH_USER, AUTH_PASS)
        val request = originalRequest.newBuilder()
            .header("Authorization", credentials)
            .build()
        return chain.proceed(request)
    }
    companion object {
        private const val AUTH_USER = "Администратор БП"
        private const val AUTH_PASS = "123"
    }
}