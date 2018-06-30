package com.example.shaileshlobo.assignmentapp.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.example.shaileshlobo.assignmentapp.data.models.UserModel
import com.example.shaileshlobo.assignmentapp.data.remote.CustomerListService
import com.example.shaileshlobo.assignmentapp.data.remote.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by spl on 20/6/18.
 */
class MainAcitivityViewModel(application: Application): AndroidViewModel(application){

    private var customerList : MutableLiveData<List<UserModel>>? = null


    /*
    *   Get list of customers to show
    *
    * */
    fun getCustomerListing(): MutableLiveData<List<UserModel>> {
        if (customerList == null) {
            customerList = MutableLiveData()
            fetchCustomerListingApi()
        }
        return customerList as MutableLiveData<List<UserModel>>
    }


    /*
    *
    * fetch rest api from server
    *
    * */
    private fun fetchCustomerListingApi(){
        val customerServiceApi = RetrofitClient.getRetrofitInstance().create(CustomerListService::class.java)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(customerServiceApi.getCustomerListing()
                .subscribeOn(Schedulers.io())/*
                .map { customers -> customers }*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ customers ->
                    customerList?.value = customers
                }, {
                    throwable ->
                    Toast.makeText(getApplication()," error occurred "+throwable.localizedMessage
                             ,Toast.LENGTH_SHORT).show()
                        }))
    }

    /*
    *  Update Customer Selection
    * */
    fun updateCustomerSelection(idOfCustomer: Long, checked: Boolean) {
        val items = customerList?.value

        if (items!= null){
            for (item in items){
                if (item.id  == idOfCustomer){
                    item.isSelected = checked
                }
            }
        }
    }


    /*
    *
    * get list of selected customers
    *
    * */
    fun getSelectedCustomers(): ArrayList<UserModel> {
        val allItems = customerList?.value
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

    /*
    *
    *  Get Customer by Id
    *
    * */
    fun getCustomerById(customerId: Long?) : UserModel? {
        val allItems = customerList?.value

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