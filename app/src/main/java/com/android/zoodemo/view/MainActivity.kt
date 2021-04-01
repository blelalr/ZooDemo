package com.android.zoodemo.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.android.zoodemo.R
import com.android.zoodemo.data.network.ApiResponse
import com.android.zoodemo.databinding.ActivityMainBinding
import com.android.zoodemo.util.viewBinding
import com.android.zoodemo.viewmodel.ZooViewModel


class MainActivity : AppCompatActivity() {
    private val mBinding by viewBinding(ActivityMainBinding::inflate)
    private val mZooViewModel: ZooViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        setupToolBar()
        if(mZooViewModel.mAreaList.value == null || mZooViewModel.mPlantMap.value == null) {
            getDataFromNetWork()
        }
    }

    private fun setupToolBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        mBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun getDataFromNetWork() {
        mBinding.progressLoading.visibility = VISIBLE
        mZooViewModel.getAreaList().observe(this, Observer {
            mBinding.progressLoading.visibility = GONE
            when(it){
                is ApiResponse.Success -> {
                    mBinding.containerMain.visibility = VISIBLE
                    mBinding.tvError.visibility = GONE
                    mZooViewModel.mAreaList.postValue(it.data)
                }
                is ApiResponse.Error -> {
                    mBinding.containerMain.visibility = GONE
                    mBinding.tvError.visibility = VISIBLE
                    mBinding.tvError.text = "API ERROR -- error code: ${it.errno} message: ${it.msg}"
                }
                is ApiResponse.Exception -> {
                    mBinding.containerMain.visibility = GONE
                    mBinding.tvError.visibility = VISIBLE
                    mBinding.tvError.text = "Exception -- exception: ${it.exception.message}"
                }
            }
        })
        mZooViewModel.getPlantMap().observe(this, Observer {
            mBinding.progressLoading.visibility = GONE
            when(it){
                is ApiResponse.Success -> {
                    mBinding.containerMain.visibility = VISIBLE
                    mBinding.tvError.visibility = GONE
                    mZooViewModel.mPlantMap.postValue(it.data)
                }
                is ApiResponse.Error -> {
                    mBinding.containerMain.visibility = GONE
                    mBinding.tvError.visibility = VISIBLE
                    mBinding.tvError.text = "API ERROR -- error code: ${it.errno} message: ${it.msg}"
                }
                is ApiResponse.Exception -> {
                    mBinding.containerMain.visibility = GONE
                    mBinding.tvError.visibility = VISIBLE
                    mBinding.tvError.text = "Exception -- exception: ${it.exception.message}"
                }
            }
        })
    }

}