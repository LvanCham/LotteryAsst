package com.cham.lotteryasst.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cham.baselib.BASE_URL
import com.cham.baselib.net.RetrofitFactory
import com.cham.lotteryasst.api.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception

/**
 * Hello World
 * Date: 2019/7/29
 * Author: Cham
 */
class SimpleViewModel : ViewModel() {

    private val error by lazy { MutableLiveData<Exception>() }

    private val finally by lazy { MutableLiveData<Int>() }

    override fun onCleared() {
        super.onCleared()
    }
    fun launchUI(block:suspend   CoroutineScope.()->Unit) =viewModelScope.launch {
        try {
            withTimeout(10000){
                block()
            }
        }catch (e:Exception){
            error.value = e
        }finally {
            finally.value = 200
        }

    }


     fun  getData () = launchUI {
         var AAAA =  RetrofitFactory.instance(BASE_URL).create(ApiService::class.java).getAncientPoetry2()
         Log.e("job4", AAAA.content)

     }



}