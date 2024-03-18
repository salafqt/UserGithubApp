package com.bangkit.usergithub.data.retrofit

import User_Github.app.BuildConfig
import com.bangkit.usergithub.data.User
import com.bangkit.usergithub.data.response.ResponseApi
import com.bangkit.usergithub.data.response.ResponseDetail
import com.bangkit.usergithub.data.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: token ${BuildConfig.API_TOKEN}")

    @GET("search/users")
    fun getUsers(
        @Query("q") query: String
    ) : Call<ResponseApi>

    @GET("search/users")
    fun getSeachUsers(
        @Query("q") query: String
    ) : Call<ResponseUser>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ) : Call<ResponseDetail>

    @GET("users/{username}/followers")
    fun getFollowerUser(
        @Path("username") username: String
    ) : Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getFollowingUser(
        @Path("username") username: String
    ) : Call<ArrayList<User>>

}