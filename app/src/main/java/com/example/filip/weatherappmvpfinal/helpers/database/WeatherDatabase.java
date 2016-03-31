package com.example.filip.weatherappmvpfinal.helpers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.filip.weatherappmvpfinal.pojo.Main;
import com.example.filip.weatherappmvpfinal.pojo.Weather;
import com.example.filip.weatherappmvpfinal.pojo.WeatherResponse;
import com.example.filip.weatherappmvpfinal.pojo.Wind;

/**
 * Created by Filip on 27/03/2016.
 */
public class WeatherDatabase extends SQLiteOpenHelper {
    private final static String TABLE_WEATHER = "weather";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_CITY = "city";
    private final static String COLUMN_DESCRIPTION = "description";
    private final static String COLUMN_MAIN_TEMPERATURE = "temp";
    private final static String COLUMN_MAIN_MIN_TEMPERATURE = "temp_min";
    private final static String COLUMN_MAIN_MAX_TEMPERATURE = "temp_max";
    private final static String COLUMN_MAIN_HUMIDITY = "humidity";
    private final static String COLUMN_MAIN_PRESSURE = "pressure";
    private final static String COLUMN_WIND_SPEED = "speed";

    private final static String[] COLUMNS = {COLUMN_ID, COLUMN_CITY, COLUMN_DESCRIPTION, COLUMN_MAIN_TEMPERATURE, COLUMN_MAIN_MIN_TEMPERATURE, COLUMN_MAIN_MAX_TEMPERATURE, COLUMN_MAIN_HUMIDITY, COLUMN_MAIN_PRESSURE, COLUMN_WIND_SPEED};

    private final static String DATABASE_NAME = "WeatherDB";
    private final static int DATABASE_VERSION = 1;

    public WeatherDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WEATHER_TABLES = "CREATE TABLE " + TABLE_WEATHER
                + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CITY + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_MAIN_TEMPERATURE + " DOUBLE, "
                + COLUMN_MAIN_MIN_TEMPERATURE + " DOUBLE, "
                + COLUMN_MAIN_MAX_TEMPERATURE + " DOUBLE, "
                + COLUMN_MAIN_HUMIDITY + " INTEGER, "
                + COLUMN_MAIN_PRESSURE + " DOUBLE, "
                + COLUMN_WIND_SPEED + " DOUBLE "
                + ")";
        db.execSQL(CREATE_WEATHER_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS accounts");
        this.onCreate(db);
    }

    public void addWeatherResponseToDatabase(WeatherResponse responseToStore, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = createValues(responseToStore, city);
        if (values != null)
            db.insert(TABLE_WEATHER, null, values);
        db.close();
    }

    public void updateWeatherResponseInDatabase(WeatherResponse responseToUpdate, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        String args[] = new String[]{city};
        ContentValues values = createValues(responseToUpdate, city);
        if (values != null)
            db.update(TABLE_WEATHER, values, COLUMN_CITY + "=?", args); //update where the city column values equals city to edit
        db.close();
    }

    public void deleteAResponseFromDatabase(String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        String args[] = new String[]{city};
        db.delete(TABLE_WEATHER, COLUMN_CITY + "=?", args);
        db.close();
    }

    private ContentValues createValues(WeatherResponse response, String city) {
        ContentValues values = new ContentValues();
        Weather weather = response.getWeatherObject();
        Main main = response.getMain();
        Wind wind = response.getWind();
        if (weather != null && wind != null && main != null) {
            values.put(COLUMN_CITY, city);
            values.put(COLUMN_DESCRIPTION, weather.getDescription());
            values.put(COLUMN_MAIN_TEMPERATURE, main.getTemp());
            values.put(COLUMN_MAIN_MIN_TEMPERATURE, main.getTemp_min());
            values.put(COLUMN_MAIN_MAX_TEMPERATURE, main.getTemp_max());
            values.put(COLUMN_MAIN_HUMIDITY, main.getHumidity());
            values.put(COLUMN_MAIN_PRESSURE, main.getPressure());
            values.put(COLUMN_WIND_SPEED, wind.getSpeed());
            return values;
        }
        return null;
    }

    public WeatherResponse getWeatherResponseForCertainCity(String city) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WEATHER, COLUMNS, null, null, null, null, null, null);
        WeatherResponse response = new WeatherResponse();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (cursor.getString(1).equalsIgnoreCase(city)) {
                Weather weather = new Weather(null, cursor.getString(2));
                Main main = new Main(cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(5), cursor.getInt(6), cursor.getDouble(7));
                Wind wind = new Wind(cursor.getDouble(8), 0);
                response.setMain(main);
                response.setWeatherObject(weather);
                response.setWind(wind);
            }
        }
        cursor.close();
        db.close();
        return response;
    }

    public boolean checkIfLocationIsCachedInDatabase(String city) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WEATHER, COLUMNS, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (cursor.getString(1).equalsIgnoreCase(city)) {
                cursor.close();
                db.close();
                return true;
            }
        }
        return false;
    }
}