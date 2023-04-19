package com.android.zoodemo.view.areaList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.android.zoodemo.R
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.data.model.AreaResponseModel
import com.android.zoodemo.data.network.NetworkState
import com.android.zoodemo.databinding.FragmentAreaListBinding
import com.android.zoodemo.view.areaList.AreaListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AreaListFragment : Fragment(R.layout.fragment_area_list) {

    private val viewModel: AreaListViewModel by viewModels()
    private var _binding: FragmentAreaListBinding? = null
    private val binding get() = _binding!!
    private val areaListAdapter: AreaListAdapter by lazy { AreaListAdapter() }
    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAreaList.adapter = areaListAdapter
        areaListAdapter.onItemClick = {
            navToDetail(it)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        NetworkState.Loading -> {

                        }
                        is NetworkState.Success<AreaResponseModel> -> {
                            areaListAdapter.submitList( it.data.areaDataModel.areaList )
                        }
                        is NetworkState.Error -> {

                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun navToDetail(area: AreaModel) {
        navController.currentDestination?.getAction(R.id.action_areaListFragment_to_areaDetailFragment)
            ?.let {
                navController.navigate(
                    AreaListFragmentDirections.actionAreaListFragmentToAreaDetailFragment(
                        area,
                        area.eName
                    )
                )
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}