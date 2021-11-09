package com.example.cryptwatch;

public class CurrencyRVModel {
    private String currencyName;
    private String currencySymbol;
    private double price;
    private double priceChangeIn24Hr;
    private boolean isFavorite;

    public CurrencyRVModel(String currencyName, String currencySymbol, double price, double priceChangeIn24hr, boolean isFavorite) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.price = price;
        this.priceChangeIn24Hr = priceChangeIn24hr;
        this.isFavorite = isFavorite;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceChangeIn24Hr() {
        return priceChangeIn24Hr;
    }

    public void setPriceChangeIn24Hr(double priceChangeIn24Hr) {
        this.priceChangeIn24Hr = priceChangeIn24Hr;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
