package com.example.moviesloader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements CallBackAsyncTask {

    protected static String[] names = null;
    protected static String[] descriptions = null;
    private int progress;
    private TextView textView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setArray();
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        button.setOnClickListener(view -> {
            progress = 0;
            new GetMovies(this).execute(Movie.generateMovies().toArray(new Movie[0]));
            setAdapter();
        });
    }


    private void setArray() {
        names = getResources().getStringArray(R.array.name);
        descriptions = getResources().getStringArray(R.array.description);
    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MoviesAdapter(Movie.generateMovies()));
    }

    @Override
    public void downloadStart() {
        progressBar.setProgress(0);
        progressBar.setMax(Movie.generateMovies().size());
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void downloadFinished() {
        textView.setText("completed");
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void downloadOverview(Movie movie) {
        textView.setText("Please wait... \n we found " + progress + " movies\n" + movie.getName());
        progressBar.setProgress(progress++);
    }
}