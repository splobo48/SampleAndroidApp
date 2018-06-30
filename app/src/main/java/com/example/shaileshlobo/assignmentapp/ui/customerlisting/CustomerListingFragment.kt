package com.example.shaileshlobo.assignmentapp.ui.customerlisting


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.shaileshlobo.assignmentapp.ui.MainAcitivityViewModel
import com.example.shaileshlobo.assignmentapp.R
import com.example.shaileshlobo.assignmentapp.ui.customerlisting.adapter.CustomersGridViewAdapter
import com.example.shaileshlobo.assignmentapp.ui.customerlisting.adapter.CustomersGridViewAdapter.OnCheckChangedInterface
import com.example.shaileshlobo.assignmentapp.ui.selectedcustomers.SelectedCustomersListing
import kotlinx.android.synthetic.main.fragment_customer_listings.*


/**
 * A simple [Fragment] subclass.
 * Use the [CustomerListingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerListingFragment : Fragment() {

    private lateinit var mContext: Context;
    private lateinit var model: MainAcitivityViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_customer_listings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        model = ViewModelProviders.of(mContext as AppCompatActivity)
                .get(MainAcitivityViewModel::class.java)
        model.getCustomerListing().observe(this, Observer { userModels ->
            if (userModels?.size == 0){
                tv_show_detials_customer_listing_view.visibility = View.GONE;
            }
            else{
                tv_show_detials_customer_listing_view.visibility = View.VISIBLE;
                gridlayout_customer_listing_view.adapter = CustomersGridViewAdapter(mContext,
                        userModels , OnCheckChangedInterface { idOfCustomer, isChecked ->
                    model.updateCustomerSelection(idOfCustomer, isChecked);
                })
            }
        })
    }

    private fun initViews(){

        tv_show_detials_customer_listing_view.visibility = View.GONE;
        tv_show_detials_customer_listing_view.setOnClickListener{
            val selectedCustomers = model.getSelectedCustomers()
            if (selectedCustomers.size == 0){
                Toast.makeText(mContext, mContext.getString(R.string.no_cust_selected),
                        Toast.LENGTH_SHORT).show()
            }
            else{
                (mContext as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SelectedCustomersListing())
                        .addToBackStack(null)
                        .commit()
                activity?.findViewById<TextView>(R.id.tv_toolbar)?.text =
                        getString(R.string.customer_details_toolbar_title)
            }

        }
    }

}