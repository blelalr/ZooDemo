package com.android.zoodemo.view.area

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.zoodemo.R
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.databinding.FragmentAreaListBinding
import com.android.zoodemo.util.viewBinding
import com.android.zoodemo.viewmodel.ZooViewModel

class AreaListFragment : Fragment(R.layout.fragment_area_list) {
    private val mZooViewModel: ZooViewModel by activityViewModels()
    private val mAreaListAdapter = AreaListAdapter { areaItemClick(it) }
    private val mBinding by viewBinding(FragmentAreaListBinding::bind)
    private val mNavController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mBinding.rvAreaList.layoutManager = layoutManager
        mBinding.rvAreaList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        mZooViewModel.mAreaList.observe(viewLifecycleOwner, Observer {
            mAreaListAdapter.apply { mData = it.areaDataModel.areaList }
            mBinding.rvAreaList.adapter = mAreaListAdapter
        })
    }

    private fun areaItemClick(area: AreaModel) {
        mNavController.currentDestination?.getAction(R.id.action_areaListFragment_to_areaDetailFragment)?.let {
            mNavController.navigate(AreaListFragmentDirections.actionAreaListFragmentToAreaDetailFragment(area, area.eName))
        }
    }

}