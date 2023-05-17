package dev.sheltonfrancisco.studentassistent.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dev.sheltonfrancisco.studentassistent.ui.auth.LoginFragment;
import dev.sheltonfrancisco.studentassistent.ui.auth.RegisterFragment;

public class AuthViewPagerAdapter extends FragmentStateAdapter {
    public AuthViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new LoginFragment() : new RegisterFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
