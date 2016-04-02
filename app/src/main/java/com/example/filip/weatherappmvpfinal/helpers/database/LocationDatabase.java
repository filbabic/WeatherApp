package com.example.filip.weatherappmvpfinal.helpers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;

import java.util.ArrayList;

/**
 * Created by Filip on 10/02/2016.
 */
public class LocationDatabase extends SQLiteOpenHelper {

    private static final String TABLE_LOCATIONS = "locations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CITY = "city";

    private static final String DATABASE_NAME = "locations";
    private static final int DATABASE_VERSION = 1;

    public LocationDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CITIES_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CITY +
                " TEXT " + ")";
        db.execSQL(CREATE_CITIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS locations");
        this.onCreate(db);
    }

    public ArrayList<LocationWrapper> getLocations() {
        ArrayList<LocationWrapper> locationWrappers = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_LOCATIONS, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            LocationWrapper locationWrapper = new LocationWrapper(city);
            locationWrappers.add(locationWrapper);
        }
        cursor.close();
        database.close();
        return locationWrappers;
    }

    public void addLocation(@NonNull String locationName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CITY, locationName);

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(TABLE_LOCATIONS, null, contentValues);
        database.close();
    }

    public void deleteLocation(String locationName) {
        String[] arg = new String[]{locationName};

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LOCATIONS, COLUMN_CITY + "=?", arg);
        db.close();
    }

}