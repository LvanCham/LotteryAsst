package com.cham.lotteryasst.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.cham.lotteryasst.R





/**
 * Hello World
 * Date: 2019/7/8
 * Author: Cham
 */
class LoadingButton : AppCompatTextView {

    private lateinit var mContext: Context
    private lateinit var mProgressDrawable: CircularProgressDrawable
    private lateinit var mPaint:Paint
    private  lateinit var  mBounds:Rect
    private lateinit var mShrinkAnimator: ValueAnimator
    private val mShrinkDuration: Int = 450
    private   var  mLoadingSize = 20


    //文字的宽度
    private   var  mTextWidth:Float =0f
    private var mTextSize :Float =0f

    //左右边距
    private var mLeftRightPadding  =0f
    private var mTopBottomPadding = 0f

    //半径
    private var mRadiu  = 0f
    //默认半径
    private var mDefaultRadiu = 40f
    // 记录中间矩形的宽度值
    private var rectWidth =0f



    private val mRootViewSizeSaved = intArrayOf(0, 0)

    constructor(context: Context): super(context){
        mContext = context
    }


    constructor(context: Context, attrs: AttributeSet?):super(context,attrs){
        mContext = context
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton)

        mLeftRightPadding = typedArray.getDimensionPixelOffset(R.styleable.LoadingButton_contentPaddingLR,10).toFloat()
        mTopBottomPadding = typedArray.getDimensionPixelOffset(R.styleable.LoadingButton_contentPaddingTB,10).toFloat()
        mRadiu =typedArray.getDimensionPixelOffset(R.styleable.LoadingButton_radiu,40).toFloat()


        inintView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr:Int) :super(context,attrs, defStyleAttr){}

    fun inintView(){
        mProgressDrawable = CircularProgressDrawable(mContext)
        mProgressDrawable.setColorSchemeColors(textColors.defaultColor)
        mProgressDrawable.setBounds(0,0,80,80)
        mBounds=  mProgressDrawable.copyBounds()
        setCompoundDrawables(mProgressDrawable, null, null, null)
        mProgressDrawable.strokeWidth = 10f

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        ProgressStop()
    }

    fun ProgressStart(){
        mProgressDrawable.start()
    }
    fun ProgressStop(){
        mProgressDrawable.stop()
    }

    /**
     * 收缩后的尺寸（正方形）
     */

    fun getShrinkSize():Int{
        return Math.max(Math.min(mRootViewSizeSaved[0], mRootViewSizeSaved[1]), getLoadingEndDrawableSize())
    }

    fun getLoadingEndDrawableSize(): Int {
        return mLoadingSize
    }
    /**
     * 设置收缩动画，主要用来收缩和恢复布局的宽度，动画开始前会保存一些收缩前的参数（文字，其他Drawable等）
     */
    fun  setUpShrinkAnimator(){
        mShrinkAnimator = ValueAnimator.ofFloat(0f,1f)
        mShrinkAnimator.duration = mShrinkDuration.toLong()


        mShrinkAnimator.addUpdateListener { animation ->

            layoutParams.width =
                ((getShrinkSize() - mRootViewSizeSaved[0]) * animation.animatedValue as Float + mRootViewSizeSaved[0]).toInt()
            layoutParams.height =
                ((getShrinkSize() - mRootViewSizeSaved[1]) * animation.animatedValue as Float + mRootViewSizeSaved[1]).toInt()
            requestLayout()
        }

    }

    /**
     * 计算需要的位移
     * */
    fun calcOffset():Float{
        return (width - (compoundPaddingStart+ getTextWidth() )) / 2
    }

    /**
     *  计算文字的长度
     * */
    fun getTextWidth():Float{
        return  paint.measureText(text.toString())
    }


    /**
     * 测量
     * */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)


        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        //获得字体大小
        mTextSize = paint.textSize
        //用于保存最终尺寸
        var resultW = widthSize.toFloat()
        var resultH = heightSize.toFloat()

        // contentW contentH 用于确定中间矩形的尺寸
        var contentW = 0f
        var contentH = 0f

        if(widthMode == MeasureSpec.AT_MOST){
            mTextWidth=paint.measureText(text.toString())

            contentW += mTextWidth + mLeftRightPadding * 2 + mRadiu * 2
        }

        if ( heightMode == MeasureSpec.AT_MOST ) {
            contentH += mTopBottomPadding * 2 + mTextSize
            resultH = if( contentH < heightSize) contentH else heightSize.toFloat()
        }

        resultW = if (resultW < 2 * mRadiu) (2 * mRadiu) else resultW
        resultH = if (resultH < 2 * mRadiu) (2 * mRadiu) else resultH

        // 修整圆形的半径
        mRadiu = resultH / 2
        // 记录中间矩形的宽度值
        rectWidth = resultW - 2 * mRadiu

        setMeasuredDimension(resultW.toInt(), resultH.toInt())

    }





    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val offsetX = calcOffset()
        mProgressDrawable.setBounds(offsetX.toInt(),mBounds.top, (mBounds.right+offsetX).toInt(),mBounds.bottom)
        //计算画布向左平移的距离
        val  tranX = (mBounds.width() + compoundDrawablePadding) / 2
        canvas!!.translate((-tranX).toFloat(), 0f)





    }




}