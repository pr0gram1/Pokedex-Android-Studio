package com.example.pokedex.pokeapi;

import com.example.pokedex.models.PokemonResquesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonResquesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
