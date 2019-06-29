package com.cham.lotteryasst

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.BarUtils
import com.cham.baselib.base.BaseActivity
import com.cham.lotteryasst.entity.User
import com.cham.lotteryasst.utils.SpUtils
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val TAG="MainActivity"
    private   var  us:String =""

    override fun initLayout(): Int {
     return R.layout.activity_main
    }

    override fun initData() {
        BarUtils.setStatusBarColor(this, resources. getColor(R.color.colorB3),0)
    }




    /**
     * sp用法
     * */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun Sp_Do_it(){
        us = SpUtils<String>().sp.getString("key","aaaaa")
        us ="1113321321313"
        us = "555666"
        //Sp 写法二 直接获取  通过by
        var ss by SpUtils("lalal","sssss")
        ss="ppllplp"
        Log.e(TAG,ss)
        Log.e(TAG , us)
    }
}
