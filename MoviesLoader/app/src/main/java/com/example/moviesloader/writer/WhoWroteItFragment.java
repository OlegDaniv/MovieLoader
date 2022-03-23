package com.example.moviesloader.writer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.moviesloader.R;
import com.example.moviesloader.databinding.FragmentWhoWroteItBinding;
import java.util.ArrayList;

public class WhoWroteItFragment extends Fragment implements OnWhoWroteItCallBack {

    public final String FRAGMENT_NAME = this.getClass().getName();
    private FragmentWhoWroteItBinding whoWroteItBinding;

    public static WhoWroteItFragment newInstance(String text) {
        WhoWroteItFragment fragment = new WhoWroteItFragment();
        Bundle args = new Bundle();
        args.putString(fragment.FRAGMENT_NAME, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        whoWroteItBinding = FragmentWhoWroteItBinding.inflate(inflater, container, false);
        whoWroteItBinding.searchButton.setOnClickListener(v -> {
            searchBooks();
        });
        return whoWroteItBinding.getRoot();
    }

    @Override
    public void onExecute(ArrayList<Book> books) {
        whoWroteItBinding.titleText.setText(books.get(0).title);
        whoWroteItBinding.authorText.setText(books.get(0).authors);
    }

    @Override
    public void onNotFoundResults() {
        whoWroteItBinding.titleText.setText(R.string.no_results);
        whoWroteItBinding.authorText.setText("");
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(whoWroteItBinding.searchButton.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean checkNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void searchBooks() {
        String queryString = whoWroteItBinding.bookInput.getText().toString();
        hideKeyboard();
        if (!checkNetwork()) {
            Toast.makeText(getContext(), "Internet not Available", Toast.LENGTH_SHORT).show();
        }
        if (queryString.length() == 0) {
            whoWroteItBinding.titleText.setText(R.string.no_search_term);
            whoWroteItBinding.authorText.setText("");
        } else
            new FetchBook(this).execute(queryString);
        whoWroteItBinding.titleText.setText(R.string.loading);
        whoWroteItBinding.authorText.setText("");
    }
}