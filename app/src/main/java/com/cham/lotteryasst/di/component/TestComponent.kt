package com.cham.lotteryasst.di.component

import com.cham.baselib.di.component.AppComponent
import com.cham.baselib.di.scope.ActivtyScope
import com.cham.lotteryasst.MainActivity

import com.cham.lotteryasst.di.module.TestModule
import dagger.Component

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
@ActivtyScope
@Component(dependencies = [AppComponent::class],modules = [TestModule::class])
interface TestComponent {

    fun injetct(aty :MainActivity)

}