package com.cham.baselib.di.module

import android.content.Context
import com.cham.baselib.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
@Module
open class AppModule(val context: BaseApplication) {

    @Provides
    @Singleton
    fun getApplicationContext(): Context {
        return context
    }
}