package com.example.moviesloader.writer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;
import com.example.moviesloader.R;
import com.example.moviesloader.databinding.FragmentWhoWroteItBinding;

public class WhoWroteItFragment extends Fragment implements WhoWroteItCallBack {

    private FragmentWhoWroteItBinding whoWroteItBinding;

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
    public void postExecute(String title, String author) {
        whoWroteItBinding.titleText.setText(title);
        whoWroteItBinding.authorText.setText(author);
    }

    @Override
    public void postNull() {
        whoWroteItBinding.titleText.setText(R.string.no_results);
        whoWroteItBinding.authorText.setText("");
    }

    public void searchBooks() {
        String queryString = whoWroteItBinding.bookInput.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(whoWroteItBinding.searchButton.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            new FetchBook(this).execute(queryString);
            whoWroteItBinding.titleText.setText(R.string.loading);
            whoWroteItBinding.authorText.setText("");
        } else {
            if (queryString.length() == 0) {
                whoWroteItBinding.titleText.setText(R.string.no_search_term);
                whoWroteItBinding.authorText.setText("");
            } else {
                whoWroteItBinding.titleText.setText(R.string.no_network);
                whoWroteItBinding.authorText.setText("");
            }
        }

    }
}