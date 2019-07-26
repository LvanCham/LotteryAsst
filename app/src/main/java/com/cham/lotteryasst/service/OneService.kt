package com.cham.lotteryasst.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Hello World
 * Date: 2019/7/26
 * Author: Cham
 */
class OneService : Service() {


    /**
     *
     * 初始化
     * */
    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
      return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("OneService",""+super.onStartCommand(intent, flags, startId))
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 销毁
     *
     * */
    override fun onDestroy() {
        super.onDestroy()
    }


}
