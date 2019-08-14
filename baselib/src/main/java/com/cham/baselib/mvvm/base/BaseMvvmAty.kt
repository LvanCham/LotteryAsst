package com.cham.baselib.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.cham.baselib.base.IActivity

/**
 * Hello World
 * Date: 2019/7/29
 * Author: Cham
 */
abstract class BaseMvvmAty <VM:BaseViewModel>: AppCompatActivity(),IActivity {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        ScreenUtils.adaptScreen4VerticalSlide(this, 375)
        // BarUtils.setStatusBarColor(this, Color.parseColor("#1FBAF3"), 255)
        BarUtils.setStatusBarAlpha(this)
        setContentView(initLayout())

        initData()
    }

    open fun providerVMClass(): Class<VM>? = null

    private fun initVM(){
        providerVMClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(viewModel)
        }
    }




    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }
}