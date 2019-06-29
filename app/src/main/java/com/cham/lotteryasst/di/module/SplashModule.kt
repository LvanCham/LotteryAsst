package com.cham.lotteryasst.di.module

import com.cham.lotteryasst.mvp.contract.SplashContract
import com.cham.lotteryasst.mvp.model.SplashModel
import com.cham.lotteryasst.mvp.presenter.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */

@Module
open class SplashModule{

    @Provides
    fun providesSplahPresenter(rootview :SplashContract.SplashView,model:SplashModel):SplashPresenter{

        return SplashPresenter(rootview,model)
    }
}