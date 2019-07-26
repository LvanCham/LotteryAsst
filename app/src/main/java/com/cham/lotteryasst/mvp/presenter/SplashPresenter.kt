package com.cham.lotteryasst.mvp.presenter

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.cham.baselib.base.BasePresenter
import com.cham.baselib.di.scope.ActivtyScope
import com.cham.baselib.net.getApiService
import com.cham.lotteryasst.api.ApiService
import com.cham.lotteryasst.entity.DailyEnglishEntity
import com.cham.lotteryasst.entity.PoetryEntity
import com.cham.lotteryasst.mvp.contract.SplashContract
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.flatMapSequence
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers.io
import okhttp3.ResponseBody
import java.util.function.Consumer
import javax.inject.Inject

/**
 * Hello World
 * Date: 2019/6/29
 * Author: Cham
 */

@ActivtyScope
class SplashPresenter
@Inject constructor(rootView: SplashContract.SplashView, model:SplashContract.SplashModel): BasePresenter<SplashContract.SplashView,
            SplashContract.SplashModel>(rootView,model) {

    /**
     * 去吧 皮卡丘
     * */
    fun gogo(){
       mModel.getPoetry()
         .subscribeOn(io())
         .observeOn(mainThread())
         .subscribe(object : Observer<PoetryEntity>{
             override fun onNext(t: PoetryEntity) {
                 mRootView.showTest(t.content)
             }
             override fun onComplete() {}
             override fun onSubscribe(d: Disposable) {}
             override fun onError(e: Throwable) {
                 mRootView.showTest(mModel.getTest())
             }
         })


        mModel.getDailyEn()
            .subscribeOn(io())
            .observeOn(mainThread())
            .subscribe(object : Observer<DailyEnglishEntity>{
                override fun onNext(t: DailyEnglishEntity) {
                    mRootView.showDailyEn(t)
                }
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {

                }
            })











    }


}



