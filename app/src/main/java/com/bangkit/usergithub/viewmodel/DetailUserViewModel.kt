package com.bangkit.usergithub.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.usergithub.data.response.ResponseDetail
import com.bangkit.usergithub.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val _user = MutableLiveData<ResponseDetail>()
    val user: LiveData<ResponseDetail> = _user

    fun setDetailUser(qq: String) {
        val client = ApiConfig.getApiService().getDetailUser(qq)
        client.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (response.isSuccessful) {
                    _user.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDetailUser(): LiveData<ResponseDetail> = user
}