package com.cham.lotteryasst.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.cham.lotteryasst.R


/**
 * Hello World
 * Date: 2019/7/3
 * Author: Cham
 * 一个反转动画
 */
class PageTurnView : View {
    private lateinit var  mPaint : Paint

    private  lateinit var mCamre :Camera

    private lateinit var mBitmap: Bitmap

    /**
     * a半边的动画旋转角度 第一个动画 3D
     * */
    private   var  degreeY:Int =0

    /**
     * b半边的动画旋转角度  第三个动画 3D
     * */
    private var degreeNextY:Int =0

    /**
     * 画布的动画旋转角度 （第二个动画 2d）
     * */
    private var degreeZ:Int =0

    constructor(context: Context?) : super(context) {

    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs) {
        initSth()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,attrs,defStyleAttr) {

    }


    private fun initSth(){
        //获取图标
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon_1)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        //移动相机位置 使投影正常
        mCamre = Camera()
        mCamre.setLocation(0f,0f, -6*resources.displayMetrics.density)

    }

    public fun  getDegreeY ():Int{
        return degreeY
    }

    public fun setDegreeY(degreenY :Int){

        this.degreeY = degreenY
    }


    fun getDegreeZ(): Int {
        return degreeZ
    }

    fun setDegreeZ(degreeZ: Int) {
        this.degreeZ = degreeZ
        invalidate()
    }

    fun getDegreeNextY(): Int {
        return degreeNextY
    }

    fun setDegreeNextY(degreeNextY: Int) {
        this.degreeNextY = degreeNextY
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var centerX:Float= (width /2).toFloat()
        var centerY = (height /2).toFloat()

        var x = centerX - mBitmap.width/2
        var y = centerY -mBitmap.height/2

        //a 半边处理
        canvas!!.save()
        mCamre.save()
        canvas.translate(centerX,centerY)
        //第一个动画旋转
        mCamre.rotateY(degreeY.toFloat())
        //画布旋转
        canvas.rotate(degreeZ.toFloat())

        //此时图标正中心跟坐标原点位置相同
        canvas.clipRect(0f,-centerY,centerX,centerY)
        mCamre.applyToCanvas(canvas)
        //记住画布要旋转回来
        canvas.rotate(-degreeZ.toFloat())
        canvas.translate(-centerX,-centerY)
        mCamre.restore()

        canvas.drawBitmap(mBitmap,x,y,mPaint)
        canvas.restore()

        // b 的半边处理

        canvas.save()
        mCamre.save()

        canvas.translate(centerX,centerY)
        //b半边也要跟着旋转
        canvas.rotate(degreeZ.toFloat())
        //第三个动画 3d 旋转

        mCamre.rotateY(degreeNextY.toFloat())
        canvas.clipRect(-centerX,-centerY,0f,centerY)
        mCamre.applyToCanvas(canvas)
        //画布旋转回来
        canvas.rotate(-degreeZ.toFloat())
        canvas.translate(-centerX,-centerY)
        mCamre.restore()

        canvas.drawBitmap(mBitmap,x,y,mPaint)
        canvas.restore()








    }

}