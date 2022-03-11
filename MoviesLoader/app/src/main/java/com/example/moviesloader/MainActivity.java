package com.example.moviesloader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements CallBackAsyncTask {

    private TextView textView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MoviesAdapter(Movie.generateMovies(this)));
        button.setOnClickListener(view -> {
           new GetMovies(this).execute();
        });

    }

    @Override
    public void processCallBack(String output) {
        textView.setText(output);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void processFinished(String finished) {
        textView.setText(finished);
        recyclerView.setVisibility(View.VISIBLE);
    }
}