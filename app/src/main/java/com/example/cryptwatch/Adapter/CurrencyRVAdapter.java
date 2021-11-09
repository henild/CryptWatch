package com.example.cryptwatch.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.Viewholder> {

    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;
    final private FirebaseAuth mAuth;
    final private FirebaseFirestore db;
    final private Context context;
    final private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public CurrencyRVAdapter(ArrayList<CurrencyRVModel> currencyRVModelArrayList, Context context) {
        this.currencyRVModelArrayList = currencyRVModelArrayList;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void filteredList(ArrayList<CurrencyRVModel> filteredList) {
        currencyRVModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyRVAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new CurrencyRVAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.Viewholder holder, int position) {
        CurrencyRVModel currencyRVModel = currencyRVModelArrayList.get(position);
        holder.currencyName.setText(currencyRVModel.getCurrencyName());
        holder.currencySymbol.setText(currencyRVModel.getCurrencySymbol());
        holder.currencyRate.setText("₹ "+decimalFormat.format(currencyRVModel.getPrice()));
        holder.currencyChangePercentage.setText(decimalFormat.format(currencyRVModel.getPriceChangeIn24Hr())+" %");
        holder.favbutton.setChecked(false);
        holder.favbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.favbutton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.ic_card_favourite));
                }
                else {
                    holder.favbutton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.ic_card_not_favourite));
                }
                updateFavourites(currencyRVModel.getCurrencySymbol(), isChecked);
            }
        });
    }

    private void updateFavourites(String symbol, boolean isChecked) {
        String userUID = mAuth.getCurrentUser().getUid();
        DocumentReference doc = db.collection("users").document(userUID);
        String ref = "favorites+" + symbol;
        Map<String, Object> updates = new HashMap<>();
        if(isChecked) {
           updates.put(ref, null);
           doc.set(updates, SetOptions.merge());
       } else {
           updates.put(ref, FieldValue.delete());
           doc.update(updates);
       }
    }

    @Override
    public int getItemCount() {
        return currencyRVModelArrayList.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {

        final private TextView currencyName , currencySymbol , currencyRate , currencyChangePercentage;
        ToggleButton favbutton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.card_name);
            currencySymbol = itemView.findViewById(R.id.card_symbol);
            currencyRate = itemView.findViewById(R.id.card_price);
            currencyChangePercentage = itemView.findViewById(R.id.card_percentage);
            favbutton = itemView.findViewById(R.id.button_fav);
        }
    }
}
