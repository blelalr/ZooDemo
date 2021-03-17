package com.android.zoodemo.view.plant

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.zoodemo.R
import com.android.zoodemo.databinding.FragmentPlantDetailBinding
import com.android.zoodemo.util.GlideUtil
import com.xnbay.xnfun.tv.delegate.viewBinding

class PlantDetailFragment : Fragment(R.layout.fragment_plant_detail) {
    private val mBinding by viewBinding(FragmentPlantDetailBinding::bind)
    private val arg : PlantDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        GlideUtil.load(arg.plant.fPic01URL, mBinding.ivPlantDetailImage, android.R.drawable.stat_sys_download)
        mBinding.tvPlantDetailNameCh.text = arg.plant.fNameCh
        mBinding.tvPlantDetailNameEn.text = arg.plant.fNameEn

        mBinding.tvPlantDetailAlsoKnow.text = "別名\n${arg.plant.fAlsoKnown}"
        mBinding.tvPlantDetailBrief.text = "簡介\n${arg.plant.fBrief}"

        mBinding.tvPlantDetailFeature.text = "識別方法\n${arg.plant.fFeature}"
        mBinding.tvPlantDetailFunction.text = "功能\n${arg.plant.fFunction_application}"
        mBinding.tvPlantDetailUpdate.text = "更新時間\n${arg.plant.fUpdate}"
    }
}