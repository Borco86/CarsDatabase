package com.example.rent.carsdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RENT on 2017-03-25.
 */

public class CarsDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cars_database.db";
    private static int DATABASE_VERSION = 3;

    private static String SQL_CREATE_TABLE = "CREATE TABLE " + CarsTableContract.TABLE_NAME + " ("
            + CarsTableContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CarsTableContract.COLUMN_MAKE + " TEXT, "
            + CarsTableContract.COLUMN_MODEL + " TEXT, "
            + CarsTableContract.COLUMN_YEAR + " INTEGER, "
            + CarsTableContract.COLUMN_IMAGE + " TEXT)";

    private static String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + CarsTableContract.TABLE_NAME;

    public CarsDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    public boolean insertCar(Car car) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(CarsTableContract.COLUMN_MAKE, car.getMake());
        contentValues.put(CarsTableContract.COLUMN_MODEL, car.getModel());
        contentValues.put(CarsTableContract.COLUMN_YEAR, car.getYear());
        contentValues.put(CarsTableContract.COLUMN_IMAGE, car.getImage());

        long value = getWritableDatabase().insert(CarsTableContract.TABLE_NAME, null, contentValues);

        return value != -1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(SQL_DROP_TABLE);
            onCreate(db);
        }
    }
}
