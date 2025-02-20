package com.example.foodplanner.network;

import com.example.foodplanner.homescreenfragment.presenter.HomeContract;
import com.example.foodplanner.models.Meals;
import com.example.foodplanner.models.MealsResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {
    List<Meals> listOfMeals;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    MealsService service;
    private static MealsRemoteDataSource remoteSource;

    private MealsRemoteDataSource(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(MealsService.class);
    }


    public static MealsRemoteDataSource getInstance(){

        if(remoteSource == null){
            remoteSource = new MealsRemoteDataSource();
        }
        return remoteSource;
    }

   public Single<MealsResponse> getRandomMeals(){
        return service.getRandomMeals();
   }

}

//public void getRandomMeal(HomeContract homeContract){
//    MealsService mealsService = MealsRemoteDataSource.getRetrofitConnection().create(MealsService.class);
//    Call<MealsResponse> call = mealsService.getRandomMeals();
//    call.enqueue(new Callback<MealsResponse>() {
//        @Override
//        public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
//            if(response.isSuccessful() && response.body() != null){
//                listOfMeals.addAll(response.body().getMeals());
//                homeContract.assignAdapter(listOfMeals);
//
//            }else {
//                homeContract.showToast(response.errorBody().toString());
//            }
//        }
//
//        @Override
//        public void onFailure(Call<MealsResponse> call, Throwable throwable) {
//            throwable.printStackTrace();
//
//        }
//    });
//}
