package com.example.rent.carsdatabase;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by RENT on 2017-03-25.
 */

public class CarsTableContract implements BaseColumns{

    public static String TABLE_NAME = "CARS";
    public static String COLUMN_MAKE = "make";
    public static String COLUMN_MODEL = "model";
    public static String COLUMN_YEAR = "year";
    public static String COLUMN_IMAGE = "image";
    public static final Uri DATA_CONTENT_URI = BetterCarDatabaseContentProvider.CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
}
