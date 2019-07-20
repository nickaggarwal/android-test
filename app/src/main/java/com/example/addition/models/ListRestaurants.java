package com.example.addition.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;


@Data
public class ListRestaurants {


        @SerializedName("next_page_token")
        private String next_page_token ;

        @SerializedName("results")
        private List<Restaurant> restaurants ;

        @SerializedName("status")
        private String status ;

}
