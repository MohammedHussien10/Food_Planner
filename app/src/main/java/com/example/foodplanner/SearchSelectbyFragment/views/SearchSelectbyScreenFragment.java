package com.example.foodplanner.SearchSelectbyFragment.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.foodplanner.SearchSelectbyFragment.presenter.SearchSelectbyContract;
import com.example.foodplanner.SearchSelectbyFragment.presenter.SearchSelectbyPresenter;
import com.example.foodplanner.db.MealsLocalDataSource;
import com.example.foodplanner.models.Area;
import com.example.foodplanner.models.Categories;
import com.example.foodplanner.models.Ingredients;
import com.example.foodplanner.models.MealsRepository;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class SearchSelectbyScreenFragment extends Fragment implements SearchSelectbyClickListener, SearchSelectbyContract {
    SearchSelectbyCategoryAdapter searchSelectbyCategoryAdapter;
    SearchSelectbyIngredientAdapter searchSelectbyIngredientAdapter;
    SearchSelectbyAreaAdapter searchSelectbyAreaAdapter;
    RecyclerView recyclerV_Selected_items;
    SearchSelectbyPresenter searchSelectbyPresenter;
    ChipGroup chipGroup;
    EditText earchbyitem;
    public SearchSelectbyScreenFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchSelectbyPresenter = new SearchSelectbyPresenter(this,
                MealsRepository.getInstance(MealsLocalDataSource
                        .getInstance(requireContext()), MealsRemoteDataSource.getInstance()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.searchby, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipGroup = view.findViewById(R.id.chipitems);
        earchbyitem = view.findViewById(R.id.et_searchbymeal);
        recyclerV_Selected_items = view.findViewById(R.id.searchbymeal_Rv);
        searchSelectbyPresenter.getCategory(getContext());
        searchSelectbyPresenter.getIngredient(getContext());
        searchSelectbyPresenter.getArea(getContext());
        searchSelectbyCategoryAdapter = new SearchSelectbyCategoryAdapter(getContext(), this);
        searchSelectbyIngredientAdapter = new SearchSelectbyIngredientAdapter(getContext(), this);
        searchSelectbyAreaAdapter = new SearchSelectbyAreaAdapter(getContext(), this);
//        recyclerV_Selected_items.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


//choose your chips
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                if (checkedId == R.id.Category_chip) {
                    recyclerV_Selected_items.setAdapter(searchSelectbyCategoryAdapter);
                } else if (checkedId == R.id.Ingredient_chip) {
                    recyclerV_Selected_items.setAdapter(searchSelectbyIngredientAdapter);
                } else if (checkedId == R.id.Country_chip) {
                    recyclerV_Selected_items.setAdapter(searchSelectbyAreaAdapter);
                }
            }
        });



        earchbyitem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String searchText = charSequence.toString().trim();

                RecyclerView.Adapter currentAdapter = recyclerV_Selected_items.getAdapter();

                if (currentAdapter instanceof SearchSelectbyCategoryAdapter) {
                    searchSelectbyCategoryAdapter.filter(searchText);

                } else if (currentAdapter instanceof SearchSelectbyIngredientAdapter) {
                    searchSelectbyIngredientAdapter.filter(searchText);
                }else {
                    searchSelectbyAreaAdapter.filter(searchText);
                }
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
    public void assignCategoryAdapter(List<Categories> Categories) {
        searchSelectbyCategoryAdapter.setcategoryList(Categories);
        searchSelectbyCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void assignIngredientAdapter(List<Ingredients> ingredients) {
        searchSelectbyIngredientAdapter.setIngredientsList(ingredients);
        searchSelectbyIngredientAdapter.notifyDataSetChanged();

    }

    @Override
    public void assignAreaAdapter(List<Area> area) {
        searchSelectbyAreaAdapter.setAreaList(area);
        searchSelectbyAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateCategoryToSearchbyMeal(String Category) {
        Bundle bundle = new Bundle();
        bundle.putString("categoryName", Category);
        Navigation.findNavController(requireView()).navigate(R.id.action_searchSelectbyScreenFragment_to_searchByMealFragment, bundle);
    }

    @Override
    public void navigateAreaToSearchbyMeal(String area) {
        Bundle bundle = new Bundle();
        bundle.putString("areaName", area);
        Navigation.findNavController(requireView()).navigate(R.id.action_searchSelectbyScreenFragment_to_searchByMealFragment, bundle);
    }

    @Override
    public void navigateIngredientToSearchbyMeal(String ingredient) {
        Bundle bundle = new Bundle();
        bundle.putString("ingredientName", ingredient);
        Navigation.findNavController(requireView()).navigate(R.id.action_searchSelectbyScreenFragment_to_searchByMealFragment, bundle);
    }


    @Override
    public void showToast(String toast) {

    }




}