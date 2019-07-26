package com.cham.lotteryasst.widget

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import kotlin.math.max


/**
 * Hello World
 * Date: 2019/7/19
 * Author: Cham
 * 自定义View
 */
class DiskView  : View {

    private lateinit var  mContext:Context

    private  var mWidth : Int =0

    private var mHeight : Int =0

    private var mPadding =0

    //画布边缘半径
    private var mRaius =0
    //起始角度
    private var mStarAngle = 150
    //绘制角度
    private var mSweepAngle = 240

    private var mMin=0
    private var mMax =260

    //国际六档
    private var mSection = 6

    private var mHeaderText = "km/h"
    //画笔宽度
    private var mStrokeWidth = 0

    //长刻度的相对圆弧的长度
    private var mLength1 =0
    //刻度读数顶部的相对圆弧的长度
    private var mLength2 =1
    // 指针长半径
    private val mPLRadius: Int = 0
    // 指针短半径
    private val mPSRadius: Int = 0
    //圆心坐标
    private var mCenterX  =0f
    private var mCenterY =0f


    private lateinit var mPaint : Paint

    private  lateinit var  mRectFArc : RectF

    private lateinit var mRectFInnerArc :RectF

    private lateinit var  mRectText : Rect



    constructor(context: Context): super(context){
        mContext = context
    }
    constructor(context: Context,attributes: AttributeSet): super(context,attributes ){
        mContext = context
    }
    constructor(context: Context,attributes: AttributeSet,defStyleAttr:Int):super(context,attributes,defStyleAttr){
        mContext = context
        initView()
    }


    private fun initView(){

        mPaint = Paint()
        mPaint.isAntiAlias =true
        mPaint.strokeCap = Paint.Cap.ROUND


        mStrokeWidth = SizeUtils.dp2px(3f)
        mLength1= SizeUtils.dp2px(8f) + mStrokeWidth
        mLength2  =mLength1  +SizeUtils.dp2px(4f)

        mRectFArc = RectF()
        mRectFInnerArc = RectF()
        mRectText = Rect()

    }

    /**
     * 测量
     * */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        mWidth = widthSize
        mHeight = heightSize

        setMeasuredDimension(widthSize, heightSize)

        mPadding =  max(max(paddingLeft, paddingTop), max(paddingRight, paddingBottom))

        setPadding(mPadding,mPadding,mPadding,mPadding)



    }


}