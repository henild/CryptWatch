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
import com.example.cryptwatch.DataHolder;
import com.example.cryptwatch.R;

import java.util.ArrayList;
import java.util.Map;


public class FavouriteFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    CurrencyRVAdapter currencyRVAdapter;
    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeFragment.refreshDatabase();
        View view = inflater.inflate(R.layout.fragment_favourite,container,false);
        recyclerView = view.findViewById(R.id.recyclerview_fav);
        progressBar = view.findViewById(R.id.progressbar_fav);
        currencyRVModelArrayList = new ArrayList<>();
        currencyRVAdapter = new CurrencyRVAdapter(currencyRVModelArrayList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(currencyRVAdapter);
        favoriteList();
        return view;
    }

    private void favoriteList() {
        ArrayList<CurrencyRVModel> rvModelArrayList = new ArrayList<>();
        ArrayList<CurrencyRVModel> data = DataHolder.getInstance().getData();
        Map<String, Object> firebaseData = DataHolder.getInstance().getFirebaseData();
        for(CurrencyRVModel model: data) {
            if(firebaseData.containsKey(model.getCurrencySymbol())) {
                rvModelArrayList.add(model);
            }
        }
        currencyRVAdapter.favoriteList(rvModelArrayList);
        progressBar.setVisibility(View.GONE);

    }
}