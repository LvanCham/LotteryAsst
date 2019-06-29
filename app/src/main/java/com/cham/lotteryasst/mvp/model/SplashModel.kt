package com.cham.lotteryasst.mvp.model

import com.cham.baselib.base.BaseModel
import com.cham.baselib.base.IModel
import com.cham.lotteryasst.mvp.contract.SplashContract
import javax.inject.Inject

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
class SplashModel  @Inject constructor() :BaseModel(),SplashContract.SplashModel {

    override fun getTest(): String {
        return "朝為田舍郎，暮登天子堂"
    }
}