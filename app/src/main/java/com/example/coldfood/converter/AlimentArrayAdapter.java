package com.example.coldfood.converter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.coldfood.Aliment;
import com.example.coldfood.R;

import java.util.List;

public class AlimentArrayAdapter extends ArrayAdapter<Aliment>{
    public AlimentArrayAdapter(Context context, List<Aliment> foodList){
        super(context, 0, foodList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aliment aliment = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_lay, parent, false);
        }

        TextView name = convertView.findViewById(R.id.foodListItem_name);
        name.setText(aliment.getNume());

        TextView fresh = convertView.findViewById(R.id.foodListItem_pro);
        fresh.setText(String.valueOf(aliment.getFreshProcent())+"%");

        CardView card=convertView.findViewById(R.id.foodListItem_card);

        ProgressBar bar = convertView.findViewById(R.id.ProgressBar);
        bar.setProgress(aliment.getFreshProcent());

        int pink =Color.rgb(255,181,181);
        int red = Color.rgb(218, 41, 28);
        int orange = Color.rgb(255, 166, 77);
        int yellow = Color.rgb(255, 241, 118);
        int green = Color.rgb(158, 189, 123);

        if(aliment.getFreshProcent()==0)
        {
            card.setBackgroundColor(pink);
        }
        else if(aliment.getFreshProcent()<=25)
        {
            bar.setProgressTintList(ColorStateList.valueOf(red));
        }
        else if(aliment.getFreshProcent()<=50)
        {
            bar.setProgressTintList(ColorStateList.valueOf(orange));
        }
        else if(aliment.getFreshProcent()<=75)
        {
            bar.setProgressTintList(ColorStateList.valueOf(yellow));
        }
        else
        {
            bar.setProgressTintList(ColorStateList.valueOf(green));
        }

        return convertView;
    }
}
