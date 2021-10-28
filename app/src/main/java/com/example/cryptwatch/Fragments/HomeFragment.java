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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.R;

import org.json.JSONObject;

import com.example.cryptwatch.Adapter.CurrencyAdapter;
import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;

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

    private void getCurrencyData() {
        String base_url = "https://api.coingecko.com/api/v3";
        String param_url = "/coins/markets?vs_currency=inr&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=24h";
        String url = base_url + param_url;

        RequestQueue reqQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        reqQueue.add(jsonObjectRequest);

    }
}
