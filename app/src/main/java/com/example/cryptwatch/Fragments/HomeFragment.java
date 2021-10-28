package com.example.cryptwatch.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cryptwatch.Adapter.CurrencyAdapter;
import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    EditText searchbar;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<CurrencyRVModel> currencyRVModelArrayList;
    CurrencyAdapter currencyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        //Hooks
        searchbar = (EditText) view.findViewById(R.id.searchbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        currencyRVModelArrayList = new ArrayList<>();
        currencyAdapter = new CurrencyAdapter(currencyRVModelArrayList,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(currencyAdapter);
        return view;
    }

    private void setCurrencyData(){
    }
}