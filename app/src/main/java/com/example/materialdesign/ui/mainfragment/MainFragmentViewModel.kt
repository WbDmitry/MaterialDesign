package com.example.materialdesign.ui.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.AppState
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.repository.PDORetrofitImpl
import com.example.materialdesign.repository.PDOServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainFragmentViewModel(
    private val liveDate: MutableLiveData<AppState> = MutableLiveData(),
    private val pdoRetrofitImpl: PDORetrofitImpl = PDORetrofitImpl()
) : ViewModel() {
    fun getData(): LiveData<AppState> = liveDate
    fun sendRequest() {
        liveDate.postValue(AppState.Loading(null))
        pdoRetrofitImpl.getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(object : Callback<PDOServerResponse> {
                override fun onResponse(
                    call: Call<PDOServerResponse>,
                    response: Response<PDOServerResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        response.body()?.let {
                            liveDate.postValue(AppState.Success(it))
                        }

                    } else {
                        // TODO HW вывести ошибку
                    }

                }

                override fun onFailure(call: Call<PDOServerResponse>, t: Throwable) {
                    // TODO HW
                }
            }
            )
    }
}