package com.bangkit.usergithub.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.usergithub.data.User
import com.bangkit.usergithub.data.response.ResponseApi
import com.bangkit.usergithub.data.response.ResponseUser
import com.bangkit.usergithub.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private const val ERROR = "Failure"
        private const val USERNAME = "a"
    }

    init {
        setUser(USERNAME)
    }


    val _listUser = MutableLiveData<ArrayList<User>>()
    val listUser: LiveData<ArrayList<User>> = _listUser

    private fun setUser(qq: String) {
        val client = ApiConfig.getApiService().getUsers(USERNAME)
        client.enqueue(object : Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                if (response.isSuccessful) {
                    _listUser.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.d(ERROR, t.message.toString())
            }
        })
    }

    fun getUser(): LiveData<ArrayList<User>> = listUser

    fun getSearchUser(qq: String) {
        val client = ApiConfig.getApiService().getSeachUsers(qq)
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                if (response.isSuccessful) {
                    val listUser = response.body()?.items
                    if (listUser != null) {
                        _listUser.postValue(listUser as ArrayList<User>?)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseUser>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message}")
            }
        })
    }

}