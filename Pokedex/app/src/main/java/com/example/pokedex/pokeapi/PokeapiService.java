package com.example.pokedex.pokeapi;

import com.example.pokedex.models.RequestPokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<RequestPokemon> listPoke(@Query("limit") int limit, @Query("offset") int offset);
}
