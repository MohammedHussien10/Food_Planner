package com.example.foodplanner.SearchSelectbyFragment.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Area;
import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Ingredients;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchSelectbyAreaAdapter extends RecyclerView.Adapter<SearchSelectbyAreaAdapter.AreaHolder> {

    private Context context;
    private List<Area> areaList;
    private List<Area> copyAreaList;
    private SearchSelectbyClickListener searchSelectbyClickListener;
    public SearchSelectbyAreaAdapter(Context context, SearchSelectbyClickListener searchSelectbyClickListener) {
        this.context = context;
        this.areaList = new ArrayList<>();
        this.searchSelectbyClickListener = searchSelectbyClickListener;
        this.copyAreaList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AreaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchbyitem, parent, false);
        return new AreaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaHolder holder, int position) {
        Area area = areaList.get(position);
        holder.areaName.setText(area.getStrArea());
        String imageName = area.getStrArea().toLowerCase().replace(" ", "_");
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        if (imageResId != 0) {
            holder.areaImage.setImageResource(imageResId);
        } else {
          Log.i("image","no image");
        }

        holder.selectedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchSelectbyClickListener.navigateAreaToSearchbyMeal(area.getStrArea());
            }
        });

    }

    public void setAreaList(List<Area> areaList) {
        this.areaList.clear();
        this.areaList.addAll(areaList);
        this.copyAreaList.clear();
        this.copyAreaList.addAll(areaList);
    }


    @Override
    public int getItemCount() {
        return areaList.size();
    }

    public void filter(String text) {
        areaList.clear();
        if (text.isEmpty()) {
            areaList.addAll(copyAreaList);
        } else {
            for (Area item : copyAreaList) {
                if (item.getStrArea().toLowerCase().contains(text.toLowerCase())) {
                    areaList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class AreaHolder extends RecyclerView.ViewHolder {

        TextView areaName;
        ImageView areaImage;
        CardView selectedCard;

        public AreaHolder(@NonNull View view) {
            super(view);
            areaName = view.findViewById(R.id.item_searchName);
            areaImage = view.findViewById(R.id.item_searchImage);
            selectedCard = view.findViewById(R.id.selecteditemcard);
        }
    }
}

