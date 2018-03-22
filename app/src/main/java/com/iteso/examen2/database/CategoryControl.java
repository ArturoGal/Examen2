package com.iteso.examen2.database;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import com.iteso.examen2.beans.Category;
        import java.util.ArrayList;

        import static com.iteso.examen2.tools.Constant.*;

public class CategoryControl {

    public void addCategory(Category category, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_ID, category.getId());
        values.put(KEY_CATEGORY_NAME, category.getName());
        db.insert(TABLE_CATEGORY, null, values);
        try{
            db.close();
        }catch(Exception e){

        }
        db = null;
        values = null;

    }

    public void deleteCategory(String idCategory, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.delete(TABLE_CATEGORY, KEY_CATEGORY_ID + " = ?",
                new String[]{idCategory});
        try{
            db.close();
        }catch(Exception e){
        }
        db = null;
    }

    public ArrayList<Category> getCategories(DataBaseHandler dh){
        ArrayList<Category> categories = new ArrayList<>();
        String select = "SELECT " + KEY_CATEGORY_ID + ", " +
                KEY_CATEGORY_NAME +
                " FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try{
            cursor.close(); //Siempre cerrar primero el cursor
            db.close();
        }catch(Exception e){
        }
        db = null;
        cursor = null;
        return categories;
    }
}