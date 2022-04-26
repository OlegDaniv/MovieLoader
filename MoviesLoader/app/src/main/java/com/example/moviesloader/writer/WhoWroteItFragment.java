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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.moviesloader.R;
import com.example.moviesloader.databinding.FragmentWhoWroteItBinding;
import java.util.List;

public class WhoWroteItFragment extends Fragment implements BookResultCallBack {

    public final String FRAGMENT_NAME = this.getClass().getName();
    private FragmentWhoWroteItBinding binding;

    public static WhoWroteItFragment newInstance(String text) {
        WhoWroteItFragment fragment = new WhoWroteItFragment();
        Bundle args = new Bundle();
        args.putString(fragment.FRAGMENT_NAME, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWhoWroteItBinding.inflate(inflater, container, false);
        binding.buttonWhoWroteSearchBooks.setOnClickListener(v -> searchBooks());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getArguments().getString(FRAGMENT_NAME));
    }

    @Override
    public void onExecute(List<Book> books) {
        binding.textviewFragmentWhoWroteBooksName.setText(books.get(0).getTitle());
        binding.textviewFragmentWhoWroteAuthorName.setText(books.get(0).getAuthors());
    }

    @Override
    public void onResultsNotFound() {
        binding.textviewFragmentWhoWroteBooksName.setText(R.string.who_wrote_no_results);
        binding.textviewFragmentWhoWroteAuthorName.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(binding.buttonWhoWroteSearchBooks.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void searchBooks() {
        String queryString = binding.edittextFragmentWhoWrote.getText().toString();
        hideKeyboard();
        if (isNetworkAvailable() && queryString.length() > 0) {
            new FetchBook(this).execute(queryString);
            binding.textviewFragmentWhoWroteBooksName.setText(R.string.who_wrote_loading);
            binding.textviewFragmentWhoWroteBooksName.setVisibility(View.GONE);
        } else if (!isNetworkAvailable()) {
            Toast.makeText(getContext(), R.string.who_wrote_no_internet, Toast.LENGTH_SHORT).show();
        } else {
            binding.textviewFragmentWhoWroteBooksName.setText(R.string.who_wrote_no_term);
            binding.textviewFragmentWhoWroteAuthorName.setVisibility(View.GONE);
        }
    }
}
