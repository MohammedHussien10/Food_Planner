package com.example.foodplanner.calendar.views;

import android.content.Context;
import android.util.Log;
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
import com.example.foodplanner.models.CalendarPlan;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarHolder>{

    private Context context;
    private List<CalendarPlan> mealsCalenderPlan;
    private OnCalendarClickListener listener;


    public CalendarAdapter(Context context, OnCalendarClickListener listener) {
        this.context = context;
        this.mealsCalenderPlan = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorite_calendar_item,parent,false);
        return new CalendarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarHolder holder, int position) {
        CalendarPlan meal = mealsCalenderPlan.get(position);
        holder.mealsCalenderPlanName.setText(meal.getName());
        Glide.with(context).load(meal.getMealImage()).into(holder.mealsCalenderPlanImage);

        holder.deleteMeal.setOnClickListener(view -> {
            listener.removeMealFromCalenderPlan(meal);
        });

        holder.cardviewCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.navigateToDetails(meal.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsCalenderPlan.size();
    }

    public void setList(List<CalendarPlan> mealsList){
        Log.d("ADAPTER", "Updating RecyclerView with " + mealsCalenderPlan.size() + " meals");
        mealsCalenderPlan.clear();
        mealsCalenderPlan.addAll(mealsList);
    }
}

class CalendarHolder extends RecyclerView.ViewHolder {
    ImageView mealsCalenderPlanImage;
    TextView mealsCalenderPlanName;
    Button deleteMeal;
    CardView cardviewCal;
    public CalendarHolder(@NonNull View itemView) {
        super(itemView);
        mealsCalenderPlanImage = itemView.findViewById(R.id.imageView_Meal);
        mealsCalenderPlanName = itemView.findViewById(R.id.name_Meal);

        deleteMeal = itemView.findViewById(R.id.btn_delete);
        cardviewCal = itemView.findViewById(R.id.cardviewFavo_cal);
    }
}
