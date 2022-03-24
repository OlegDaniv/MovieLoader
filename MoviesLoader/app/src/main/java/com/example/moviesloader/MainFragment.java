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

    public final String FRAGMENT_NAME = this.getClass().getName();
    private FragmentMainBinding fragmentMainBinding;

    private static Fragment resolveFragment(int viewID) {
        Fragment fragment = null;
        switch (viewID) {
            case R.id.button_who_write_it:
                fragment = WhoWroteItFragment.newInstance("Who Wrote it");
                break;
            case R.id.button_movies_loader:
                fragment = MovieLoaderFragment.newInstance("Movie loader");
                break;
        }
        return fragment;
    }

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
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_frame_layout, resolveFragment(v.getId()))
                .addToBackStack(FRAGMENT_NAME)
                .commit();
    }
}


