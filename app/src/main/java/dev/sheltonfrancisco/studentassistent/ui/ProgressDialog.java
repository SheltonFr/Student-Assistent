package dev.sheltonfrancisco.studentassistent.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import dev.sheltonfrancisco.studentassistent.R;

public class ProgressDialog {

    private Activity activity;
    private AlertDialog alertDialog;

    public ProgressDialog(Activity activity){
        this.activity = activity;
    }

    @SuppressLint("InflateParams")
    public void startLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_loader, null));

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissDialog() {
        alertDialog.dismiss();
    }
}
