package com.example.addition.service;

import com.example.addition.models.ListRestaurants;
import com.example.addition.models.Restaurant;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestaurantService {

    @GET("/maps/api/place/nearbysearch/json")
    Call<ListRestaurants> getAllRestaurants(@QueryMap Map<String, String> params);


}
