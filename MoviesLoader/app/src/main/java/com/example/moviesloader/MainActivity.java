package com.example.moviesloader;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moviesloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        setMainFragment();
    }

    private void setMainFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_frame_layout, new MainFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}