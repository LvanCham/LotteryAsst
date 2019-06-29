package com.cham.lotteryasst.di.module

import com.cham.lotteryasst.entity.User
import dagger.Module
import dagger.Provides

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
@Module
open class TestModule {


    @Provides
    fun provideUser():User{
        return  User("AAAA")
    }
}