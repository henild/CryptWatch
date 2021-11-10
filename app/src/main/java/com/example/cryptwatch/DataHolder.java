package com.example.cryptwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {
    private ArrayList<CurrencyRVModel> data = new ArrayList<>();
    private Map<String, Object> firebaseData = new HashMap<>();
    public ArrayList<CurrencyRVModel> getData() {return data;}
    public Map<String, Object> getFirebaseData() {return firebaseData;}
    public void setData(ArrayList<CurrencyRVModel> data) {this.data = data;}
    public void setFirebaseData(Map<String, Object> data) {this.firebaseData = data;}

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}

}