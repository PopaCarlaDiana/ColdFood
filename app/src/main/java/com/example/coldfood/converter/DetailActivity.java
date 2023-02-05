//package com.example.fridgeiside;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//
//import java.time.LocalDate;
//
//public class DetailActivity extends AppCompatActivity {
//
//    EditText nume_aliment,data_expirare;
//    Button save_button;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //ACTION BAR COLOR--------------------
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2eb875"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);
//        //-------------------------------------
//
//        setContentView(R.layout.activity_add);
//
//        nume_aliment=findViewById(R.id.nume_aliment);
//        data_expirare=findViewById(R.id.data_expirare);
//        save_button=findViewById(R.id.save_button);
//
//        /*
//        save_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DataBaseHelperFrigider myDB = new DataBaseHelperFrigider(AddActivity.this);
//                //String date = "2016-08-16";
//                //
//                //        //default, ISO_LOCAL_DATE
//                //        LocalDate localDate = LocalDate.parse(date);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    myDB.addAliment(nume_aliment.getText().toString().trim(),
//                            data_expirare.getText().toString().trim());
//                }
//                else{
//                    System.out.println("API!!!!!! Add Activity");
//                }
//            }
//        });
//        */
//    }
//}
