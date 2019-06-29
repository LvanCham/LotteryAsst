package com.cham.lotteryasst.mvp.presenter

import com.cham.baselib.base.BasePresenter
import com.cham.baselib.di.scope.ActivtyScope
import com.cham.lotteryasst.mvp.contract.SplashContract
import javax.inject.Inject

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */

@ActivtyScope
class SplashPresenter @Inject constructor(rootView: SplashContract.SplashView, model:SplashContract.SplashModel) :
    BasePresenter<SplashContract.SplashView, SplashContract.SplashModel>(rootView,model) {




    /**
     * 去吧 皮卡丘
     * */
    fun gogo(){

        mRootView.showTest(mModel.getTest())
    }



}