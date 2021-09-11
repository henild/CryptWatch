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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    TextInputLayout tilfullname , tilemail , tilnumber , tilusername , tilpassword;
    TextInputEditText fullname , email , number , username , password;
    Button btn_signup , btn_regtolog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        //Hooks
        tilfullname = (TextInputLayout) findViewById(R.id.til_fullname);
        tilemail = (TextInputLayout) findViewById(R.id.til_email);
        tilnumber = (TextInputLayout) findViewById(R.id.til_number);
        tilusername = (TextInputLayout) findViewById(R.id.til_username);
        tilpassword = (TextInputLayout) findViewById(R.id.til_password);

        fullname = (TextInputEditText) findViewById(R.id.fullname);
        email = (TextInputEditText) findViewById(R.id.email);
        number = (TextInputEditText) findViewById(R.id.number);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);

        btn_signup = (Button) findViewById(R.id.btnlogin);
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
        String name = fullname.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String mobile = number.getText().toString().trim();
        String uname = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(!isValidateName(name)){
            fullname.requestFocus();
            tilfullname.setErrorEnabled(true);
            tilfullname.setError("Enter valid name");

            tilfullname.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilfullname.setError(null);
                    tilfullname.setErrorEnabled(false);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        else if (!isValidEmail(mail)){
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
        else if (!isValidatePhone(mobile)){
            number.requestFocus();
            tilnumber.setErrorEnabled(true);
            tilnumber.setError("Enter valid name");

            tilnumber.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tilnumber.setError(null);
                    tilnumber.setErrorEnabled(false);
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
                    tilusername.setCounterEnabled(true);
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
            tilpassword.setError("Enter valid name");

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

    private boolean isValidateName(String name){
        String NAME_PATTERN = "^[a-zA-Z\\\\s]*$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    private boolean isValidEmail(String email){
        String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean isValidatePhone(String phone){
        String PHONE_PATTERN = "^[6-9]\\d{9}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);

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