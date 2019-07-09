package com.cham.lotteryasst.mvp.contract

import android.app.Activity
import com.cham.baselib.base.IModel
import com.cham.baselib.base.IVew
import com.cham.lotteryasst.entity.DailyEnglishEntity
import com.cham.lotteryasst.entity.PoetryEntity
import io.reactivex.Observable

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
interface SplashContract {

    interface  SplashView :IVew{
         fun getActivity(): Activity
        fun  showTest(content :String)
        fun showDailyEn(str :DailyEnglishEntity)

    }

    interface  SplashModel :IModel{

        fun getTest():String

        fun getPoetry():Observable<PoetryEntity>

        fun getDailyEn():Observable<DailyEnglishEntity>

    }
}