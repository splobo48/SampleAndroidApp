package com.example.shaileshlobo.assignmentapp.ui.selectedcustomers.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shaileshlobo.assignmentapp.utils.AppSpecificUtils

import com.example.shaileshlobo.assignmentapp.R
import com.example.shaileshlobo.assignmentapp.data.models.UserModel
import kotlinx.android.synthetic.main.row_selected_customers_view.view.*

/**
 * Created by spl on 21/6/18.
 */

class SelectedCustomerAdapter(private val list: List<UserModel>,
                              private val listener: (UserModel) -> Unit)
    : RecyclerView.Adapter<SelectedCustomerAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SelectedCustomerAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_selected_customers_view, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(list.get(position),listener = listener)

    override fun getItemCount()
            = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(userModel: UserModel, listener: (UserModel) -> Unit) = with(itemView) {

            tv_address_row_selected_customers.text = itemView.context
                    .getString(R.string.customer_details_row_address_label,
                    AppSpecificUtils.getCompleteAddress(userModel.address))

            setOnClickListener { listener(userModel) }
        }
    }

}