package dev.sheltonfrancisco.studentassistent.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import dev.sheltonfrancisco.studentassistent.MainActivity;
import dev.sheltonfrancisco.studentassistent.api.RetrofitConfig;
import dev.sheltonfrancisco.studentassistent.api.requests.AuthBody;
import dev.sheltonfrancisco.studentassistent.api.responses.AuthResponse;
import dev.sheltonfrancisco.studentassistent.databinding.ActivityAuthBinding;
import dev.sheltonfrancisco.studentassistent.ui.ProgressDialog;
import dev.sheltonfrancisco.studentassistent.ui.adapters.AuthViewPagerAdapter;
import dev.sheltonfrancisco.studentassistent.ui.listeners.AuthEventListener;
import dev.sheltonfrancisco.studentassistent.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        AuthBody body = new AuthBody(email, password);
        Call<AuthResponse> userCall = new RetrofitConfig().getUserService().login(body);

        userCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                loadingDialog .dismissDialog();
                if (response.isSuccessful() && response.code() == 200) {
                    AuthResponse responseToken = response.body();
                    String token = responseToken.getToken();
                    Storage.saveTokenToSharedPreferences(getApplicationContext(), token);
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                Toast.makeText(AuthActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("ERROR", t.getMessage(), t);
            }
        });

    }

    @Override
    public void handleRegistration(String email, String username, String password) {

        final ProgressDialog loadDialog = new ProgressDialog(this);
        loadDialog.startLoading();

        AuthBody body = new AuthBody(username, email, password);
        Call<AuthResponse> userCall = new RetrofitConfig().getUserService().create(body);

        userCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                loadDialog.dismissDialog();
                if (response.isSuccessful() && response.code() == 201) {
                    AuthResponse responseToken = response.body();
                    String token = responseToken.getToken();
                    Storage.saveTokenToSharedPreferences(getApplicationContext(), token);
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                Toast.makeText(AuthActivity.this, "An error occurred!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("ERROR", t.getMessage(), t);
            }
        });
    }


}