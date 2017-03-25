package com.example.rent.carsdatabase.listing;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rent.carsdatabase.R;

public class ListingActivity extends AppCompatActivity {

    private static final String QUERRY = "querry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);


    }

    public static Intent createIntent(Context context, String query){
        Intent intent = new Intent(context, ListingActivity.class);
        intent.putExtra(QUERRY, query);
        return intent;
    }
}
