package com.example.foodplanner.SearchSelectbyFragment.views;

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
import com.example.foodplanner.models.Categories;

import java.util.ArrayList;
import java.util.List;

public class SearchSelectbyCategoryAdapter extends RecyclerView.Adapter<SearchSelectbyCategoryAdapter.CategoryHolder> {

    private Context context;
    private List<Categories> categoryList;
    private List<Categories> copyCategoryList;
    private SearchSelectbyClickListener searchSelectbyClickListener;

    public SearchSelectbyCategoryAdapter(Context context, SearchSelectbyClickListener searchSelectbyClickListener) {
        this.context = context;
        this.categoryList = new ArrayList<>();
        this.copyCategoryList = new ArrayList<>();
        this.searchSelectbyClickListener = searchSelectbyClickListener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchbyitem, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Categories category = categoryList.get(position);
        holder.categoryName.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.categoryImage);
        holder.selectedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchSelectbyClickListener.navigateCategoryToSearchbyMeal(category.getStrCategory());
            }
        });

    }

    public void setcategoryList(List<Categories> category) {
        this.categoryList.clear();
        this.categoryList.addAll(category);
        this.copyCategoryList.clear();
        this.copyCategoryList.addAll(category);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public void filter(String text) {
        categoryList.clear();
        if (text.isEmpty()) {
            categoryList.addAll(copyCategoryList);
        } else {
            for (Categories item : copyCategoryList) {
                if (item.getStrCategory().toLowerCase().contains(text.toLowerCase())) {
                    categoryList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class CategoryHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;
        CardView selectedCard;

        public CategoryHolder(@NonNull View view) {
            super(view);
            categoryName = view.findViewById(R.id.item_searchName);
            categoryImage = view.findViewById(R.id.item_searchImage);
            selectedCard = view.findViewById(R.id.selecteditemcard);

        }
    }
}

