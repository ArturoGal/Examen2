package com.iteso.examen2;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;

        import com.iteso.examen2.beans.Store;
        import com.iteso.examen2.beans.User;
        import com.iteso.examen2.database.DataBaseHandler;
        import com.iteso.examen2.database.StoreControl;

        import java.util.ArrayList;
        import java.util.Timer;
        import java.util.TimerTask;

        import static com.iteso.examen2.tools.Constant.*;

public class ActivitySplashScreen extends AppCompatActivity {
    DataBaseHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadUser();
                Intent intent;
                if(user.isLogged()){
                    intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                }else{
                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };
        dh = DataBaseHandler.getInstance(ActivitySplashScreen.this);
        loadStores();
        Timer timer = new Timer();
        timer.schedule(task, 2000);

    }

    private void loadStores() {
        ArrayList<Store> stores =  new StoreControl().getStores(dh);
        if (stores.size() == 0) addStores();
    }

    public void addStores(){
        SQLiteDatabase db = dh.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_STORE
                + " (" + KEY_STORE_NAME + "," + KEY_STORE_PHONE + ","
                + KEY_STORE_CITY + "," + KEY_STORE_THUMBNAIL + ","
                + KEY_STORE_LAT + "," + KEY_STORE_LNG
                + ") VALUES ('BESTBUY CIUDADELA', '01 800 237 8289', 1, 0, 20.6489713, -103.4207757)");

        db.execSQL("INSERT INTO " + TABLE_STORE
                + " (" + KEY_STORE_NAME + "," + KEY_STORE_PHONE + ","
                + KEY_STORE_CITY + "," + KEY_STORE_THUMBNAIL + ","
                + KEY_STORE_LAT + "," + KEY_STORE_LNG
                + ") VALUES ('SEARS LA GRAN PLAZA', '01 800 376 7683', 2, 1, 24.3791824, -109.73167952)");

        db.execSQL("INSERT INTO " + TABLE_STORE
                + " (" + KEY_STORE_NAME + "," + KEY_STORE_PHONE + ","
                + KEY_STORE_CITY + "," + KEY_STORE_THUMBNAIL + ","
                + KEY_STORE_LAT + "," + KEY_STORE_LNG
                + ") VALUES ('FRYS', '01 800 667 7319', 3, 2, 28.4673197, -104.3467817)");
    }

    public User loadUser(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.USER_PREFERENCES",
                        MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("NAME", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences = null;
        return user;
    }
}

