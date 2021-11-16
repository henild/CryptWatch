package com.example.cryptwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword extends AppCompatActivity {

    TextInputLayout til_current_password , til_new_password , til_confirm_new_password;
    EditText current_password , new_password , confirm_new_password;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        current_password = (EditText) findViewById(R.id.forgot_currpass);
        new_password = (EditText) findViewById(R.id.forgot_newpass);
        confirm_new_password = (EditText) findViewById(R.id.forgot_connewpass);
        update = (Button) findViewById(R.id.btnupdate);
        til_current_password = (TextInputLayout) findViewById(R.id.forgot_til_currpass);
        til_new_password = (TextInputLayout) findViewById(R.id.forgot_til_newpass);
        til_confirm_new_password = (TextInputLayout) findViewById(R.id.forgot_til_connewpass);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currpass = current_password.getText().toString().trim();
                String newpass = new_password.getText().toString().trim();
                String confnewpass = confirm_new_password.getText().toString().trim();

                if(TextUtils.isEmpty(currpass)){
                    current_password.requestFocus();
                    til_current_password.setErrorEnabled(true);
                    til_current_password.setError("Enter Current Password");
                    til_current_password.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            til_current_password.setError(null);
                            til_current_password.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                else if(!isValidatePassword(newpass)){
                    new_password.requestFocus();
                    til_new_password.setErrorEnabled(true);
                    til_new_password.setError("Password not strong!");
                    til_new_password.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            til_new_password.setError(null);
                            til_new_password.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                else if (!confnewpass.equals(newpass)){
                    confirm_new_password.requestFocus();
                    til_confirm_new_password.setErrorEnabled(true);
                    til_confirm_new_password.setError("Password does not match!");
                    til_confirm_new_password.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            til_confirm_new_password.setError(null);
                            til_confirm_new_password.setErrorEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                else {
                    //Update in Firebase
                }
            }
        });

    }

    private boolean isValidatePassword(String password){
        String PASSWORD_PATTERN = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}