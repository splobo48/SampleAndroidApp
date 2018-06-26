package com.example.shaileshlobo.assignmentapp.ui.customerlisting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.shaileshlobo.assignmentapp.R;
import com.example.shaileshlobo.assignmentapp.data.models.UserModel;

import java.util.List;

/**
 * Created by spl on 21/6/18.
 */

public class CustomersGridViewAdapter extends BaseAdapter {

    private List<UserModel> list ;
    private Context context;
    private OnCheckChangedInterface callback;

    public CustomersGridViewAdapter(Context context, List<UserModel> list, OnCheckChangedInterface callback){
        this.context = context;
        this.list = list;
        this.callback = callback;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.row_customer_listing_view, parent, false);
        }

        // get current item to be displayed
        final UserModel currentItem = (UserModel) getItem(position);

        // get the TextView for item name and item description
        TextView itemNameTV = convertView
                .findViewById(R.id.tv_name_row_customer_listing_view);
        TextView itemEmailTV = convertView
                .findViewById(R.id.tv_email_row_customer_listing_view);
        final CheckBox isCustomerSelectedCheckBox = convertView.
                findViewById(R.id.rb_selected_row_customer_listing_view);

        //sets the text for item name and item description from the current item object
        itemNameTV.setText(context.getString(R.string.main_activity_row_name_label,
                currentItem.getName()));
        itemEmailTV.setText(context.getString(R.string.main_activity_row_email_label,
                currentItem.getEmail()));
        isCustomerSelectedCheckBox.setChecked(currentItem.isSelected());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCustomerSelectedCheckBox.toggle();
            }
        });

        isCustomerSelectedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                callback.onChanged(currentItem.getId(),isChecked);
            }
        });

        return convertView;
    }


    public interface OnCheckChangedInterface{
        void onChanged(long idOfCustomer, boolean isChecked);
    }


}
