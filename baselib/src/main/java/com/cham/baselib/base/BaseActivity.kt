package com.cham.baselib.base

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 * 基類Activity
 */
abstract class BaseActivity :AppCompatActivity() ,IActivity{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenUtils.adaptScreen4HorizontalSlide(this, 375)
       // BarUtils.setStatusBarColor(this, Color.parseColor("#1FBAF3"), 255)
        BarUtils.setStatusBarAlpha(this)
        setContentView(initLayout())


        initData()
    }



}