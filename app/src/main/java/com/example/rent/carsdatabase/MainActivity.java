package com.example.rent.carsdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rent.carsdatabase.add.AddNewCarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Car car = new CarBuilder()
//                .setMake("Opel")
//                .setModel("Astra")
//                .setYear(1986)
//                .setImage("http://tralalala")
//                .createCar();
//
//        CarsDatabaseOpenHelper carsDatabaseOpenHelper = new CarsDatabaseOpenHelper(this);
//        carsDatabaseOpenHelper.insertCar(car);
    }

    @OnClick(R.id.add_new_car)
    void onAddNewCarButtonClick() {
        Intent intent = new Intent(this, AddNewCarActivity.class);
        startActivity(intent);
    }
}
