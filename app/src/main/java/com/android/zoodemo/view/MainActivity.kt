package com.android.zoodemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.android.zoodemo.R
import com.android.zoodemo.databinding.ActivityMainBinding
import com.android.zoodemo.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mBinding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        setupToolBar()
    }

    private fun setupToolBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        mBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}