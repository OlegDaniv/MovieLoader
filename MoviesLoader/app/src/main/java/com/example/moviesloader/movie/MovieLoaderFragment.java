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

public class MovieLoaderFragment extends Fragment implements CallBackAsyncTask {

    protected static String[] names = null;
    protected static String[] descriptions = null;
    private FragmentMovieLoaderBinding movieLoaderBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieLoaderBinding = FragmentMovieLoaderBinding.inflate(inflater, container, false);
        movieLoaderBinding.buttonFindMovies.setOnClickListener(v -> {
            setArray();
            new GetMovies(this).execute();
        });
        return movieLoaderBinding.getRoot();
    }

    private void setArray() {
        names = getResources().getStringArray(R.array.name);
        descriptions = getResources().getStringArray(R.array.description);
    }

    @Override
    public void downloadStart() {
        movieLoaderBinding.progressBar.setVisibility(View.VISIBLE);
        movieLoaderBinding.textView.setText(R.string.wait_please);
    }

    @Override
    public void downloadFinished(ArrayList<Movie> result) {
        movieLoaderBinding.textView.setText(R.string.completed);
        movieLoaderBinding.progressBar.setVisibility(View.GONE);
        movieLoaderBinding.recyclerView.setVisibility(View.VISIBLE);
        movieLoaderBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieLoaderBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        movieLoaderBinding.recyclerView.setAdapter(new MoviesAdapter(result));
    }
}