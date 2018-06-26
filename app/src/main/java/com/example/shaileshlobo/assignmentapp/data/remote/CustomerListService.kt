package com.example.shaileshlobo.assignmentapp.data.remote

import com.example.shaileshlobo.assignmentapp.data.models.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by spl on 20/6/18.
 */
interface CustomerListService {


    @GET("users")
    fun listCustomers(): Call<List<UserModel>>
}