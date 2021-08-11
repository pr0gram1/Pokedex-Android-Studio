package com.example.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.models.Pokemon;



public class AdapterPokeList extends RecyclerView.Adapter<AdapterPokeList.ViewHolder> {

    private ArrayList<Pokemon> arrayRes;

    private Context context;

    public AdapterPokeList(Context context) {
        this.context = context;
        arrayRes = new ArrayList<>();


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup patent, int viewType) {
        View view = LayoutInflater.from(patent.getContext()).inflate(R.layout.poke_chararcter,patent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = arrayRes.get(position);
        holder.viewText.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber() +".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.viewImage);
    }

    @Override
    public int getItemCount() {
        return arrayRes.size();
    }

    public void addPokeList(ArrayList<Pokemon> listaPokemon) {

        arrayRes.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView viewImage;
        private TextView viewText;

        public ViewHolder(View itemView) {
            super(itemView);

            viewImage = (ImageView) itemView.findViewById(R.id.viewImage);
            viewText = (TextView) itemView.findViewById(R.id.viewText);
        }
    }
}
