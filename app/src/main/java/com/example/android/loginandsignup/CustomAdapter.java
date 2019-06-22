package com.example.android.loginandsignup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;


class CustomAdapter extends ArrayAdapter<String>{
    public CustomAdapter(Context context, ArrayList<String> foods) {
        super(context, R.layout.customrow, foods);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.customrow, parent, false);
        String singleFoodItem = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.adComplain);

        itemText.setText(singleFoodItem);

        Button rb = (Button) customView.findViewById(R.id.resolveButton);
        rb.setTag(position);
        rb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int pos = (int)arg0.getTag();
                remove(getItem(pos));
                ComplainDetailsAdvisorActivity.remove_complain(pos);
//                foods.remove(pos);
//                CustomAdapter.this.notifyDataSetChanged();

            }
        });

        return customView;
    }
}

