package com.example.foodplanner.favorites.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meals;


import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder>{

    private Context context;
    private List<Meals> favoriteList;
    private OnFavoriteClickListener listener;


    public FavoriteAdapter(Context context, OnFavoriteClickListener listener) {
        this.context = context;
        this.favoriteList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorite_calendar_item,parent,false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Meals meal = favoriteList.get(position);
        holder.mealFavoriteName.setText(meal.getName());
        Glide.with(context).load(meal.getMealImage()).into(holder.mealFavoriteImage);

        holder.deleteMeal.setOnClickListener(view -> {
            listener.removeMealFromFavourite(meal);
        });

        holder.cardviewFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.navigateToDetails(meal.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public void setList(List<Meals> mealsList){
        favoriteList.clear();
        favoriteList.addAll(mealsList);
    }
}

class FavoriteViewHolder extends RecyclerView.ViewHolder {
    ImageView mealFavoriteImage;
    TextView mealFavoriteName;
    Button deleteMeal;
    CardView cardviewFavo;

    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);
        mealFavoriteImage = itemView.findViewById(R.id.imageView_Meal);
        mealFavoriteName = itemView.findViewById(R.id.name_Meal);

        deleteMeal = itemView.findViewById(R.id.btn_delete);
        cardviewFavo = itemView.findViewById(R.id.cardviewFavo_cal);
    }
}
