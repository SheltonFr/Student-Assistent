package dev.sheltonfrancisco.studentassistent.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import dev.sheltonfrancisco.studentassistent.MainActivity;
import dev.sheltonfrancisco.studentassistent.databinding.ActivityAuthBinding;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.adapters.AuthViewPagerAdapter;
import dev.sheltonfrancisco.studentassistent.ui.listeners.AuthEventListener;

public class AuthActivity extends AppCompatActivity implements AuthEventListener {

    private ActivityAuthBinding binding;
    private AuthViewPagerAdapter adapter;
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tabLayout = binding.authTabLayout;
        viewPager = binding.viewPager;
        fragmentManager = getSupportFragmentManager();
        adapter = new AuthViewPagerAdapter(fragmentManager, getLifecycle());

        setupViewpager();

    }


    public void setupViewpager() {
        tabLayout.addTab(tabLayout.newTab().setText("Sign In"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public void handleSignIn(String email, String password) {

        final ProgressDialog loadingDialog = new ProgressDialog(this);
        loadingDialog.startLoading();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    loadingDialog.dismissDialog();
                    if(task.isSuccessful()){
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        return;
                    }

                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                });

    }

    @Override
    public void handleRegistration(String email, String username, String passeord) {
        final ProgressDialog loadingDialog = new ProgressDialog(this);
        loadingDialog.startLoading();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, passeord)
                .addOnCompleteListener(task -> {
                    loadingDialog.dismissDialog();
                    if(task.isSuccessful()){

                        Log.i("TESTE", task.getResult().getUser().getUid());

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        return;
                    }


                })
                .addOnFailureListener(e -> {
                    if(e.getClass() == FirebaseAuthWeakPasswordException.class){
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(e.getClass() == FirebaseAuthInvalidCredentialsException.class) {
                        Toast.makeText(this, "Enter a vald email", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Log.e("TESTE", e.getMessage(), e);
                    Toast.makeText(this, "An error occured while creating user", Toast.LENGTH_LONG).show();
                });
    }
}