package com.example.addition.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Restaurant {


    @SerializedName("name")
    private String name ;

    @SerializedName("rating")
    private String rating ;

    @SerializedName("icon")
    private String icon ;

    @SerializedName("formatted_address")
    private String formattedAddress ;

}
