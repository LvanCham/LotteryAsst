package com.cham.lotteryasst.mvp.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import com.cham.baselib.base.BaseApplication
import com.cham.baselib.base.BaseMvpActivity
import com.cham.baselib.di.component.DaggerAppComponent
import com.cham.lotteryasst.MainActivity

import com.cham.lotteryasst.di.component.DaggerSplashComponent
import com.cham.lotteryasst.mvp.contract.SplashContract
import com.cham.lotteryasst.mvp.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.view.*
import android.view.animation.LinearInterpolator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.ViewAnimationUtils
import com.cham.lotteryasst.R
import android.animation.AnimatorListenerAdapter

import android.animation.Animator
import android.view.animation.AccelerateDecelerateInterpolator
import com.cham.lotteryasst.entity.DailyEnglishEntity


/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
class SplashActivty  : BaseMvpActivity<SplashPresenter>(),SplashContract.SplashView{


    override fun showDailyEn(str: DailyEnglishEntity) {
        Tv_en.text = str.content
        Tv_zh.text = str.note

    }

    private var centerX =0
    private var centerY =0
    override fun initInjectDo() {
        mPresenter.gogo()
        //点击事件
        pageTurnView.setOnClickListener {
          //  doRevealAnimation()
            val vLocation = IntArray(2)
            pageTurnView.getLocationInWindow(vLocation)
            centerX = vLocation[0] + pageTurnView.measuredWidth / 2
            centerY = vLocation[1] + pageTurnView.measuredHeight / 2
            val i = Intent(this ,MainActivity::class.java)
            i.putExtra("cx",centerX)
            i.putExtra("cy",centerY)
            startActivity(i)
        }
    }


    override fun initInjects() {
        DaggerSplashComponent.builder().appComponent(BaseApplication.instance.appComponent)
            .view(this)
            .build()
            .inject(this)


    }

    override fun initLayout(): Int {
       return R.layout.activity_splash
    }

    override fun initData() {
        initUI()
    }

    override fun getActivity(): Activity {
        return  this@SplashActivty
    }

    override fun showTest(content: String) {
       Tv_Content.text=content

    }

    override fun showMessage(message: String) {

    }

    private fun  initUI(){
        // 第一个动画
        val animator1 = ObjectAnimator.ofInt(pageTurnView, "degreeY", 0, -45)
        animator1.duration = 1000
        // 第二个动画
        val animator2 = ObjectAnimator.ofInt(pageTurnView, "degreeZ", 0, -270)
        animator2.duration = 1000
        // 第三个动画
        val animator3 = ObjectAnimator.ofInt(pageTurnView, "degreeNextY", 0, -10)
        animator3.duration = 1000

        // 三个动画按顺序执行
        val animatorSet = AnimatorSet()
        animatorSet.interpolator = LinearInterpolator()
        animatorSet.playSequentially(animator1,animator2,animator3)
        animatorSet.start()



    }


    private var mflag = false

    /**
     * 揭露动画 测试
     * */
    private fun doRevealAnimation(){
        val vLocation = IntArray(2)
        pageTurnView.getLocationInWindow(vLocation)

         centerX = vLocation[0] + pageTurnView.measuredWidth / 2
         centerY = vLocation[1] + pageTurnView.measuredHeight / 2

        val height = view_puppet.height
        val width = view_puppet.width

        val maxRradius = Math.hypot(height.toDouble(), width.toDouble())

        if(mflag){
            val  animator = ViewAnimationUtils.
                createCircularReveal(view_puppet,centerX,centerY, maxRradius.toFloat(),0f)
            animator.duration = 1000
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view_puppet.visibility = View.INVISIBLE
                }
            })
            animator.start()
           mflag = false
        }else{
            val  animator = ViewAnimationUtils.
                createCircularReveal(view_puppet,centerX,centerY,0f,maxRradius.toFloat())
            animator.duration = 1000
            animator.addListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {}

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationStart(animation: Animator?) {
                    view_puppet.visibility = View.VISIBLE
                }

            })
            animator.start()
            mflag = true
        }

    }

    override fun onRestart() {
        super.onRestart()
        //动画需要依赖于某个视图才可启动，
        // 这里依赖于根布局对象，并且开辟一个子线程，充分利用资源
        content.post(Runnable {
            val vLocation = IntArray(2)
            pageTurnView.getLocationInWindow(vLocation)
            centerX = vLocation[0] + pageTurnView.measuredWidth / 2
            centerY = vLocation[1] + pageTurnView.measuredHeight / 2
            val animator = createRevealAnimator(centerX, centerY)
            animator.start()
        })
    }


    private fun createRevealAnimator(x: Int, y: Int): Animator {
        val startRadius = Math.hypot(content.height.toDouble(), content.width.toDouble()).toFloat()
        val endRadius = pageTurnView.measuredWidth / 2

        //注意揭露动画开启时是用根布局作为操作对象，关闭时用揭露层作为操作对象
        val animator = ViewAnimationUtils.createCircularReveal(
            view_puppet, x, y,
            startRadius,
            endRadius.toFloat()
        )
        animator.duration = 500
        //设置插值器
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addListener(object:Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                view_puppet.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {
                //按下返回键时，动画开启，揭露层设置为可见
                view_puppet.visibility = View.VISIBLE
            }

        })
        return animator
    }
}