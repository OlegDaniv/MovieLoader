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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallBackAsyncTask {

    protected static String[] names = null;
    protected static String[] descriptions = null;
    private TextView textView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        setArray();
        button.setOnClickListener(view -> {
            new GetMovies(this).execute();
        });
    }

    private void setArray() {
        names = getResources().getStringArray(R.array.name);
        descriptions = getResources().getStringArray(R.array.description);
    }

    @Override
    public void downloadStart() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Wait please");
    }

    @Override
    public void downloadFinished(ArrayList<Movie> result) {
        textView.setText("completed");
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MoviesAdapter(result));
    }
}