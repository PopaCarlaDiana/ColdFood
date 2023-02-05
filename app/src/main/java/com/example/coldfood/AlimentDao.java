package com.example.coldfood;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlimentDao {

    @Insert
    void insert(Aliment word);

    @Query("DELETE FROM listaAlimente")
    void deleteAll();

    @Query("DELETE FROM listaAlimente WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM listaAlimente ORDER BY id ASC")
    LiveData<List<Aliment>> getAlimente();

    @Query("SELECT * FROM listaAlimente ORDER BY cantitate ASC")
    LiveData<List<Aliment>> getAlimenteCantitatecresc();

    @Query("SELECT * FROM listaAlimente ORDER BY cantitate DESC")
    LiveData<List<Aliment>> getAlimenteCantitatedesc();

}
