package com.cham.lotteryasst.mvp.model

import com.cham.baselib.base.BaseModel
import com.cham.baselib.net.getApiService
import com.cham.lotteryasst.api.ApiService
import com.cham.lotteryasst.entity.DailyEnglishEntity
import com.cham.lotteryasst.entity.PoetryEntity
import com.cham.lotteryasst.mvp.contract.SplashContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
class SplashModel  @Inject constructor() :BaseModel(),SplashContract.SplashModel {



    override fun getDailyEn(): Observable<DailyEnglishEntity> {
        return  getApiService(ApiService::class.java,"http://open.iciba.com/").getDailyEn()

    }


    override fun getPoetry(): Observable<PoetryEntity> {

        return getApiService(ApiService::class.java,"https://api.gushi.ci/").getAncientPoetry()
    }

    override fun getTest(): String {
        return "朝為田舍郎，暮登天子堂"
    }





}