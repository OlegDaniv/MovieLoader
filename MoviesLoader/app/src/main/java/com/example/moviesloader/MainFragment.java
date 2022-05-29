package com.example.moviesloader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.moviesloader.databinding.FragmentMainBinding;
import com.example.moviesloader.movie.MovieLoaderFragment;
import com.example.moviesloader.writer.WhoWroteItFragment;

public class MainFragment extends Fragment implements View.OnClickListener {

    public final String FRAGMENT_NAME = this.getClass().getName();
    private FragmentMainBinding fragmentMainBinding;

    public static MainFragment newInstance(String text) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(fragment.FRAGMENT_NAME, text);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("NonConstantResourceId")
    private static Fragment resolveFragment(int viewID) {
        Fragment fragment = null;
        switch (viewID) {
            case R.id.button_main_fragment_who_write:
                fragment = WhoWroteItFragment.newInstance("Who Wrote it");
                break;
            case R.id.button_main_fragment_movies:
                fragment = MovieLoaderFragment.newInstance("Movie loader");
                break;
        }
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);
        fragmentMainBinding.buttonMainFragmentMovies.setOnClickListener(this);
        fragmentMainBinding.buttonMainFragmentWhoWrite.setOnClickListener(this);
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        requireActivity().setTitle(getArguments().getString(FRAGMENT_NAME));
    }

    @Override
    public void onClick(View v) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_main_holder, resolveFragment(v.getId()))
                .addToBackStack(FRAGMENT_NAME)
                .commit();
    }
}


