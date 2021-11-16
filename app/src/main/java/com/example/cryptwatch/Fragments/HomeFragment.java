package com.example.cryptwatch.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.DataHolder;
import com.example.cryptwatch.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.cryptwatch.Adapter.CurrencyRVAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;


public class HomeFragment extends Fragment {
    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;

    SwipeRefreshLayout swipeRefreshLayout;
    EditText searchbar;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    CurrencyRVAdapter currencyRVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        refreshDatabase();

        //Hooks
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        searchbar = view.findViewById(R.id.searchbar);
        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progressbar);
        currencyRVModelArrayList = DataHolder.getInstance().getData();
        currencyRVAdapter = new CurrencyRVAdapter(DataHolder.getInstance().getData(), getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(currencyRVAdapter);
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCurrencies(s.toString());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getCurrencyData();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCurrencyData();
        refreshDatabase();
    }

    private void filterCurrencies(String query) {
        ArrayList<CurrencyRVModel> filteredList = new ArrayList<>();
        for(CurrencyRVModel item: currencyRVModelArrayList) {
            if(item.getCurrencyName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()) {
            Toast.makeText(getActivity(), "No currency found for this search query", Toast.LENGTH_SHORT).show();
        } else {
            currencyRVAdapter.filteredList(filteredList);
        }
    }

    public static void refreshDatabase() {
        FirebaseAuth mAuth;
        FirebaseFirestore db;
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        try {
            Log.d("HAHA", "refreshDatabase");
            db.collection("users").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DataHolder data = DataHolder.getInstance();
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            data.setFirebaseData(document.getData());
                            Log.d("HAHA", data.getFirebaseData().toString());
                        } else {
                            Log.d("HAHA", "No such document");
                        }
                    } else {
                        Log.d("HAHA", "Task failed");
                    }
                }
            });
        } catch (Exception e) {
            Log.d("HAHA", e.toString());
        }
    }

    private void getCurrencyData() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        String base_url = "https://api.coingecko.com/api/v3";
        String param_url = "/coins/markets?vs_currency=inr&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=24h";
        String url = base_url + param_url;
        currencyRVModelArrayList.clear();

        RequestQueue reqQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray dataArray) {
                try {
                    for(int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String currencyName = dataObj.getString("name");
                        String currencySymbol = dataObj.getString("symbol").toUpperCase(Locale.ROOT);
                        double price = dataObj.getDouble("current_price");
                        double priceChangeIn24Hr = dataObj.getDouble("price_change_percentage_24h");
                        currencyRVModelArrayList.add(new CurrencyRVModel(currencyName, currencySymbol, price, priceChangeIn24Hr, false));
                    }
                    currencyRVAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Fail to parse JSON data", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DEBUG", error.toString());
            }
        });
        reqQueue.add(jsonArrayRequest);
    }
}
