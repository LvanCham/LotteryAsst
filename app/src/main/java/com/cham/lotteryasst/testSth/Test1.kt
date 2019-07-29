package com.cham.lotteryasst.testSth

import android.util.Log

/**
 * Hello World
 * Date: 2019/7/27
 * Author: Cham
 */
class Test1 {


    val TAG = "TEST"
    /*主构造*/
    constructor() {
    Log.e(TAG,"构造函数")
    }

    constructor(t:String){
        Log.e(TAG,"有参构造函数")
    }


    /**
     * 伴生对象  相当于 java  static
     * */
    companion object {

        val SS ="sssssss"
        /*伴生对象中的初始化代码*/
        init {
            Log.e("111111","伴生对象")
        }

        init {
            Log.e("22222","初始化代码块")
        }
    }

    /*初始化代码块*/
    init {
        Log.e(TAG,"初始化代码块")
    }


    init {
        Log.e(TAG,"初始化代码块2")
    }
}