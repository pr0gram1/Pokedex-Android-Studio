package com.example.pokedex.models;

import java.util.ArrayList;

public class RequestPokemon {

    private ArrayList<Pokemon> arrayRes;

    public ArrayList<Pokemon> getResults() {
        return arrayRes;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.arrayRes = arrayRes;
    }
}
