package com.example.coldfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalDate;

public class DataBaseHelperFrigider extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "COLDFood.db";
    private static final int DATABASE_VESRION = 1;

    private static final String TABLE_NAME = "Frigider";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUMEALIMENT = "nume_aliment";
    private static final String COLUMN_DATA_EXPIRARE = "data_expirare";

    public DataBaseHelperFrigider(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VESRION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME+
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NUMEALIMENT+" TEXT, "+
                COLUMN_DATA_EXPIRARE+" LOCALDATE);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    void addAliment(String numeAliment, String DataExpirare){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NUMEALIMENT,numeAliment);
        cv.put(COLUMN_DATA_EXPIRARE,DataExpirare);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context,"Faild to add",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added successfully!",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "Select * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            db.rawQuery(query,null);
        }
        return cursor;
    }

}
