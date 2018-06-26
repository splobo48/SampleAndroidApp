package com.example.shaileshlobo.assignmentapp.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.example.shaileshlobo.assignmentapp.CustomApplicaton
import com.example.shaileshlobo.assignmentapp.R
import com.example.shaileshlobo.assignmentapp.data.models.UserModel
import com.example.shaileshlobo.assignmentapp.data.remote.CustomerListService
import com.example.shaileshlobo.assignmentapp.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by spl on 20/6/18.
 */
class MainAcitivityViewModel(application: Application): AndroidViewModel(application){

    private lateinit var customerList : MutableLiveData<List<UserModel>>

    fun getUsers(): MutableLiveData<List<UserModel>> {
        if (customerList == null) {
            customerList = MutableLiveData<List<UserModel>>()
            loadCustomerList()
        }
        return customerList
    }

    private fun loadCustomerList() {
        val application = getApplication<CustomApplicaton>()
        // Do an asynchronous operation to fetch users.
        val service = RetrofitClient.getRetrofitInstance()
                .create(CustomerListService::class.java)

        val call = service.listCustomers()

        call.enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(call: Call<List<UserModel>>,
                                    response: Response<List<UserModel>>) {
                val body = response.body()
                if (body != null && body.isNotEmpty()){
                    customerList.value = body
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
             Toast.makeText(getApplication(),
                        application.getString(R.string.api_called_failed),
                        Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun updateCustomerSelection(idOfCustomer: Long, checked: Boolean) {
        val items = customerList.value

        if (items!= null){
            for (item in items){
                if (item.id  == idOfCustomer){
                    item.isSelected = checked
                }
            }
        }
    }

    fun getSelectedCustomers(): ArrayList<UserModel> {
        val allItems = customerList.value
        val selectedItems = ArrayList<UserModel>();

        if (allItems!= null){
            for (item in allItems){
                if (item.isSelected){
                    selectedItems.add(item);
                }
            }
        }

        return selectedItems;
    }

    fun getCustomerById(customerId: Long?) : UserModel? {
        val allItems = customerList.value

        if (allItems!= null){
            for (item in allItems){
                if (item.id == customerId){
                    return item
                }
            }
        }
        return null;

    }
}