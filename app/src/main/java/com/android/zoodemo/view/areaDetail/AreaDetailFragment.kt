package com.android.zoodemo.view.areaDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.zoodemo.R
import com.android.zoodemo.databinding.FragmentAreaDetailBinding
import com.android.zoodemo.util.GlideUtil
import com.android.zoodemo.util.viewBinding
import com.android.zoodemo.view.plant.PlantListAdapter
import com.android.zoodemo.data.model.PlantModel

class AreaDetailFragment : Fragment(R.layout.fragment_area_detail){
    private var mPlantList: List<PlantModel> = mutableListOf()
    private val mBinding by viewBinding(FragmentAreaDetailBinding::bind)
    private val arg : AreaDetailFragmentArgs by navArgs()
    private val plantListAdapter = PlantListAdapter { plantItemClick(it) }
    private val mNavController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mPlantList = mZooViewModel.getPlantListByKey(arg.area.eName)
        initView()

    }

    private fun initView() {
        GlideUtil.load(arg.area.ePicURL, mBinding.ivAreaDetailImage, android.R.drawable.stat_sys_download)
        mBinding.tvAreaDetailInfo.text = arg.area.eInfo
        mBinding.tvAreaDetailCategory.text = arg.area.eCategory
        mBinding.tvAreaDetailMemo.text = if(arg.area.eMemo .isNullOrBlank()) {
            getString(R.string.item_view_area_tv_no_memo)
        } else {
            arg.area.eMemo
        }

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mBinding.rvPlantList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        mBinding.rvPlantList.layoutManager = layoutManager
        plantListAdapter.apply { mData = mPlantList }
        mBinding.rvPlantList.adapter = plantListAdapter

        mBinding.tvAreaDetailLink.setOnClickListener {
            openUrlBrowser(arg.area.eURL)
        }

    }

    private fun openUrlBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun plantItemClick(plant: PlantModel) {
        mNavController.currentDestination?.getAction(R.id.action_areaDetailFragment_to_plantDetailFragment)?.let {
            mNavController.navigate(AreaDetailFragmentDirections.actionAreaDetailFragmentToPlantDetailFragment(plant, plant.fNameCh))
        }
    }
}