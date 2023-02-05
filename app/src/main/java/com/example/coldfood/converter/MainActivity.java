//package com.example.fridgeiside;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.coldfood.converter.AlimentArrayAdapter;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
//
//    ListView foodList;
//    int sortType=1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2eb875"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);
//
//        setContentView(R.layout.activity_main);
//
//        foodList = (ListView)findViewById(R.id.foodList);
//
//        ArrayList<Food> arrayList = new ArrayList<>();
//
//        Food a1= new Food("Salam", 20, 1);
//        Food a2= new Food("Lapte", 100, 2);
//        Food a3= new Food("Oua", 70, 3);
//        Food a4= new Food("Steak", 50, 5);
//        Food a5= new Food("Piept de Pui", 84, 5);
//        Food a6= new Food("Sarmale", 38, 5);
//        Food a7= new Food("Ciorba de Burta", 64, 5);
//        Food a8= new Food("Costite", 15, 5);
//
//        arrayList.add(a1);
//        arrayList.add(a2);
//        arrayList.add(a3);
//        arrayList.add(a4);
//        arrayList.add(a5);
//        arrayList.add(a6);
//        arrayList.add(a7);
//        arrayList.add(a8);
//        arrayList.add(a1);
//        arrayList.add(a2);
//        arrayList.add(a3);
//        arrayList.add(a4);
//        arrayList.add(a5);
//        arrayList.add(a6);
//        arrayList.add(a7);
//        arrayList.add(a8);
//
//        AlimentArrayAdapter arrayAdapter = new AlimentArrayAdapter(this, arrayList);
//
//        foodList.setAdapter(arrayAdapter);
//
//        foodList.setOnItemClickListener(this);
//
//        FloatingActionButton floatButton = findViewById(R.id.floatButton);
//        floatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,AddActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton buton = findViewById(R.id.sortItems);
//        buton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(sortType==1){
//                    Collections.sort(arrayList,Food.FoodAscendingFreshness);
//                    sortType=-1;
//                    buton.setRotation(180);
//                }
//                else{
//                    Collections.sort(arrayList,Food.FoodDescendingFreshness);
//                    sortType=1;
//                    buton.setRotation(0);
//                }
//                foodList.setAdapter(arrayAdapter);
//            }
//        });
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Food food = (Food) adapterView.getItemAtPosition(i);
//        Intent intent = new Intent(MainActivity.this,AddActivity.class);
//        startActivity(intent);
//    }
//}