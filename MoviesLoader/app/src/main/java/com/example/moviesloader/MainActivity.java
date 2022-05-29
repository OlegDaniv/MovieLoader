package com.example.moviesloader;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainFragment();
    }

    private void setMainFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_main_holder, MainFragment.newInstance("Main Fragment"))
                .commit();
    }
}
