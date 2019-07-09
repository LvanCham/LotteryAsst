package com.cham.baselib.net

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Hello World
 * Date: 2019/7/9
 * Author: Cham
 */
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿

       var  request :Request = chain.request()
        //请求发起的时间
        val t1 = System.nanoTime()
        LogUtils.e(
            String.format(
                "请求URL------%s on %s%n请求头------%s",
                request.url(), chain.connection(), request.headers()
            )
        )
        val response = chain.proceed(request)
          //收到响应的时间
        val t2 = System.nanoTime()

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理

       var  responseBody :ResponseBody = response.peekBody(1024*1024)
        LogUtils.e(
            String.format(
                "响应URL-------: %s %n响应数据------%s 请求用时--------%.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6,
                response.headers()
            )
        )

        return  response

    }
}