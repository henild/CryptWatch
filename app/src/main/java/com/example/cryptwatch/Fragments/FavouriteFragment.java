package com.example.cryptwatch.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cryptwatch.Adapter.CurrencyRVAdapter;
import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.R;

import java.util.ArrayList;


public class FavouriteFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    CurrencyRVAdapter currencyRVAdapter;
    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_fav);
        progressBar = view.findViewById(R.id.progressbar_fav);
        currencyRVModelArrayList = new ArrayList<>();
        currencyRVAdapter = new CurrencyRVAdapter(currencyRVModelArrayList,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(currencyRVAdapter);
        //getCurrencyData();
        CurrencyRVModel t1,t2,t3;
        t1 = new CurrencyRVModel("ABC","ABC",25000.00,10,true);
        t2 = new CurrencyRVModel("XYZ","ABC",25000.00,10,true);
        t3 = new CurrencyRVModel("PQR","ABC",25000.00,10,true);
        currencyRVModelArrayList.add(t1);
        currencyRVModelArrayList.add(t2);
        currencyRVModelArrayList.add(t3);
        return view;
    }
}