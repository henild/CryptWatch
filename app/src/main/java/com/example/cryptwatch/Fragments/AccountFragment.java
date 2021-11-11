package com.example.cryptwatch.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptwatch.CurrencyRVModel;
import com.example.cryptwatch.LoginInfo.Login;
import com.example.cryptwatch.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class AccountFragment extends Fragment {
    Button logout , changepassword;
    EditText username, email;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        logout = view.findViewById(R.id.btnlogout);
        username = view.findViewById(R.id.account_name);
        email = view.findViewById(R.id.account_email);
        changepassword = view.findViewById(R.id.btnchangepass);
        mAuth = FirebaseAuth.getInstance();

        username.setText(mAuth.getCurrentUser().getDisplayName());
        username.setFocusable(false);
        email.setText(mAuth.getCurrentUser().getEmail());
        email.setFocusable(false);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getActivity(), "Logout!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}