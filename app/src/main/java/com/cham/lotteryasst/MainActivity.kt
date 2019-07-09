package com.cham.lotteryasst

import android.animation.Animator
import android.util.Log
import com.blankj.utilcode.util.BarUtils
import com.cham.baselib.base.BaseActivity

import com.cham.lotteryasst.utils.SpUtils
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.ValueAnimator
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation






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

        BarUtils.setStatusBarColor(this, resources. getColor(R.color.colorYin),0)

        btn_load.setOnClickListener {
            if(i==0){
                i =1
                btn_load.ProgressStart()
            }else{
                i =0
                btn_load.ProgressStop()
            }
          }

        ContentPanel.post {
            val animator = createRevealAnimator(mX, mY)
            animator.start()
        }
    }



    fun createRevealAnimator(x:Int,y:Int) : Animator{

        var startRadius  = 0f
        var endRadius  = Math.hypot(ContentPanel.height.toDouble(), ContentPanel.width.toDouble()).toFloat()
        var animator  =  ViewAnimationUtils.createCircularReveal(ContentPanel,
            x,y,startRadius,endRadius)
        animator.duration = 600
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator .addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                ViewPuppet.startAnimation(createAlphaAnimation());
                //动画结束时，揭露动画设置为不可见
                ViewPuppet.visibility = View.INVISIBLE;
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }


        })

        return animator
    }


    private fun createAlphaAnimation(): AlphaAnimation {
        val aa = AlphaAnimation(1f, 0f)
        aa.duration = 400
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
