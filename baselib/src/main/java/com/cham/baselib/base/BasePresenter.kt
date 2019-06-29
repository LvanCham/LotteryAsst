package com.cham.baselib.base

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */
open class BasePresenter <V: IVew,M:IModel> :IPresenter {

    lateinit protected var mModel: M
    lateinit protected  var mRootView: V


    constructor(rootView:V,model:M ){
        mModel = model
        mRootView =rootView
        onStart()
    }
    constructor(rootView: V){
        this.mRootView = rootView
        onStart()
    }
    constructor(){
        onStart()
    }


    /**
     * 預留 做些初始化
     * */
    override fun onStart() {

    }

    override fun onDestroy() {

        mModel.onDestroy()
    }

}