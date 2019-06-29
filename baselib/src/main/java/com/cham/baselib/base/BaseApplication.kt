package com.cham.baselib.base

import android.app.Application
import com.cham.baselib.di.component.AppComponent
import com.cham.baselib.di.component.DaggerAppComponent
import com.cham.baselib.di.module.AppModule

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
open class BaseApplication :Application() {

    companion object {
        lateinit var instance: BaseApplication
    }
    lateinit var  appComponent:AppComponent
    override fun onCreate() {
        super.onCreate()
        instance=this
        appComponent = DaggerAppComponent.builder().application(this).appModule(AppModule(this)).build()

    }
}