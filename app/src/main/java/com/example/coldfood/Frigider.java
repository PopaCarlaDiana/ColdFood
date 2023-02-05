package com.example.coldfood;

import java.util.ArrayList;

public class Frigider {

    private ArrayList<Aliment> listaAlimente = new ArrayList<>();

    public Frigider() {
    }

    public Frigider(ArrayList<Aliment> listaAlimente) {
        this.listaAlimente = listaAlimente;
    }

    public ArrayList<Aliment> getListaAlimente() {
        return listaAlimente;
    }

    public void setListaAlimente(ArrayList<Aliment> listaAlimente) {
        this.listaAlimente = listaAlimente;
    }

    public void AdaugareAliment(Aliment aliment){
        listaAlimente.add(aliment);
    }

    public void StergereAliment(Aliment aliment){
        //TODO ??
        this.listaAlimente.remove(aliment);
    }

}
