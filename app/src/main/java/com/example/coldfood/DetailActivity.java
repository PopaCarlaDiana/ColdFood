package com.example.coldfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;

public class DetailActivity extends AppCompatActivity {

    EditText editTextNumber;
    Button plus_button, minus_button;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ACTION BAR COLOR--------------------
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2eb875"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        //-------------------------------------

        setContentView(R.layout.activity_detail);

        EditText editTextNumber;
        Button plus_button, minus_button, commit_button;
        TextView textView;

        editTextNumber = findViewById(R.id.editTextNumber);
        plus_button = findViewById(R.id.plus_button);
        minus_button = findViewById(R.id.minus_button);
        commit_button = findViewById(R.id.commit_button);
        textView = findViewById(R.id.textView);


        Intent intent = new Intent();
        intent = getIntent();
        Aliment food = (Aliment) intent.getSerializableExtra("food");

        textView.setText(food.getNume());
        editTextNumber.setText(String.valueOf(food.getCantitate()));

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = editTextNumber.getText().toString();
                int i = Integer.parseInt(val);
                i += 1;
                editTextNumber.setText(String.valueOf(i));
            }
        });
        minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = editTextNumber.getText().toString();
                int i = Integer.parseInt(val);
                if(i>0)
                    i -= 1;
                editTextNumber.setText(String.valueOf(i));
            }
        });

        commit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = editTextNumber.getText().toString();
                int i = Integer.parseInt(val);
                food.setCantitate(i);
            }
        });

    }
}

