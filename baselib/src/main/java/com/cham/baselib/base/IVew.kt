package com.cham.baselib.base

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
interface IVew {
    /**
     * 显示加载
     */
    fun showLoading() {

    }

    /**
     * 隐藏加载
     */
    fun hideLoading() {

    }

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 `null`
     */
    fun showMessage(message: String)
}