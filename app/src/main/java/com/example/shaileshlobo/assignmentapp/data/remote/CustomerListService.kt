package com.example.shaileshlobo.assignmentapp.data.remote

import com.example.shaileshlobo.assignmentapp.data.models.UserModel
import io.reactivex.Observable
import retrofit2.http.GET



/**
 * Created by spl on 20/6/18.
 */
interface CustomerListService {

    @GET("users")
    fun getCustomerListing(): Observable<List<UserModel>>

}