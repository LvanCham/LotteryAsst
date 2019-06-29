package com.cham.lotteryasst.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.cham.baselib.base.BaseApplication

/**
 * Hello World
 * Date: 2019/6/26
 * Author: Cham
 */
class MyApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }

}