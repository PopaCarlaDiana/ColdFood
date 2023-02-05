package com.example.coldfood;

import java.util.ArrayList;

public class ListaDeCumparaturi {
    private ArrayList<Aliment> listaAlimente = new ArrayList<>();

    public ListaDeCumparaturi(ArrayList<Aliment> listaAlimente) {
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
