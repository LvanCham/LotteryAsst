package com.cham.lotteryasst.mvp.contract

import android.app.Activity
import com.cham.baselib.base.IModel
import com.cham.baselib.base.IVew

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
interface SplashContract {

    interface  SplashView :IVew{
         fun getActivity(): Activity
        fun  showTest(content :String)
    }

    interface  SplashModel :IModel{

        fun getTest():String
    }
}