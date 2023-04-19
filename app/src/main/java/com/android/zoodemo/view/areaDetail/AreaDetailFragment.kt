package com.android.zoodemo.view.areaDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AreaDetailFragment : Fragment(R.layout.fragment_area_detail) {
    private val mBinding by viewBinding(FragmentAreaDetailBinding::bind)
    private val arg: AreaDetailFragmentArgs by navArgs()
    private val plantListAdapter = PlantListAdapter { plantItemClick(it) }
    private val mNavController by lazy { NavHostFragment.findNavController(this) }
    private val plantPageViewModel: PlantPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantPageViewModel.getPlantPagingDataByAreaName(arg.area.eName).collectLatest {
                    plantListAdapter.submitData(it)
                }
            }
        }
        initView()

    }

    private fun initView() {
        GlideUtil.load(
            arg.area.ePicURL,
            mBinding.ivAreaDetailImage,
            android.R.drawable.stat_sys_download
        )
        mBinding.tvAreaDetailInfo.text = arg.area.eInfo
        mBinding.tvAreaDetailCategory.text = arg.area.eCategory
        mBinding.tvAreaDetailMemo.text = arg.area.eMemo.ifBlank {
            getString(R.string.item_view_area_tv_no_memo)
        }

        mBinding.tvAreaDetailLink.setOnClickListener {
            openUrlBrowser(arg.area.eURL)
        }

        mBinding.rvPlantList.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        mBinding.rvPlantList.adapter = plantListAdapter


    }

    private fun openUrlBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun plantItemClick(plant: PlantModel) {
        mNavController.currentDestination?.getAction(R.id.action_areaDetailFragment_to_plantDetailFragment)
            ?.let {
                mNavController.navigate(
                    AreaDetailFragmentDirections.actionAreaDetailFragmentToPlantDetailFragment(
                        plant,
                        plant.fNameCh
                    )
                )
            }
    }
}