package com.cham.lotteryasst.utils

import com.cham.lotteryasst.entity.EventEntity
import org.greenrobot.eventbus.EventBus


/**
 * Hello World
 * Date: 2019/5/17
 * Author: Cham
 * EventBus 封一下
 */
class EventBusUtils {

    companion object{
        /**
         * 绑定 接受者
         * @param subscriber
         */
        fun register(subscriber: Any){
            EventBus.getDefault().register(subscriber)

        }

        /**
         * 解绑定
         * @param subscriber
         */
        fun unregister(subscriber: Any){
            EventBus.getDefault().unregister(subscriber)
        }


        /**
         * 发送消息(事件)
         * @param event
         */
        fun sendEvent(event: EventEntity<Any>){
            EventBus.getDefault().post(event)
        }


        /**
         * 发送 粘性 事件
         * 粘性事件，在注册之前便把事件发生出去，等到注册之后便会收到最近发送的粘性事件（必须匹配）
         * 注意：只会接收到最近发送的一次粘性事件，之前的会接受不到。
         * @param event
         */
        fun sendStickyEvent(event:EventEntity<Any>){

            EventBus.getDefault().postSticky(event)
        }
        /**
         * 移除全部黏性事件
         * */
        fun removeSticky(){
            EventBus.getDefault().removeAllStickyEvents()
        }

        /**
         * 移除某个黏性事件 带返回
         * */
        fun removeItemSticky(event:EventEntity<Any>) : Boolean{
           return EventBus.getDefault().removeStickyEvent(event)
        }

    }

}