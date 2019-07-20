package com.example.addition;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.addition.adapters.RestaurantsAdapter;
import com.example.addition.interfaces.RetrofitClientInstance;
import com.example.addition.models.ListRestaurants;
import com.example.addition.models.Restaurant;
import com.example.addition.service.RestaurantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HelloWorldActivity extends AppCompatActivity {


    // Variable Declaration
    private RestaurantsAdapter restaurantsAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        //ConfigHelper.getConfigValue(this, "api_url");

        progressDialog = new ProgressDialog(HelloWorldActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        // Create Interface and Restaurant service
        RestaurantService restaurantService = RetrofitClientInstance.getRetrofitInstance().create(RestaurantService.class);
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("location", "47.6204,-122.3491");
        paramsMap.put("radius", "2500");
        paramsMap.put("type", "restaurant");
        paramsMap.put("key", "AIzaSyAIux_9gVtovYz4EOfxouSI5GXpkcT5KKs");
        Call<ListRestaurants> call = restaurantService.getAllRestaurants(paramsMap);
        call.enqueue(new Callback<ListRestaurants>() {
            @Override
            public void onResponse(Call<ListRestaurants> call, Response<ListRestaurants> response) {
                progressDialog.dismiss();
                Log.i("MAIN", response.body().toString() );
                generateDataList(response.body().getRestaurants());
            }

            @Override
            public void onFailure(Call<ListRestaurants> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HelloWorldActivity.this, "Something Went Worong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Restaurant> photoList) {
        recyclerView = findViewById(R.id.custom_recycler_view);
        restaurantsAdapter = new RestaurantsAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelloWorldActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(restaurantsAdapter);
    }
}
