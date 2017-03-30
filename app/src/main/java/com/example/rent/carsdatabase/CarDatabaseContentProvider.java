package com.example.rent.carsdatabase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by RENT on 2017-03-29.
 */

public class CarDatabaseContentProvider extends ContentProvider {


    private static final String AUTHORITY = "com.example.rent.carsdatabase;";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int SINGLE_ITEM = 2;
    public static final int CAR_MULTIPLE_ITEM = 1;

    static {
        uriMatcher.addURI(AUTHORITY, CarsTableContract.TABLE_NAME, CAR_MULTIPLE_ITEM);
        uriMatcher.addURI(AUTHORITY, CarsTableContract.TABLE_NAME + "/#", SINGLE_ITEM);
    }

    private CarsDatabaseOpenHelper openHelper;


    @Override
    public boolean onCreate() {
        openHelper = new CarsDatabaseOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = -1;
        if (uriMatcher.match(uri) == SINGLE_ITEM) {
            id = openHelper.getWritableDatabase().insert(CarsTableContract.TABLE_NAME, null, values);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (uriMatcher.match(uri) == SINGLE_ITEM) {

        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
