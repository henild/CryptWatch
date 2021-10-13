package com.example.cryptwatch.LoginInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cryptwatch.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    TextInputLayout  tilemail , tilusername , tilpassword;
    EditText email , username , password;
    Button btn_signup , btn_regtolog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hooks
        tilemail = (TextInputLayout) findViewById(R.id.til_email);
        tilusername = (TextInputLayout) findViewById(R.id.til_username);
        tilpassword = (TextInputLayout) findViewById(R.id.til_password);

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.password);

        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_regtolog = (Button) findViewById(R.id.btn_regtolog);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

       regToLog();
    }

    private void signUp() {

        String mail = email.getText().toString().trim();

        String uname = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (!isValidEmail(mail)){
            email.requestFocus();
            tilemail.setErrorEnabled(true);
            tilemail.setError("Enter valid name");

            tilemail.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilemail.setError(null);
                    tilemail.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        else if (TextUtils.isEmpty(uname)){
            username.requestFocus();
            tilusername.setErrorEnabled(true);
            tilusername.setError("Enter valid name");

            tilusername.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilusername.setError(null);
                    tilusername.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        else if (!isValidatePassword(pass)){
            password.requestFocus();
            tilpassword.setErrorEnabled(true);
            tilpassword.setError("Enter valid password");

            tilpassword.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilpassword.setError(null);
                    tilpassword.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        else {

        }
    }


    private boolean isValidEmail(String email){
        String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


    private boolean isValidatePassword(String password){
        String PASSWORD_PATTERN = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private void regToLog(){
        btn_regtolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }
}