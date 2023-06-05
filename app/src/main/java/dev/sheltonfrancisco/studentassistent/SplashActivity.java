package dev.sheltonfrancisco.studentassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dev.sheltonfrancisco.studentassistent.ui.auth.AuthActivity;
import dev.sheltonfrancisco.studentassistent.utils.Storage;

public class SplashActivity extends AppCompatActivity {

    private static int SPALH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);

        new Handler().postDelayed(() -> {
            // RUNNABLE FUNCTIONAL INTERFACE
            verifySession();
            finish();
        }, SPALH_TIME_OUT);
    }


    private void verifySession() {

        String token = Storage.getTokenFromSharedPreferences(getApplicationContext());
        if (token == null) {
            Intent intent = new Intent(this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}