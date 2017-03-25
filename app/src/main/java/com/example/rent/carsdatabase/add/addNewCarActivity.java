package com.example.rent.carsdatabase.add;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rent.carsdatabase.Car;
import com.example.rent.carsdatabase.CarBuilder;
import com.example.rent.carsdatabase.CarsDatabaseOpenHelper;
import com.example.rent.carsdatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCarActivity extends AppCompatActivity {

    private CarsDatabaseOpenHelper carsDatabaseOpenHelper;
    private String imageUrl;

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.make)
    TextView make;
    @BindView(R.id.model)
    TextView model;
    @BindView(R.id.year)
    TextView year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car_activity);
        ButterKnife.bind(this);
        carsDatabaseOpenHelper = new CarsDatabaseOpenHelper(this);
    }

    @OnClick(R.id.add_car_button)
    void onAddCarButtonClick() {
        Car car = new CarBuilder()
                .setMake(make.getText().toString())
                .setModel(model.getText().toString())
                .setYear(Integer.parseInt(year.getText().toString()))
                .setImage(imageUrl)
                .createCar();
        boolean isAdded = carsDatabaseOpenHelper.insertCar(car);
        if (isAdded) {
            Toast.makeText(this, "Dodano nowy samochód!", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.add_image_button)
    void onAddImageButtonClick() {
       View promptView =  LayoutInflater.from(this).inflate(R.layout.dialog_prompt, null);
        EditText urlEditText = (EditText) findViewById(R.id.url_edit_text);

        new AlertDialog.Builder(this)
                .setView(promptView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        imageUrl = urlEditText.getText().toString();
                        Glide.with(AddNewCarActivity.this)
                                .load(imageUrl)
                                .into(image);
                    }
                }).show();
    }
}
