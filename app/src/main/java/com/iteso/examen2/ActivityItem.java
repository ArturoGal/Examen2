package com.iteso.examen2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.examen2.beans.Category;
import com.iteso.examen2.beans.ItemProduct;
import com.iteso.examen2.beans.Store;
import com.iteso.examen2.database.CategoryControl;
import com.iteso.examen2.database.DataBaseHandler;
import com.iteso.examen2.database.ItemProductControl;
import com.iteso.examen2.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {
    protected Spinner stores;
    protected Spinner categories;
    protected Spinner images;
    protected EditText title;
    protected ArrayAdapter<Store> storesAdapter;
    protected ArrayAdapter<Category> categoriesAdapter;
    protected ArrayAdapter<String> imagesAdapter;
    protected DataBaseHandler dh; //DataBase Instance
    protected Store storeSelected; //Store selected in spinner
    protected Category categorySelected; //Category selected in spinner
    protected int imageSelected; //Image selected in spinner
    protected Button save_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        stores = findViewById(R.id.activity_item_store);
        categories = findViewById(R.id.activity_item_category);
        images = findViewById(R.id.activity_item_image);
        title = findViewById(R.id.activity_item_title);
        save_btn = findViewById(R.id.activity_item_save);

        storeSelected = null;
        categorySelected = null;
        imageSelected = -1;

//DataBase Objects
        dh = DataBaseHandler.getInstance(this);
        StoreControl storeControl = new StoreControl();
        CategoryControl categoryControl = new CategoryControl();

//Fill info from Database
        ArrayList<Store> storesList = storeControl.getStores(dh);
        ArrayList<Category> categoriesList = categoryControl.getCategories(dh);

//Create Adapter to show into Spinner, ListView or GridLayout
        storesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storesList);
        stores.setAdapter(storesAdapter);
        categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoriesList);
        categories.setAdapter(categoriesAdapter);



        stores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeSelected = storesAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorySelected = categoriesAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        images.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    ItemProduct itemProduct = new ItemProduct();

                    itemProduct.setCode(DataBaseHandler.ITEMPRODUCT_ID);
                    DataBaseHandler.ITEMPRODUCT_ID++;
                    itemProduct.setTitle(title.getText().toString().trim());
                    itemProduct.setStore(storeSelected);
                    itemProduct.setCategory(categorySelected);
                    itemProduct.setImage(imageSelected);
                    ItemProductControl itemProductControl = new ItemProductControl();
                    itemProductControl.addItemProduct(itemProduct, dh);

                    Intent intent = new Intent();
                    intent.putExtra("ITEM", itemProduct);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

            }
        });

    }
    private boolean checkFields() {
        if (title.getText().toString().isEmpty()) {
            Toast.makeText(ActivityItem.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
