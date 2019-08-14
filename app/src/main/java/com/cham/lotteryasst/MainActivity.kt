package com.cham.lotteryasst

import android.animation.Animator
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.blankj.utilcode.util.BarUtils
import com.cham.baselib.base.BaseActivity

import com.cham.lotteryasst.utils.SpUtils
import kotlinx.android.synthetic.main.activity_main.*

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import com.cham.baselib.BASE_URL
import com.cham.baselib.net.RetrofitFactory
import com.cham.lotteryasst.api.ApiService
import com.cham.lotteryasst.service.OneService

import kotlinx.coroutines.*
import kotlin.math.hypot
import kotlin.system.measureTimeMillis


class MainActivity : BaseActivity() {

    private val TAG="MainActivity"
    private   var  us:String =""
    private  var  i = 0
    private var mX: Int = 0
    private var mY: Int = 0

    override fun initLayout(): Int {
     return R.layout.activity_main
    }


    override fun initData() {

        mX = intent.getIntExtra("cx", 0)
        mY = intent.getIntExtra("cy", 0)



        var  job =  GlobalScope.launch (Dispatchers.Main){

            var content = fetchData()
            Log.e("Coroutine1",content)
        }

        var  job2 =  GlobalScope.launch {

            Log.e("Coroutine2",fetchData())
            testA()

        }

        btn_load.setOnClickListener {
            if(i==0){
                i =1
                btn_load.ProgressStart()
             startService(Intent(this@MainActivity, OneService::class.java))
            }else{
                i =0
                btn_load.ProgressStop()
            }
          }

        ContentPanel.post {
            val animator = createRevealAnimator(mX, mY)
            animator.start()
        }

        //--------------------------协程---- 好像不用start（）
//        job.start()
//        job2.start()
        Log.e("Coroutine3","Stop")
//
//        runBlocking {     // 这块阻塞了主线程
//            delay(2000L)  // 延迟2s，让jvm不挂掉
//        }
        Log.e("Coroutine4","Stop2")

        /**
         * 协程的启动
         * */

        //UI线程
        val  job4 =   GlobalScope.launch(Dispatchers.Main){

            try{
                var AAAA =  RetrofitFactory.instance(BASE_URL).create(ApiService::class.java).getAncientPoetry2()
                Log.e(TAG, AAAA.content +"    ${Thread.currentThread().name}")
            }catch (e:Throwable){
                Log.e(TAG, "    ${Thread.currentThread().name}   --- $e")
            }



        }

        //UI线程
//        val job5 = GlobalScope.launch(Dispatchers.Main){
//
//            var time = measureTimeMillis{
//                var pop = RetrofitFactory.instance("http://open.iciba.com/")
//                    .create(ApiService::class.java).getDailyEn2()
//                Log.e("job5", pop.content+"   ---     ${Thread.currentThread().name}")
//            }
//
//
//            Log.e("time", "花费的时间 ： $time")
//
//
//        }

        //IO线程
//        val job6 = GlobalScope.launch(Dispatchers.IO){
//            var pop = RetrofitFactory.insttt(BASE_URL).value.create(ApiService::class.java).getAncientPoetry2()
//            Log.e("job6", pop.content+"    ${Thread.currentThread().name}")
//        }

//        var  p = Test1()



    }


    fun testA()  = runBlocking<Unit>{

        delay(1000L)
        Log.e("Coroutine5","testA")

        val job = launch {

            delay(1000L)
            Log.e("Coroutine6","testA1")
        }.join()

    }
    suspend fun fetchData():String{
        delay(2000)
        return "content"
    }

    fun createRevealAnimator(x:Int,y:Int) : Animator{

        var startRadius  = 0f
        var endRadius  = hypot(ContentPanel.height.toDouble(), ContentPanel.width.toDouble()).toFloat()
        var animator  =  ViewAnimationUtils.createCircularReveal(ContentPanel,
            x,y,startRadius,endRadius)
        animator.duration = 1000
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator .addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {

                //动画结束时，揭露动画设置为不可见
                ViewPuppet.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                ViewPuppet.startAnimation(createAlphaAnimation())
            }
        })

        return animator
    }


    private fun createAlphaAnimation(): AlphaAnimation {
        val aa = AlphaAnimation(2f, 0f)
        aa.duration = 1000
        aa.interpolator = AccelerateDecelerateInterpolator()//设置插值器
        return aa
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
