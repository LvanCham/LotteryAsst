package com.cham.lotteryasst.di.component

import com.cham.baselib.di.component.AppComponent
import com.cham.baselib.di.scope.ActivtyScope
import com.cham.lotteryasst.di.module.SplashModule
import com.cham.lotteryasst.mvp.contract.SplashContract
import com.cham.lotteryasst.mvp.ui.SplashActivty
import dagger.BindsInstance
import dagger.Component

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
@ActivtyScope
@Component(dependencies = [AppComponent::class],modules = [SplashModule::class])
interface SplashComponent {
    fun inject(activity: SplashActivty)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: SplashContract.SplashView): SplashComponent.Builder

        fun appComponent(appComponent: AppComponent): SplashComponent.Builder

        fun build(): SplashComponent
    }
}