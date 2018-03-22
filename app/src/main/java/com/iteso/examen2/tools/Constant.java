package com.iteso.examen2.tools;


public class Constant {
    public static final int TYPE_MAC = 0;
    public static final int TYPE_ALIENWARE = 1;
    public static final int TYPE_BLENDER = 2;
    public static final int TYPE_LANIX = 3;
    public static final int TYPE_TV = 4;
    public static final int TYPE_SPEAKERS = 5;
    public static final int TYPE_SHEETS = 6;
    public static final int TYPE_MICRO = 7;
    public static final int TYPE_PILLOWS = 8;
    public static final int TYPE_REFRI = 9;

    public static final int TYPE_BESTBUY = 0;
    public static final int TYPE_SEARS = 1;
    public static final int TYPE_FRYS = 2;


    public static final String EXTRA_PRODUCT = "PRODUCT";
    public static final String EXTRA_FRAGMENT = "FRAGMENT";
    public static final int FRAGMENT_TECHNOLOGY = 0;
    public static final int FRAGMENT_HOME = 1;
    public static final int FRAGMENT_ELECTRONICS = 2;

    public static final int INTENT_PRODUCTS_NOTIFY = 9999;

    // Table names
    public static final String TABLE_CITY = "city";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_STORE = "store";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_STOREPRODUCT = "storeProduct";

    // Columns Cities
    public static final String KEY_CITY_ID = "idCity";
    public static final String KEY_CITY_NAME = "name";

    // Columns Category
    public static final String KEY_CATEGORY_ID = "idCategory";
    public static final String KEY_CATEGORY_NAME = "name";

    // Columns Store
    public static final String KEY_STORE_ID = "idStore";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_CITY = "idCity";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LAT = "latitude";
    public static final String KEY_STORE_LNG = "longitude";

    // Columns Products
    public static final String KEY_PRODUCT_ID = "idProduct";
    public static final String KEY_PRODUCT_TITLE = "name";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_CATEGORY = "idCategory";

    // Columns StoreProducts
    public static final String KEY_STOREPRODUCT_ID = "idStoreProduct";
    public static final String KEY_STOREPRODUCT_PRODUCT = "idProduct";
    public static final String KEY_STOREPRODUCT_STORE = "idStore";

}
