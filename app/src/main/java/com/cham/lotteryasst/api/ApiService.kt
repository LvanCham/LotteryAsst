package com.cham.lotteryasst.api




import com.cham.lotteryasst.entity.DailyEnglishEntity
import com.cham.lotteryasst.entity.PoetryEntity
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Hello World
 * Date: 2019/7/2
 * Author: Cham
 */
interface ApiService {

    /**
     * 今天古诗 获取
     * */
    @GET("all.json")
    fun getAncientPoetry(): Observable<PoetryEntity>

    /**
     * 每日英语
     * */
    @GET("dsapi")
    fun getDailyEn():Observable<DailyEnglishEntity>



    // 协程
    @GET("all.json")
    suspend   fun getAncientPoetry2(): PoetryEntity


    @GET("dsapi")
    suspend  fun getDailyEn2():DailyEnglishEntity



}