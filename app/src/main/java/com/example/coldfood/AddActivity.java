package com.example.coldfood;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    EditText nume_aliment,data_expirare,cantitate;
    Button save_button;
    AlimentDao alimentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2eb875"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        setContentView(R.layout.activity_add);

        AlimentRoomDatabase db = AlimentRoomDatabase.getDatabase(this);
        alimentDao = db.alimentDao();

        nume_aliment=findViewById(R.id.nume_aliment);
        data_expirare=findViewById(R.id.data_expirare);
        cantitate=findViewById(R.id.cantitate);
        save_button=findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    AlimentRoomDatabase.databaseWriteExecutor.execute(() -> {
                        Aliment a1 = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            SimpleDateFormat formatter1=new SimpleDateFormat("dd.MM.yyyy");

                            try {
                                Date date=formatter1.parse(data_expirare.getText().toString().trim());
                                Integer nr=Integer.parseInt(cantitate.getText().toString());
                                LocalDate localDate=convertToLocalDateViaInstant(date);
                                int dataexpirare=9;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        LocalDate presentDate= LocalDate.now();
                                        if(localDate.getYear()==2023)
                                            dataexpirare=365-(presentDate.getDayOfYear()-localDate.getDayOfYear());
                                        else
                                            dataexpirare=localDate.getDayOfYear()-presentDate.getDayOfYear();
                                    }

                                System.out.println(localDate.toString()+'\n');
                                a1 = new Aliment(nume_aliment.getText().toString().trim(),dataexpirare , false, nr);
                                alimentDao.insert(a1);
                            } catch (ParseException e) {
                            }
                        }
                    });
                    Toast.makeText(getApplicationContext(),"Adaugat cu succes!",Toast.LENGTH_SHORT).show();
                }
                else{
                    System.out.println("API!!!!!! Add Activity");
                }
            }
        });

    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return dateToConvert.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate data=LocalDate.of(2023,1,13);
        }
        return null;
    }
}