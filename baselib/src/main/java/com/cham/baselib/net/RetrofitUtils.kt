package com.cham.baselib.net

import com.cham.baselib.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Hello World
 * Date: 2019/7/9
 * Author: Cham
 */

fun  getOkHttpClient() : OkHttpClient {

    var httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    // OkHttp进行添加拦截器loggingInterceptor
    httpClientBuilder.readTimeout(20000, TimeUnit.MILLISECONDS)
    httpClientBuilder.connectTimeout(20000, TimeUnit.MILLISECONDS)
    httpClientBuilder.addInterceptor(LoggingInterceptor())
    httpClientBuilder.addInterceptor(HttpHeaderInterceptor())
    httpClientBuilder.addNetworkInterceptor(HttpCacheInterceptor())
    return httpClientBuilder.build()

}

fun  getRetrofitBuilder(): Retrofit.Builder{
      var gson :Gson  =GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
      var okHttpClient:OkHttpClient = getOkHttpClient()

      return Retrofit.Builder().client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(BASE_URL)
  }

fun  getRetrofitBuilder( Url:String): Retrofit.Builder{
    var gson :Gson  =GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
    var okHttpClient:OkHttpClient = getOkHttpClient()
    return Retrofit.Builder().client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(Url)
}

fun <T> getApiService(cls: Class<T>, baseUrl: String): T {
    val retrofit = getRetrofitBuilder(baseUrl).build()
    return retrofit.create(cls)
}


fun <T> getApiService(cls: Class<T>): T {
    val retrofit =getRetrofitBuilder().build()
    return retrofit.create(cls)


}

//
//fun <T> handleResult(): ObservableTransformer<T, T> {
//
//
//
//}
