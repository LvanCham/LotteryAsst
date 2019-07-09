package com.cham.baselib.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Hello World
 * Date: 2019/7/9
 * Author: Cham
 */
class HttpHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        //  配置请求头
        val accessToken = "token"
        val  tokenType = "tokenType"

        var request =chain.request().newBuilder()
            .addHeader("app_key","app_Id")
            .header("Authorization", "$tokenType $accessToken")
            .header("Content-Type", "application/json")
            .addHeader("Connection", "close")
            .addHeader("Accept-Encoding", "identity")
            .build()

        return  chain.proceed(request)
    }
}