package com.example.rent.carsdatabase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by RENT on 2017-03-30.
 */

public class BetterCarDatabaseContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.rent.carsdatabase";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private CarsDatabaseOpenHelper openHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int CARS_MULTIPLE_ITEM = 1;
    public static final int CARS_SINGLE_ITEM = 2;

    static {
        uriMatcher.addURI(AUTHORITY, CarsTableContract.TABLE_NAME, CARS_MULTIPLE_ITEM);
        uriMatcher.addURI(AUTHORITY, CarsTableContract.TABLE_NAME + "/#", CARS_SINGLE_ITEM);
    }

    @Override
    public boolean onCreate() {
        openHelper = new CarsDatabaseOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case CARS_SINGLE_ITEM: {
                cursor = readableDatabase.query(CarsTableContract.TABLE_NAME,
                        projection,
                        CarsTableContract._ID + " = ?",
                        new String[]{uri.getLastPathSegment()},
                        null, null, sortOrder);

                break;
            }
            case CARS_MULTIPLE_ITEM: {
                cursor = readableDatabase.query(CarsTableContract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String type = null;
        switch (uriMatcher.match(uri)) {
            case CARS_SINGLE_ITEM: {
                type = "vnd.android.cursor.item/vnd.com.example.rent.carsdatabase.cars";
                break;
            }
            case CARS_MULTIPLE_ITEM: {
                type = "vnd.android.cursor.dir/vnd.com.example.rent.carsdatabase.cars";
            }
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = -1;
        switch (uriMatcher.match(uri)) {
            case CARS_MULTIPLE_ITEM: {
                id = openHelper.getWritableDatabase().insert(CarsTableContract.TABLE_NAME, null, values);
                break;
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uri.buildUpon().appendPath(String.valueOf(id)).build();
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase writeableDatabase = openHelper.getWritableDatabase();
        int deletedItems = 0;
        if(uriMatcher.match(uri) == CARS_SINGLE_ITEM){
            deletedItems = writeableDatabase.delete(CarsTableContract.TABLE_NAME,
                    CarsTableContract._ID + " = ?", new String[]{uri.getLastPathSegment()});
        }else if(uriMatcher.match(uri)== CARS_MULTIPLE_ITEM){
            deletedItems = writeableDatabase.delete(CarsTableContract.TABLE_NAME,selection,selectionArgs);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedItems;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // selection = WHERE
        SQLiteDatabase writeableDatabase = openHelper.getWritableDatabase();
        int updatedItems = 0;
        switch(uriMatcher.match(uri)){
            case CARS_SINGLE_ITEM:{
                updatedItems = writeableDatabase.update(CarsTableContract.TABLE_NAME,
                        values,
                        CarsTableContract._ID+ " = ?",
                        new String[] {uri.getLastPathSegment()});
                break;
            }
            case CARS_MULTIPLE_ITEM:{
                updatedItems = writeableDatabase.update(CarsTableContract.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            }

        }
        getContext().getContentResolver().notifyChange(uri,null);
        return updatedItems;
    }
}
