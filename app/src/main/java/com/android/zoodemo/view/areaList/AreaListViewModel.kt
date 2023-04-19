package com.android.zoodemo.view.areaList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.zoodemo.data.model.AreaModel
import com.android.zoodemo.data.model.AreaResponseModel

import com.android.zoodemo.data.network.NetworkState
import com.android.zoodemo.data.repository.ZooRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaListViewModel @Inject constructor(private val zooRepo: ZooRepo) : ViewModel() {
    private var _uiState: MutableStateFlow<NetworkState<AreaResponseModel>> = MutableStateFlow(NetworkState.Idle)
    val uiState: StateFlow<NetworkState<AreaResponseModel>> get() = _uiState

    init {
        Log.d(TAG, ": init ViewModel")
        fetchAreaData()
    }

    private fun fetchAreaData() {
        viewModelScope.launch {
            _uiState.value = NetworkState.Loading
            try {

                zooRepo.fetchAreaData().collect {
                    _uiState.value = NetworkState.Success<AreaResponseModel>(it)
                }
//                zooRepo.fetchAreaData().map {
//                    it.areaDataModel.areaList
//                }.distinctUntilChanged().collectLatest {
//
//                }

            } catch (exception: Exception) {
                _uiState.value = NetworkState.Error(exception.message.toString())
            }
        }
    }

    companion object {
        private const val TAG = "AreaListViewModel"
    }
}