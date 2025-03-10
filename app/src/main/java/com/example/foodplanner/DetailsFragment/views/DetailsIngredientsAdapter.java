package com.example.foodplanner.DetailsFragment.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.RemoteMeals;

import java.util.ArrayList;
import java.util.List;

public class DetailsIngredientsAdapter extends RecyclerView.Adapter<DetailsIngredientsAdapter.DetailsIngredientsHolder> {

private Context context;
private List<String>  ingredients;
private DetailsClickListener detailsClickListener;

    public DetailsIngredientsAdapter(Context context, DetailsClickListener detailsClickListener) {
        this.context = context;
        this.ingredients = new ArrayList<>();
        this.detailsClickListener = detailsClickListener;
    }

    @NonNull
    @Override
    public DetailsIngredientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchbyitem,parent,false);
        return new DetailsIngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsIngredientsHolder holder, int position) {
        String name = ingredients.get(position);
        holder.name.setText(name);

        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+name+".png").into(holder.mealImage);
//        holder.category.setText(meals.getCategory());
//        holder.area.setText(meals.getArea());
//        holder.instructions.setText(meals.getInstructions());
//        holder.ingredient.setText(meals.getIngredient1());
//        holder.Measure.setText(meals.getMeasure1());
  //      Glide.with(context).load(meals.getMealImage()).into(holder.mealImage);

//        holder.btn_Guest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                detailsClickListener.addMealToHomeAdapter(meals);
//            }
//        });
    }

    public void setIngredientsList(List<String> ingredientsList){
        this.ingredients.clear();
        this.ingredients.addAll(ingredientsList);
    }


    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public class DetailsIngredientsHolder extends RecyclerView.ViewHolder{

         TextView name,category,area,instructions,ingredient,Measure;
         ImageView mealImage;
         Button btn_Guest;



        public DetailsIngredientsHolder(@NonNull View view){
            super(view);
            name = view.findViewById(R.id.item_searchName);
//            category = view.findViewById(R.id.);
//            area = view.findViewById(R.id.area_Meal);
//            instructions = view.findViewById(R.id.instructions_Meal);
//            ingredient = view.findViewById(R.id.ingredient_Meal);
//            Measure = view.findViewById(R.id.measure_Meal);
              mealImage = view.findViewById(R.id.item_searchImage);

          }
    }
}

