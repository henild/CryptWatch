package com.example.cryptwatch.Adapter;

import android.content.Context;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.Viewholder> {

    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;
    ToggleButton favbutton;
    private Context context;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public CurrencyRVAdapter(ArrayList<CurrencyRVModel> currencyRVModelArrayList, Context context) {
        this.currencyRVModelArrayList = currencyRVModelArrayList;
        this.context = context;
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
        favbutton.findViewById(R.id.button_fav);
        CurrencyRVModel currencyRVModel = currencyRVModelArrayList.get(position);
        holder.currencyName.setText(currencyRVModel.getCurrencyName());
        holder.currencySymbol.setText(currencyRVModel.getCurrencySymbol());
        holder.currencyRate.setText("₹ "+decimalFormat.format(currencyRVModel.getPrice()));
        holder.currencyChangePercentage.setText(decimalFormat.format(currencyRVModel.getPriceChangeIn24Hr())+" %");
        holder.favbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    favbutton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.ic_card_favourite));
                }
                else {
                    favbutton.setBackgroundDrawable(ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.ic_card_not_favourite));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return currencyRVModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView currencyName , currencySymbol , currencyRate , currencyChangePercentage;
        ToggleButton favbutton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.card_name);
            currencySymbol = itemView.findViewById(R.id.card_symbol);
            currencyRate = itemView.findViewById(R.id.card_price);
            currencyChangePercentage = itemView.findViewById(R.id.card_percentage);
            favbutton = itemView.findViewById(R.id.button_fav);
            favbutton.setChecked(false);
        }
    }
}
