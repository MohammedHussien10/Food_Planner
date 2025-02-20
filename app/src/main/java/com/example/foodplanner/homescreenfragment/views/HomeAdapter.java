package com.example.foodplanner.homescreenfragment.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meals;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

private Context context;
private List<Meals> mealsList;
private HomeClickListener homeClickListener;

    public HomeAdapter(Context context, List<Meals> mealsList,HomeClickListener homeClickListener) {
        this.context = context;
        this.mealsList = new ArrayList<>(mealsList);
        this.homeClickListener = homeClickListener;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.randommeals,parent,false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        Meals meals = mealsList.get(position);
        holder.name.setText(meals.getName());
        holder.category.setText(meals.getCategory());
        holder.area.setText(meals.getArea());
        holder.instructions.setText(meals.getInstructions());
        holder.ingredient.setText(meals.getIngredient());
        holder.Measure.setText(meals.getMeasure());
        Glide.with(context).load(meals.getMealImage()).into(holder.mealImage);

//        holder.btn_Guest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                homeClickListener.addMealToHomeAdapter(meals);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return mealsList.size();
    }


    public class HomeHolder extends RecyclerView.ViewHolder{

         ConstraintLayout convertView;
         TextView name,category,area,instructions,ingredient,Measure;
         ImageView mealImage;
         Button btn_Guest;



        public HomeHolder(@NonNull View view){
            super(view);
            convertView = (ConstraintLayout) view;
            name = view.findViewById(R.id.name_Meal);
            category = view.findViewById(R.id.category_Meal);
            area = view.findViewById(R.id.area_Meal);
            instructions = view.findViewById(R.id.instructions_Meal);
            ingredient = view.findViewById(R.id.ingredient_Meal);
            Measure = view.findViewById(R.id.measure_Meal);
            mealImage = view.findViewById(R.id.imageView_Meal);
            btn_Guest = view.findViewById(R.id.btn_Guest);
            if (btn_Guest == null) {
                Log.i("HomeAdapter", "btn_Guest is NULL! Check XML file.");
          }
    }
}
}
