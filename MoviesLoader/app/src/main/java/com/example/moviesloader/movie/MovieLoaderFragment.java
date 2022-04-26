package com.example.moviesloader.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.moviesloader.R;
import com.example.moviesloader.databinding.FragmentMovieLoaderBinding;
import java.util.ArrayList;

public class MovieLoaderFragment extends Fragment implements MoviesDownloadCallback {

    public final String FRAGMENT_NAME = this.getClass().getName();
    protected String[] names = null;
    protected String[] descriptions = null;
    private FragmentMovieLoaderBinding binding;

    public static MovieLoaderFragment newInstance(String text) {
        MovieLoaderFragment fragment = new MovieLoaderFragment();
        Bundle args = new Bundle();
        args.putString(fragment.FRAGMENT_NAME, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieLoaderBinding.inflate(inflater, container, false);
        binding.buttonFragmentMovieFindMovies.setOnClickListener(v -> {
            setupInitialData();
            new GetMovies(this).execute(names,descriptions);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().setTitle(getArguments().getString(FRAGMENT_NAME));
    }

    private void setupInitialData() {
        names = getResources().getStringArray(R.array.name);
        descriptions = getResources().getStringArray(R.array.description);
    }

    @Override
    public void onDownloadStarted() {
        binding.progressbarFragmentMovieLoading.setVisibility(View.VISIBLE);
        binding.textviewFragmentMovieLoadingText.setText(R.string.movie_loader_text_loading);
    }

    @Override
    public void onDownloadFinished(ArrayList<Movie> result) {
        binding.textviewFragmentMovieLoadingText.setText(R.string.movie_loader_text_done);
        binding.progressbarFragmentMovieLoading.setVisibility(View.GONE);
        binding.recyclerviewFragmentMovieListItems.setVisibility(View.VISIBLE);
        setupRecyclerView(result);
    }

    private void setupRecyclerView(ArrayList<Movie> arrayList) {
        binding.recyclerviewFragmentMovieListItems.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerviewFragmentMovieListItems.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerviewFragmentMovieListItems.setAdapter(new MoviesAdapter(arrayList));
    }

}
