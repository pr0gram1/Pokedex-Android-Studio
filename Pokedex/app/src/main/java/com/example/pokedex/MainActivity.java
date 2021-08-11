package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.RequestPokemon;
import com.example.pokedex.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private AdapterPokeList listPoker;


    private int offset;

    private boolean forUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listPoker = new AdapterPokeList(this);
        recyclerView.setAdapter(listPoker);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (forUpload) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, "End.");

                            forUpload = false;
                            offset += 20;
                            dataObtain(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        forUpload = true;
        offset = 0;
        dataObtain(offset);
    }

    private void dataObtain(int offset) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<RequestPokemon> requestPokemonCall = service.listPoke(20, offset);

        requestPokemonCall.enqueue(new Callback<RequestPokemon>() {
            @Override
            public void onResponse(Call<RequestPokemon> call, Response<RequestPokemon> response) {
                forUpload = true;
                if ( response.isSuccessful()) {

                    RequestPokemon requestPokemon = response.body();
                    ArrayList<Pokemon> listaPokemon = requestPokemon.getResults();

                    listPoker.addPokeList(listaPokemon);



                } else {
                    Log.e(TAG, "onResponse" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RequestPokemon> call, Throwable t) {
                forUpload = true;
                Log.e(TAG, "onFailure" + t.getMessage());

            }
        });

    }
}