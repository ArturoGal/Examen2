package com.iteso.examen2;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.iteso.examen2.beans.User;

        import java.util.Timer;
        import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

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
        Timer timer = new Timer();
        timer.schedule(task, 2000);

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

