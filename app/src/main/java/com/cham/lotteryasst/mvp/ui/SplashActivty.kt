package com.cham.lotteryasst.mvp.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import com.cham.baselib.base.BaseApplication
import com.cham.baselib.base.BaseMvpActivity
import com.cham.baselib.di.component.DaggerAppComponent
import com.cham.lotteryasst.MainActivity
import com.cham.lotteryasst.R
import com.cham.lotteryasst.di.component.DaggerSplashComponent
import com.cham.lotteryasst.mvp.contract.SplashContract
import com.cham.lotteryasst.mvp.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.view.*

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
class SplashActivty  : BaseMvpActivity<SplashPresenter>(),SplashContract.SplashView{



    override fun initInjectDo() {
        mPresenter.gogo()

        Tv_Content.setOnClickListener({
            startActivity( Intent(this ,MainActivity::class.java))
        })

    }


    override fun initInjects() {
        DaggerSplashComponent.builder().appComponent(BaseApplication.instance.appComponent)
            .view(this)
            .build()
            .inject(this)


    }

    override fun initLayout(): Int {
       return R.layout.activity_splash
    }

    override fun initData() {


    }

    override fun getActivity(): Activity {
        return  this@SplashActivty
    }

    override fun showTest(content: String) {
        Tv_Content.text=content

    }

    override fun showMessage(message: String) {

    }


}