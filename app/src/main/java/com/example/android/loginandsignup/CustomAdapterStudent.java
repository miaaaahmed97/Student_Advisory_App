package com.example.android.loginandsignup;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapterStudent extends ArrayAdapter<String> {
    ArrayList<String> foods;

    public CustomAdapterStudent(Context context, ArrayList<String> foods) {
        super(context, R.layout.customrow, foods);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.customrow_student, parent, false);
        String singleFoodItem = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.studentComplain);

        itemText.setText(singleFoodItem);



        return customView;
    }
}

