package com.example.foodplanner.SearchSelectbyFragment.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class SearchSelectbyIngredientAdapter extends RecyclerView.Adapter<SearchSelectbyIngredientAdapter.IngredientsHolder> {

    private Context context;
    private List<Ingredients> ingredientList;
    private List<Ingredients> copyIngredientsList;
    private SearchSelectbyClickListener searchSelectbyClickListener;

    public SearchSelectbyIngredientAdapter(Context context, SearchSelectbyClickListener searchSelectbyClickListener) {
        this.context = context;
        this.ingredientList = new ArrayList<>();
        this.copyIngredientsList = new ArrayList<>();
        this.searchSelectbyClickListener = searchSelectbyClickListener;
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchbyitem, parent, false);
        return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder holder, int position) {
        Ingredients ingredient = ingredientList.get(position);

        holder.ingName.setText(ingredient.getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredient.getStrIngredient() + ".png").into(holder.ingImage);


        holder.selectedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchSelectbyClickListener.navigateIngredientToSearchbyMeal(ingredient.getStrIngredient());
            }
        });

    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientList.clear();
        this.ingredientList.addAll(ingredientsList);
        this.copyIngredientsList.clear();
        this.copyIngredientsList.addAll(ingredientsList);
    }


    @Override
    public int getItemCount() {
        return ingredientList.size();
    }


    public void filter(String text) {
        ingredientList.clear();
        if (text.isEmpty()) {
            ingredientList.addAll(copyIngredientsList);
        } else {
            for (Ingredients item : copyIngredientsList) {
                if (item.getStrIngredient().toLowerCase().contains(text.toLowerCase())) {
                    ingredientList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class IngredientsHolder extends RecyclerView.ViewHolder {

        TextView ingName;
        ImageView ingImage;
        CardView selectedCard;

        public IngredientsHolder(@NonNull View view) {
            super(view);
            ingName = view.findViewById(R.id.item_searchName);
            ingImage = view.findViewById(R.id.item_searchImage);
            selectedCard = view.findViewById(R.id.selecteditemcard);
        }
    }
}

