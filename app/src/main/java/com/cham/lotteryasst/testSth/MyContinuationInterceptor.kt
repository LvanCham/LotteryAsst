package com.cham.lotteryasst.testSth

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

/**
 * Hello World
 * Date: 2019/7/30
 * Author:
 * 协程调度器
 */
class MyContinuationInterceptor(override val key: CoroutineContext.Key<*>) : ContinuationInterceptor {



    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}