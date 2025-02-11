package com.example.foodplanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meals;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewMealHolder> {

private Context context;
private List<Meals> mealsList;

    public RecyclerViewAdapter(Context context, List<Meals> mealsList) {
        this.context = context;
        this.mealsList = mealsList;
    }

    @NonNull
    @Override
    public MyViewMealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.randommeals,parent,false);
        MyViewMealHolder myViewMealHolder = new MyViewMealHolder(view);
        return myViewMealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewMealHolder holder, int position) {
        Meals meals = mealsList.get(position);
        holder.name.setText(meals.getName());
        holder.category.setText(meals.getCategory());
        holder.area.setText(meals.getArea());
        holder.instructions.setText(meals.getInstructions());
        holder.ingredient.setText(meals.getIngredient());
        holder.Measure.setText(meals.getMeasure());
        Glide.with(context).load(meals.getMealImage()).into(holder.mealImage);

        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, meals.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mealsList.size();
    }


    public class MyViewMealHolder extends RecyclerView.ViewHolder{

        ConstraintLayout convertView;
        public TextView name,category,area,instructions,ingredient,Measure;
        public ImageView mealImage;

        public MyViewMealHolder(@NonNull View view){
            super(view);
            convertView = (ConstraintLayout) view;
            name = view.findViewById(R.id.name_Meal);
            category = view.findViewById(R.id.category_Meal);
            area = view.findViewById(R.id.area_Meal);
            instructions = view.findViewById(R.id.instructions_Meal);
            ingredient = view.findViewById(R.id.ingredient_Meal);
            Measure = view.findViewById(R.id.measure_Meal);
            mealImage = view.findViewById(R.id.imageView_Meal);

        }




    }
}
