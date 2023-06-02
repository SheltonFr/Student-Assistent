package dev.sheltonfrancisco.studentassistent.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {


    public static void saveTokenToSharedPreferences(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String getTokenFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", context.MODE_PRIVATE);
        return sharedPreferences.getString("token", null);
    }

    public static boolean removeTokenFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", context.MODE_PRIVATE);
        return sharedPreferences.edit().clear().commit();
    }
}
