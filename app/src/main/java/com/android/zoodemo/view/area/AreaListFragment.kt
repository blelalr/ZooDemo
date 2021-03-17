package com.android.zoodemo.view.area

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.zoodemo.R
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.databinding.FragmentAreaListBinding
import com.android.zoodemo.viewmodel.ZooViewModel
import com.xnbay.xnfun.tv.delegate.viewBinding

class AreaListFragment : Fragment(R.layout.fragment_area_list), AreaClickListener {
    private val mZooViewModel: ZooViewModel by activityViewModels()
    private val mAreaListAdapter = AreaListAdapter(this)
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

    override fun onAreaItemClick(area: AreaModel) {
        mNavController.navigate(
            AreaListFragmentDirections.actionAreaListFragmentToAreaDetailFragment(area, area.eName)
        )
    }

}