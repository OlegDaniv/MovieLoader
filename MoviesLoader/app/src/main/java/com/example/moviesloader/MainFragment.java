package com.example.moviesloader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.moviesloader.databinding.FragmentMainBinding;
import com.example.moviesloader.movie.MovieLoaderFragment;
import com.example.moviesloader.writer.WhoWroteItFragment;

public class MainFragment extends Fragment implements View.OnClickListener {

    private FragmentMainBinding fragmentMainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);
        fragmentMainBinding.buttonMoviesLoader.setOnClickListener(this);
        fragmentMainBinding.buttonWhoWriteIt.setOnClickListener(this);
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.button_who_write_it:
                fragment = new WhoWroteItFragment();
                break;
            case R.id.button_movies_loader:
                fragment = new MovieLoaderFragment();
                break;
        }
        if (fragment != null) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment)
                    .addToBackStack("MainFragment")
                    .commit();
        }
    }
}
