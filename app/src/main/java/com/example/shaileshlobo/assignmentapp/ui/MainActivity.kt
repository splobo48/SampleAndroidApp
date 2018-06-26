package com.example.shaileshlobo.assignmentapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.shaileshlobo.assignmentapp.ui.addresslocation.AddressLocationFragment
import com.example.shaileshlobo.assignmentapp.ui.customerlisting.CustomerListingFragment
import com.example.shaileshlobo.assignmentapp.ui.selectedcustomers.SelectedCustomersListing
import kotlinx.android.synthetic.main.activity_main.*
import com.example.shaileshlobo.assignmentapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        setSupportActionBar(toolbar_activity_main);
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false);
    }

    private fun initUi() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CustomerListingFragment())
                .addToBackStack(null)
                .commit()

        tv_toolbar.text = getString(R.string.main_activity_toolbar_title)
    }

    override fun onBackPressed() {

        //todo solve the below multidex issue
        /*
        * java.lang.NoSuchMethodError: No virtual method findFragmentById(I)
        * Landroid/support/v4/app/Fragment; in class Landroid/support/v4/app/FragmentManager;
         * or its super classes (declaration of 'android.support.v4.app.FragmentManager'
         * appears in split_lib_dependencies_apk.apk:classes22.dex)*/
        try{
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            if (fragment is CustomerListingFragment){
                finish()
                return
            }
            else if (fragment is SelectedCustomersListing){
                tv_toolbar.text = getString(R.string.main_activity_toolbar_title)
            }
            else if (fragment is AddressLocationFragment){
                tv_toolbar.text = getString(R.string.customer_details_toolbar_title)
            }
        }
        catch (e : Exception){
            e.printStackTrace()
        }
        super.onBackPressed()
    }
}

/*
*
* About :->
* Give Credits for icon.
*
* unchecked icon
* <div>Icons made by <a href="https://www.flaticon.com/authors/vaadin" title="Vaadin">Vaadin</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
*
*<div>Icons made by <a href="https://www.flaticon.com/authors/stephen-hutchings" title="Stephen Hutchings">Stephen Hutchings</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
* checked icon
*
* */