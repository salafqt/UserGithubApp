package com.bangkit.usergithub.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.usergithub.data.User
import com.bangkit.usergithub.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    var _listFollowing = MutableLiveData<ArrayList<User>>()
    val listFollowing: LiveData<ArrayList<User>> = _listFollowing

    fun setFollowing(qq: String){
        val client = ApiConfig.getApiService().getFollowingUser(qq)
        client.enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful){
                    _listFollowing.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    fun getFollowing(): LiveData<ArrayList<User>> = listFollowing

}