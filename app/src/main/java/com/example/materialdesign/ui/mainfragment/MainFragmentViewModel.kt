package com.example.materialdesign.ui.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.model.repository.AppState
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.model.repository.PDORetrofitImpl
import com.example.materialdesign.model.repository.PDOServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val pdoRetrofitImpl: PDORetrofitImpl = PDORetrofitImpl()
) : ViewModel() {
    fun getData(): LiveData<AppState> = liveData
    fun sendRequest() {
        liveData.postValue(AppState.Loading(null))
        pdoRetrofitImpl.getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(object : Callback<PDOServerResponse> {
                override fun onResponse(
                    call: Call<PDOServerResponse>,
                    response: Response<PDOServerResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        response.body()?.let {
                            liveData.postValue(AppState.Success(it))
                        }

                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveData.value =
                                AppState.Error(Throwable("Неопознанная ошибка"))
                        } else {
                            liveData.value =
                                AppState.Error(Throwable(message))
                        }
                    }

                }

                override fun onFailure(call: Call<PDOServerResponse>, t: Throwable) {
                    liveData.value = AppState.Error(t)
                }
            }
            )
    }
}