package com.iteso.examen2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.iteso.examen2.beans.Category;
import com.iteso.examen2.beans.City;
import com.iteso.examen2.beans.Store;

import static com.iteso.examen2.tools.Constant.*;

import java.util.ArrayList;

public class StoreControl {

    public void addStore(Store store, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STORE_ID, store.getId());
        values.put(KEY_STORE_NAME, store.getName());
        values.put(KEY_STORE_PHONE, store.getPhone());
        values.put(KEY_STORE_THUMBNAIL, store.getThumbnail());
        values.put(KEY_STORE_LAT, store.getLatitude());
        values.put(KEY_STORE_LNG, store.getLongitude());
        values.put(KEY_STORE_CITY, store.getCity().getId());
        db.insert(TABLE_STORE, null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;
    }

    public void deleteStore(String idStore, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(TABLE_STORE, KEY_STORE_ID + " = ?",
                new String[]{idStore});
        try{
            db.close();
        }catch(Exception e){
        }
        db = null;
    }
    public static Store getStoreById(int idStore, DataBaseHandler dh){
        Store store = new Store();
        SQLiteDatabase db = dh.getWritableDatabase();
        String select = "SELECT " + TABLE_STORE + "." + KEY_STORE_ID + ", " +
                                    TABLE_STORE + "." + KEY_STORE_NAME + ", " +
                                    TABLE_STORE + "." + KEY_STORE_PHONE + ", " +
                                    TABLE_STORE + "." + KEY_STORE_THUMBNAIL + ", " +
                                    TABLE_STORE + "." + KEY_STORE_LAT + ", " +
                                    TABLE_STORE + "." + KEY_STORE_LNG + ", " +
                                    TABLE_STORE + "." + KEY_STORE_CITY + ", " +
                                    TABLE_CITY + "." + KEY_CITY_NAME +
                        " FROM " + TABLE_STORE + ", " + TABLE_CITY +
                        " WHERE " + TABLE_STORE + "." + KEY_STORE_ID + " = " + idStore +
                        " AND " + TABLE_CITY + "." + KEY_CITY_ID + " = " + TABLE_STORE + "." + KEY_STORE_CITY;


        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToNext()) {
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));

            City city = new City();
            city.setId(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return store;
    }

    public ArrayList<Store> getStores(DataBaseHandler dh){
        ArrayList<Store> stores = new ArrayList<>();
        String select = " SELECT " +  TABLE_STORE + "." + KEY_STORE_ID + ", " +
                                        TABLE_STORE + "." + KEY_STORE_NAME + ", " +
                                        TABLE_STORE + "." + KEY_STORE_PHONE + ", " +
                                        TABLE_STORE + "." + KEY_STORE_THUMBNAIL + ", " +
                                        TABLE_STORE + "." + KEY_STORE_LAT + ", " +
                                        TABLE_STORE + "." + KEY_STORE_LNG + ", " +
                                        TABLE_STORE + "." + KEY_STORE_CITY + ", " +
                                        TABLE_CITY + "." + KEY_CITY_NAME +
                        " FROM " + TABLE_STORE + ", " + TABLE_CITY +
                        " WHERE " + TABLE_STORE + "." + KEY_STORE_CITY + " = " + TABLE_CITY + "." + KEY_CITY_ID;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
//        String selectCity;
//        Cursor cursorCity = null;
        while(cursor.moveToNext()){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));

//            selectCity = "SELECT " +  KEY_CITY_NAME +
//                        " FROM " + TABLE_CITY +
//                        " WHERE "  + KEY_CITY_ID + " = " + cursor.getInt(6);
//
//            cursorCity = db.rawQuery(selectCity, null);
//
            store.setCity(new City(cursor.getInt(6), cursor.getString(7)));
            stores.add(store);
        }
        try{
//            cursorCity.close();
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
//        cursorCity = null;
        return stores;
    }
}
