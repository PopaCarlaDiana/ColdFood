package com.example.coldfood;

import static androidx.lifecycle.Lifecycle.State.STARTED;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
//import android.support.v7.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coldfood.converter.AlimentArrayAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    AlimentDao alimentDao;
    List<Aliment> listaAlimente;
    Frigider frigider = new Frigider();
    AlimentArrayAdapter arrayAdapter;

    ListView foodListView;
    int sortType = 1;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2eb875"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });



        AlimentRoomDatabase db = AlimentRoomDatabase.getDatabase(this);
        alimentDao = db.alimentDao();
        listaAlimente = alimentDao.getAlimente().getValue();

        foodListView = (ListView) findViewById(R.id.foodList);
        foodListView.setOnItemClickListener(this);

        ArrayList<Aliment> arrayListAlimente = new ArrayList<>();



        ImageButton buton = findViewById(R.id.sortItems);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sortType == 1) {
                    alimentDao.getAlimenteCantitatecresc().observe((LifecycleOwner) MainActivity.this , (list) -> {
                        arrayAdapter = new AlimentArrayAdapter(getApplicationContext(), list);
                        foodListView.setAdapter(arrayAdapter);
                    });
                    sortType = -1;
                    buton.setRotation(180);
                } else {
                    alimentDao.getAlimenteCantitatedesc().observe((LifecycleOwner) MainActivity.this , (list) -> {
                        arrayAdapter = new AlimentArrayAdapter(getApplicationContext(), list);
                        foodListView.setAdapter(arrayAdapter);
                    });
                    sortType = 1;
                    buton.setRotation(0);
                }
                if (arrayAdapter != null)
                    foodListView.setAdapter(arrayAdapter);
            }
        });

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myCh","My Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myCh")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("Item expiring soon!")
                .setContentText("Be careful, an "+" item "+" will expire soon!");

        notification = builder.build();
        notificationManagerCompat = notificationManagerCompat.from(this);

        alimentDao.getAlimente().observe(this, (list) -> {
            List<Aliment> lista_alimente=list;
            for(Aliment a:list){
                if(a.getFreshProcent()<30) {
                    push(foodListView);
                    System.out.println(a.getNume()+"  "+a.getFreshProcent()+"  FRESH OKKKKKKKKKKKKKKK\n");
                }
            }
        });
    }


    public void onItemClickNotificare(AdapterView<?> adapterView, View view, int i, long l) {
        Aliment food = (Aliment) adapterView.getItemAtPosition(i);
        Toast.makeText(getApplicationContext(),food.getNume(), Toast.LENGTH_SHORT).show();
    }

    public void push(View view) {
        notificationManagerCompat.notify(4,notification);
    }


    @Override
    protected void onStart() {
        super.onStart();

        alimentDao.getAlimente().observe(this, (list) -> {
            //Log.e("XXX", list.toString());
            List<Aliment> lista_alimente=list;
            for(Aliment a:list){
                if(a.getFreshProcent()<30) {
                        System.out.println(a.getNume()+"  "+a.getFreshProcent()+"  FRESH OKKKKKKKKKKKKKKK\n");
                }
            }
            arrayAdapter = new AlimentArrayAdapter(this, list);
            foodListView.setAdapter(arrayAdapter);

            foodListView.setOnItemClickListener(this);

        });
    }

    void storeDataInArrays() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Aliment food = (Aliment) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("food", (Aliment) adapterView.getItemAtPosition(i));
        startActivity(intent);
        //food.setCantitate(MainActivity.aux);
        //System.out.println(aux+"-------------------------------------------------");
    }

}


//System.out.println("OKKKKKKKKKKKK");

//Aliment a1=new Aliment("Lapte", LocalDate.of(2022, 12, 20),LocalDate.of(2022, 11, 30),false,2);
//            Frigider frigider=new Frigider();
//            frigider.AdaugareAliment(a1);
//
//            frigider.getListaAlimente();

//             ListView foodList = (ListView)findViewById(R.id.foodList);
//             FoodArrayAdapter arrayAdapter = new FoodArrayAdapter(this, frigider.getListaAlimente());
//             foodList.setAdapter(arrayAdapter);
//             foodList.setOnItemClickListener(this);
//        Cursor cursor = myDB.readAllData();
//        System.out.println("OKKKKKKKKKKKK");

//System.out.println("OKKKKKKKKKKKKKKK" + cursor.getCount());
//            if(cursor.getCount()==0){
//               //Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
//           }else{
//                while(cursor.moveToNext()) {
//                    String numealiment = cursor.getString(0);
//                    //nume_aliment.add(cursor.getString(1));
//                    //data_expirare_aliment.add(cursor.getString(2));
//                    Aliment a = new Aliment(numealiment,null,null,false,0);
//                    lista_alimente.add(a);
//                }
//  }

//    editText = (EditText) findViewById(R.id.editTextTextPersonName);
//    btnAdd = (Button) findViewById(R.id.buttonadd);
//    //btnViewData = (Button) findViewById(R.id.btnView);
//    mDatabaseHelper = new DatabaseHelper(this);
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String NumeAliment = editText.getText().toString();
//            String DataExpirare = editText.getText().toString();
//            if (editText.length() != 0) {
//                AddData(NumeAliment,DataExpirare);
//                editText.setText("");
//            } else {
//                toastMessage("You must put something in the text field!");
//            }
//        }
//    });
//
//        btnViewData.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
//            startActivity(intent);
//        }
//    });
//}
//
//    public void AddData(String newEntry,String DataExpirare) {
//        boolean insertData = mDatabaseHelper.addData(newEntry,DataExpirare);
//
//        if (insertData) {
//            toastMessage("Data Successfully Inserted!");
//        } else {
//            toastMessage("Something went wrong");
//        }
//    }
//
//    /**
//     * customizable toast
//     * @param message
//     */
//    private void toastMessage(String message){
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
//    }

//System.out.println("AICI!!!!!!!!!!!!!");

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            Aliment a1=new Aliment("Lapte", LocalDate.of(2022, 12, 20),LocalDate.of(2022, 11, 30),false,2);
//            Aliment a2=new Aliment("Oua", LocalDate.of(2022, 12, 20),LocalDate.of(2022, 11, 30),false,2);
//            Aliment a3=new Aliment("Branza", LocalDate.of(2022, 12, 20),LocalDate.of(2022, 11, 30),false,2);
//            Aliment a4=new Aliment("Iaurt", LocalDate.of(2022, 12, 20),LocalDate.of(2022, 11, 30),false,2);
//            Aliment a5=new Aliment("Actimel", LocalDate.of(2022, 12, 20),LocalDate.of(2022, 11, 30),false,2);
//            Frigider frigider=new Frigider();
//            frigider.AdaugareAliment(a1);
//            frigider.AdaugareAliment(a2);
//            frigider.AdaugareAliment(a3);
//            frigider.AdaugareAliment(a4);
//            frigider.AdaugareAliment(a5);
//
//            frigider.getListaAlimente();
//
//            // ListView foodList = (ListView)findViewById(R.id.foodList);
//            // FoodArrayAdapter arrayAdapter = new FoodArrayAdapter(this, frigider.getListaAlimente());
//            // foodList.setAdapter(arrayAdapter);
//            //foodList.setOnItemClickListener(this);
//
//        }