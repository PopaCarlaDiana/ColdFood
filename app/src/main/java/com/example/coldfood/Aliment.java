package com.example.coldfood;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.*;


@Entity(tableName = "listaAlimente")
public class Aliment implements Serializable {
    @NonNull
    public String toString() {
        return "Aliment{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", dataExpirare=" + dataExpirare +
                ", dataInceput=" + dataInceput +
                ", importanta=" + importanta +
                ", cantitate=" + cantitate +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="nume_aliment")
    private String nume;
    @ColumnInfo(name="data_expirare")
    private Integer dataExpirare;
    @ColumnInfo(name="data_inceput")
    private Integer dataInceput;
    @ColumnInfo(name="importanta")
    private boolean importanta;
    @ColumnInfo(name="cantitate")
    private int cantitate;

    public Aliment(String nume, Integer dataExpirare, Integer dataInceput, boolean importanta, int cantitate) {
        this.nume = nume;
        this.dataExpirare = dataExpirare;
        this.dataInceput = dataInceput;
        this.importanta = importanta;
        this.cantitate = cantitate;
    }

    @Ignore
    public Aliment(String nume, Integer dataExpirare, boolean importanta, int cantitate) {
        System.out.println(dataExpirare.toString()+'\n');
        this.nume = nume;
        this.dataExpirare = dataExpirare;
        this.importanta = importanta;
        this.cantitate = cantitate;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.dataInceput=0;
        }
        System.out.println(this.dataExpirare.toString()+'\n');
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDataInceput(Integer dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Integer getDataInceput() {
        return dataInceput;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Integer dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public boolean isImportanta() {
        return importanta;
    }

    public void setImportanta(boolean importanta) {
        this.importanta = importanta;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getFreshProcent(){
        int ziPrezent=3;
        int fresh = (this.getDataExpirare()-ziPrezent)*100/(this.getDataExpirare());
        return Math.max(fresh,0);
    }

    public boolean equals(Aliment aliment) {
        return (importanta == aliment.importanta
                && cantitate == aliment.cantitate
                && nume.equals(aliment.nume)
                && dataExpirare.equals(aliment.dataExpirare)
                && dataInceput.equals(aliment.dataInceput));
    }

        public static Comparator<Aliment> AlimentAscendingFreshness = new Comparator<Aliment>() {
        @Override
        public int compare(Aliment f1, Aliment f2) {
            boolean ok=f1.getFreshProcent()>f2.getFreshProcent();
            if(ok==true)
                return 1;
            ok=f1.getFreshProcent()<f2.getFreshProcent();
            if(ok==true)
                return -1;
            return 0;
        }
    };


    public static Comparator<Aliment> AlimentDescendingFreshness = new Comparator<Aliment>() {
        @Override
        public int compare(Aliment f1, Aliment f2) {
            boolean ok=f1.getFreshProcent()<f2.getFreshProcent();
            if(ok==true)
                return 1;
            ok=f1.getFreshProcent()>f2.getFreshProcent();
            if(ok==true)
                return -1;
            return 0;
        }
    };

}
