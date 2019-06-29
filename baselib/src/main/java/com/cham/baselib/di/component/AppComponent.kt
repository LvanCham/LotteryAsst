package com.cham.baselib.di.component

import android.content.Context
import com.cham.baselib.base.BaseApplication
import com.cham.baselib.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    // Builder 模式构建 Component方便灵活的 向 Component  推荐用
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): AppComponent.Builder

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    fun getAppContext(): Context
}



