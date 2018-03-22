package com.iteso.examen2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.iteso.examen2.beans.Category;
import com.iteso.examen2.beans.ItemProduct;

import static com.iteso.examen2.tools.Constant.*;

import java.util.ArrayList;

public class ItemProductControl {

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_ID, itemProduct.getCode());
        values.put(KEY_PRODUCT_TITLE, itemProduct.getTitle());
        values.put(KEY_PRODUCT_IMAGE, itemProduct.getImage());
        values.put(KEY_PRODUCT_CATEGORY, itemProduct.getCategory().getId());
        db.insert(TABLE_PRODUCT, null, values);
        values = new ContentValues();
        values.put(KEY_STOREPRODUCT_ID, DataBaseHandler.STOREPRODUCT_ID);
        values.put(KEY_STOREPRODUCT_PRODUCT, itemProduct.getCode());
        values.put(KEY_STOREPRODUCT_STORE, itemProduct.getStore().getId());
        db.insert(TABLE_STOREPRODUCT, null, values);
        DataBaseHandler.STOREPRODUCT_ID ++;

        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;
    }


    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> products = new ArrayList<>();
        String select = "SELECT " + TABLE_PRODUCT + "." + KEY_PRODUCT_ID + ", " +
                                    TABLE_PRODUCT + "." + KEY_PRODUCT_TITLE + ", " +
                                    TABLE_PRODUCT + "." + KEY_PRODUCT_IMAGE + ", " +
                                    TABLE_STOREPRODUCT + "." + KEY_STOREPRODUCT_STORE + ", " +
                                    TABLE_CATEGORY + "." + KEY_CATEGORY_NAME +
                        " FROM " + TABLE_PRODUCT + ", " + TABLE_STOREPRODUCT + ", " + TABLE_STORE +  ", " + TABLE_CATEGORY +
                        " WHERE " + TABLE_PRODUCT + "." + KEY_PRODUCT_CATEGORY + " = " + idCategory +
                        " AND " + TABLE_STOREPRODUCT + "." + KEY_STOREPRODUCT_STORE + " = " + TABLE_STORE + "." + KEY_STORE_ID +
                        " AND " + TABLE_STOREPRODUCT + "." + KEY_STOREPRODUCT_PRODUCT + " = " + TABLE_PRODUCT + "." + KEY_PRODUCT_ID +
                        " AND " + TABLE_PRODUCT + "." + KEY_PRODUCT_CATEGORY + " = " + TABLE_CATEGORY + "." + KEY_CATEGORY_ID ;

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            ItemProduct product = new ItemProduct();
            product.setCode(cursor.getInt(0));
            product.setTitle(cursor.getString(1));
            product.setImage(cursor.getInt(2));
            product.setCategory(new Category(idCategory, cursor.getString(4)));
            product.setStore(StoreControl.getStoreById(cursor.getInt(3), dh));
            products.add(product);
        }
        try{

            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return products;
    }
}
