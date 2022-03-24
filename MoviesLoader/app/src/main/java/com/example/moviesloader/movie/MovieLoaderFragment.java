package com.example.moviesloader.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.moviesloader.R;
import com.example.moviesloader.databinding.FragmentMovieLoaderBinding;
import java.util.ArrayList;

public class MovieLoaderFragment extends Fragment implements MoviesDownloadCallback {

    protected static String[] names = null;
    protected static String[] descriptions = null;
    public final String FRAGMENT_NAME = this.getClass().getName();
    private FragmentMovieLoaderBinding movieLoaderBinding;

    public static MovieLoaderFragment newInstance(String text) {
        MovieLoaderFragment fragment = new MovieLoaderFragment();
        Bundle args = new Bundle();
        args.putString(fragment.FRAGMENT_NAME, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieLoaderBinding = FragmentMovieLoaderBinding.inflate(inflater, container, false);
        movieLoaderBinding.buttonFindMovies.setOnClickListener(v -> {
            setupInitialData();
            new GetMovies(this).execute();
        });
        return movieLoaderBinding.getRoot();
    }

    private void setupInitialData() {
        names = getResources().getStringArray(R.array.name);
        descriptions = getResources().getStringArray(R.array.description);
    }

    @Override
    public void onDownloadStarted() {
        movieLoaderBinding.progressView.setVisibility(View.VISIBLE);
        movieLoaderBinding.loadingText.setText(R.string.movie_loader_text_loading);
    }

    @Override
    public void onDownloadFinished(ArrayList<Movie> result) {
        movieLoaderBinding.loadingText.setText(R.string.movie_loader_text_done);
        movieLoaderBinding.progressView.setVisibility(View.GONE);
        movieLoaderBinding.moviesListView.setVisibility(View.VISIBLE);
        recyclerViewSetup(result);

    }

    private void recyclerViewSetup(ArrayList<Movie> arrayList) {
        movieLoaderBinding.moviesListView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieLoaderBinding.moviesListView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        movieLoaderBinding.moviesListView.setAdapter(new MoviesAdapter(arrayList));
    }
}
