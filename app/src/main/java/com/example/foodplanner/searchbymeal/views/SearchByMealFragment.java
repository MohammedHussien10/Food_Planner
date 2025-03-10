package com.example.foodplanner.searchbymeal.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.models.Area;
import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Ingredients;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.models.RemoteMeals;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.searchbymeal.presenter.SearchByMealContract;
import com.example.foodplanner.searchbymeal.presenter.SearchByMealPresenter;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class SearchByMealFragment extends Fragment implements SearchByMealClickListener, SearchByMealContract {
    SearchByMealAdapter searchByMealAdapter;
    RecyclerView recyclerV_Selected_items;
    SearchByMealPresenter searchByMealPresenter;
    EditText editText_searchbymeal;
    public SearchByMealFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchByMealPresenter = new SearchByMealPresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource
                        .getInstance(requireContext()), MealsRemoteDataSource.getInstance()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.searchbymeal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMealDetailsByIngredient();
        getMealDetailsByArea();
        getMealDetailsByCategories();
        editText_searchbymeal = view.findViewById(R.id.et_searchbymeal);
        recyclerV_Selected_items = view.findViewById(R.id.searchbymeal_Rv);
        searchByMealAdapter = new SearchByMealAdapter(getContext(), this);

        recyclerV_Selected_items.setAdapter(searchByMealAdapter);

        editText_searchbymeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String searchText = charSequence.toString().trim();


                    searchByMealAdapter.filter(searchText);

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void assignSelectedMealToAdapter(List<RemoteMeals> mealList) {
        searchByMealAdapter.setMealList(mealList);
        searchByMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMealDetailsByCategories() {
        if (getArguments() != null) {
            String itemName  = getArguments().getString("categoryName");
            if (itemName != null) {
                searchByMealPresenter.getSelectedCategory(itemName,getContext());
            }

        }

    }

    @Override
    public void getMealDetailsByArea() {
        if (getArguments() != null) {
            String itemName = getArguments().getString("areaName");
            if (itemName != null) {
                searchByMealPresenter.getSelectedArea(itemName,getContext());
            }
        }
    }

    @Override
    public void getMealDetailsByIngredient() {
        if (getArguments() != null) {
            String itemName = getArguments().getString("ingredientName");
            if (itemName != null) {
                searchByMealPresenter.getSelectedIngredient(itemName,getContext());
            }
        }
    }


    @Override
    public void showToast(String toast) {

    }


    @Override
    public void navigateToDetails(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", mealId);
        Navigation.findNavController(requireView()).navigate(R.id.action_searchByMealFragment_to_detailsScreenFragment, bundle);
    }
}