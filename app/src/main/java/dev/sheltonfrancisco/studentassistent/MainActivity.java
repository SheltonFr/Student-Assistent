package dev.sheltonfrancisco.studentassistent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Calendar;

import dev.sheltonfrancisco.studentassistent.database.AppDatabase;
import dev.sheltonfrancisco.studentassistent.database.dao.TaskDao;
import dev.sheltonfrancisco.studentassistent.databinding.ActivityMainBinding;
import dev.sheltonfrancisco.studentassistent.models.Task;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.auth.AuthActivity;
import dev.sheltonfrancisco.studentassistent.ui.create.CreateActivity;

public class MainActivity extends AppCompatActivity {


    public static String FABS_TAG = "CREATION_TAG";
    public static String SUBJECT_TAG = "SUBJECT";
    public static String TASK_TAG = "TASK";
    private final String DATABASE_NAME = "student-assistent";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private AppDatabase db;
    private TaskDao taskDao;
    private boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        taskDao = db.taskDao();

        verifySession();


        setupFabs();
        // Drawer
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_agenda, R.id.nav_subject)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            final ProgressDialog dialog = new ProgressDialog(this);
            Handler handler = new Handler();

            dialog.startLoading();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseAuth.getInstance().signOut();
                    dialog.dismissDialog();
                    verifySession();
                }
            }, 2500);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void verifySession() {
        if (FirebaseAuth.getInstance().getUid() == null) {
            Intent intent = new Intent(this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    private void setupFabs() {

        Animation close, open, clock, antiClok;

        close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        antiClok = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

        binding.appBarMain.fab.setOnClickListener(e -> {
            if (isFabOpen) {
                binding.appBarMain.fabSub.setVisibility(View.INVISIBLE);
                binding.appBarMain.fabTask.setVisibility(View.INVISIBLE);
                binding.appBarMain.fabSub.startAnimation(close);
                binding.appBarMain.fabTask.startAnimation(close);
                binding.appBarMain.fab.startAnimation(antiClok);
                binding.appBarMain.fabSub.setClickable(false);
                binding.appBarMain.fabTask.setClickable(false);
            } else {
                binding.appBarMain.fabSub.setVisibility(View.VISIBLE);
                binding.appBarMain.fabTask.setVisibility(View.VISIBLE);
                binding.appBarMain.fabSub.startAnimation(open);
                binding.appBarMain.fabTask.startAnimation(open);
                binding.appBarMain.fab.startAnimation(clock);
                binding.appBarMain.fabSub.setClickable(true);
                binding.appBarMain.fabTask.setClickable(true);
            }

            isFabOpen = !isFabOpen;
        });

        binding.appBarMain.fabTask.setOnClickListener(e -> {
            Intent intent = new Intent(this, CreateActivity.class);
            intent.putExtra(FABS_TAG, TASK_TAG);
            isFabOpen = !isFabOpen;
            startActivity(intent);
        });

        binding.appBarMain.fabSub.setOnClickListener(e -> {
            Intent intent = new Intent(this, CreateActivity.class);
            intent.putExtra(FABS_TAG, SUBJECT_TAG);
            isFabOpen = !isFabOpen;
            startActivity(intent);
        });
    }
}