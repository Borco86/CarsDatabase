package com.example.rent.carsdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FilterQueryProvider;

import com.example.rent.carsdatabase.add.AddNewCarActivity;
import com.example.rent.carsdatabase.listing.ListingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.autocomplete_text_view)
    AutoCompleteTextView autoCompleteTextView;

    private CarsDatabaseOpenHelper carsDatabaseOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        carsDatabaseOpenHelper = new CarsDatabaseOpenHelper(this);

        AutocompleteAdapter adapter = new AutocompleteAdapter(this, carsDatabaseOpenHelper.getAllItems());
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return carsDatabaseOpenHelper.searchQuerry(constraint);
            }
        });
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                autoCompleteTextView.setText(cursor.getString(cursor.getColumnIndex(CarsTableContract.COLUMN_MAKE)));
            }
        });

    }

    @OnClick(R.id.add_new_car)
    void onAddNewCarButtonClick() {
        Intent intent = new Intent(this, AddNewCarActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.search_button)
    void onSearchButtonClick() {
        startActivity(ListingActivity.createIntent(MainActivity.this, autoCompleteTextView.getText().toString()));
    }
}
