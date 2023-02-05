package com.example.coldfood;
import com.example.coldfood.Aliment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FoodArrayAdapter extends ArrayAdapter<Aliment>{
    public FoodArrayAdapter(Context context, List<Aliment> foodList){
        super(context, 0, foodList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aliment food = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_lay, parent, false);
        }

        TextView textView=convertView.findViewById(R.id.foodListItem_name);
        textView.setText(food.getNume());

        return convertView;
    }
}

