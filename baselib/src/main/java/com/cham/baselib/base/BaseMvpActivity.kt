package com.cham.baselib.base

import android.os.Bundle
import javax.inject.Inject

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
 abstract  class BaseMvpActivity <T : IPresenter> :BaseActivity() {


    @Inject
    lateinit var mPresenter:T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjects()
        mPresenter.onStart()
        initInjectDo()
    }

    abstract fun  initInjects()
    abstract fun  initInjectDo()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }


}