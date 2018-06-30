package com.example.shaileshlobo.assignmentapp.data.remote

import com.example.shaileshlobo.assignmentapp.di.component.DaggerAppComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory





/**
 * Created by spl on 20/6/18.
 */
object RetrofitClient{

    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun getRetrofitInstance(): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }

}