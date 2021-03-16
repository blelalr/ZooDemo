package com.android.zoodemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.android.zoodemo.databinding.ActivityMainBinding

import com.android.zoodemo.data.network.ApiResponse
import com.android.zoodemo.viewmodel.ZooViewModel
import com.xnbay.xnfun.tv.delegate.viewBinding

class MainActivity : AppCompatActivity() {
    private val mBinding by viewBinding(ActivityMainBinding::inflate)
    private val mZooViewModel: ZooViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        getDataFromNetWork()
    }

    private fun getDataFromNetWork() {
//        zooViewModel.getAreaList().observe(this, Observer {
//            when(it){
//                is ApiResponse.Success -> {
//
//                    Log.d("esther", "Success "+ it.data)
//                }
//                is ApiResponse.Error -> {
//                    Log.d("esther", "Error ")
//                }
//                is ApiResponse.Exception -> {
//                    Log.d("esther", "Exception ")
//                }
//            }
//        })

        mZooViewModel.getPlantMap().observe(this, Observer {
            when(it){
                is ApiResponse.Success -> {
                    for (entry in it.data) {
                        Log.d("esther", "Success ${entry.key}: ${entry.value.size}")
                    }
                }
                is ApiResponse.Error -> {
                    Log.d("esther", "Error ")
                }
                is ApiResponse.Exception -> {
                    Log.d("esther", "Exception ")
                }
            }
        })


    }
}