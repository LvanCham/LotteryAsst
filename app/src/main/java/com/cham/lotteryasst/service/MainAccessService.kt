package com.cham.lotteryasst.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

/**
 * Hello World
 * Date: 2019/7/26
 * Author: Cham
 *
 *
 * 辅助服务
 */
class MainAccessService : AccessibilityService() {


    override fun onServiceConnected() {
        super.onServiceConnected()
        //做一些初始化的操作
    }



    override fun onInterrupt() {

        //AccessibilityService被中断时会调用，在整个生命周期里会被调用多次。
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        //手机的所有操作信息都会通过这个方法回调
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}