package com.android.zoodemo.view.areaDetail

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.zoodemo.data.model.PlantModel
import com.android.zoodemo.data.network.ApiConstant
import com.android.zoodemo.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class PlantPagingSource(
    private val apiService: ApiService,
    private val areaName: String
) : PagingSource<Int, PlantModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantModel> {
        val offset = params.key ?: ApiConstant.PLANT_PAGE_START_KEY
        return try {
            val response =
                apiService.getPlantData(offset = offset, limit = ApiConstant.PLANT_LOAD_SIZE)

            val plants = response.plantDataModel.plantList.filter { plantModel -> plantModel.fLocation.contains(areaName) }

            LoadResult.Page(
                data = plants,
                prevKey = if (offset == ApiConstant.PLANT_PAGE_START_KEY) null else offset - ApiConstant.PLANT_LOAD_SIZE,
                nextKey = if (plants.isEmpty()) null else offset + ApiConstant.PLANT_LOAD_SIZE
            )
        } catch (exception: IOException) {
             return  LoadResult.Error(exception)
        } catch (exception: HttpException) {
             return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlantModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ApiConstant.PLANT_LOAD_SIZE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ApiConstant.PLANT_LOAD_SIZE)
        }
    }

}