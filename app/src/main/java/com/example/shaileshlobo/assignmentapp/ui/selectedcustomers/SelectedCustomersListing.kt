package com.example.shaileshlobo.assignmentapp.ui.selectedcustomers


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.shaileshlobo.assignmentapp.ui.MainAcitivityViewModel

import com.example.shaileshlobo.assignmentapp.R
import com.example.shaileshlobo.assignmentapp.ui.addresslocation.AddressLocationFragment
import com.example.shaileshlobo.assignmentapp.ui.selectedcustomers.adapter.SelectedCustomerAdapter
import kotlinx.android.synthetic.main.fragment_selected_customers_listing.*


/**
 * A simple [Fragment] subclass.
 */
class SelectedCustomersListing : Fragment() {

    private var mContext: Context? = null;

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_customers_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        val model = ViewModelProviders.of(mContext as AppCompatActivity).get(MainAcitivityViewModel::class.java)
        val selectedCustomers = model.getSelectedCustomers()
        rv_selected_customers_screen.adapter = SelectedCustomerAdapter(selectedCustomers){
            userModel ->
            (mContext as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,
                            AddressLocationFragment.getInstance(userModel))
                    .addToBackStack(null)
                    .commit()

            activity?.findViewById<TextView>(R.id.tv_toolbar)?.text = getString(R.string.map_view_toolbar_title)
        }
    }

    private fun initView() {
        rv_selected_customers_screen.layoutManager = LinearLayoutManager(activity);
    }

}
