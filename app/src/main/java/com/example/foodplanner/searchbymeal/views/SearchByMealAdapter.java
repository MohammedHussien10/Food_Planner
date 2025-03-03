package com.example.foodplanner.searchbymeal.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Ingredients;
import com.example.foodplanner.models.RemoteMeals;

import java.util.ArrayList;
import java.util.List;

public class SearchByMealAdapter extends RecyclerView.Adapter<SearchByMealAdapter.SearchByMealHolder> {

    private Context context;
    private List<RemoteMeals> mealsList;
   private List<RemoteMeals> copymealsList;
    private SearchByMealClickListener searchByMealClickListener;

    public SearchByMealAdapter(Context context, SearchByMealClickListener searchSelectbyClickListener) {
        this.context = context;
        this.mealsList = new ArrayList<>();
       this.copymealsList = new ArrayList<>();
        this.searchByMealClickListener = searchSelectbyClickListener;
    }

    @NonNull
    @Override
    public SearchByMealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchbyitem, parent, false);
        return new SearchByMealHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByMealHolder holder, int position) {
        RemoteMeals meal = mealsList.get(position);

        holder.mealName.setText(meal.getName());
        Glide.with(context).load(meal.getMealImage()).into(holder.mealImage);

        holder.selectedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchByMealClickListener.navigateToDetails(meal.getId());
            }
        });

    }

    public void setMealList(List<RemoteMeals> mealList) {
        this.mealsList.clear();
        this.mealsList.addAll(mealList);
        this.copymealsList.clear();
        this.copymealsList.addAll(mealList);
    }


    @Override
    public int getItemCount() {
        return mealsList.size();
    }


    public void filter(String text) {
        mealsList.clear();
        if (text.isEmpty()) {
            mealsList.addAll(copymealsList);
        } else {
            for (RemoteMeals item : copymealsList) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    mealsList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class SearchByMealHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        ImageView mealImage;
        CardView selectedCard;

        public SearchByMealHolder(@NonNull View view) {
            super(view);
            mealName = view.findViewById(R.id.item_searchName);
            mealImage = view.findViewById(R.id.item_searchImage);
            selectedCard = view.findViewById(R.id.selecteditemcard);
        }
    }
}

