package com.example.cryptwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.cryptwatch.Fragments.AccountFragment;
import com.example.cryptwatch.Fragments.FavouriteFragment;
import com.example.cryptwatch.Fragments.HomeFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class DashBoard extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);
        getSupportActionBar().hide();

        chipNavigationBar = (ChipNavigationBar) findViewById(R.id.bottom_nav);
        chipNavigationBar.setItemSelected(R.id.home_icon,true);
        ReplaceFragment(new HomeFragment());
        bottomMenu();
    }

    private void bottomMenu(){

            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    switch (i){

                        case R.id.home_icon:
                            ReplaceFragment(new HomeFragment());
                            break;

                        case R.id.fav_icon:
                            ReplaceFragment(new FavouriteFragment());
                            break;

                        case R.id.acc_icon:
                            ReplaceFragment(new AccountFragment());
                            break;
                    }
                }
            });
    }

    public void ReplaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}