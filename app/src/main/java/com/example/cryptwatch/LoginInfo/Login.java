package com.example.cryptwatch.LoginInfo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptwatch.DashBoard;
import com.example.cryptwatch.R;
import com.example.cryptwatch.SplashActivity;
import com.google.android.material.textfield.TextInputLayout;

import kotlin.Pair;

public class Login extends AppCompatActivity {

    ImageView imageView;
    TextView slogan_name , login;
    EditText username , password;
    TextInputLayout tilusername , tilpassword;
    Button btn_skip , btn_logtoreg , btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Hooks
        imageView  =(ImageView) findViewById(R.id.img_login);
        slogan_name = (TextView) findViewById(R.id.slogan_name);
        login = (TextView) findViewById(R.id.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        tilusername = (TextInputLayout) findViewById(R.id.log_username);
        tilpassword = (TextInputLayout) findViewById(R.id.log_password);

        btn_login = (Button) findViewById(R.id.btnlogin);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_logtoreg = (Button) findViewById(R.id.btn_logtoreg);

        skip();
        btn_logtoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){

        String uname = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(TextUtils.isEmpty(uname)){
            username.requestFocus();
            tilusername.setErrorEnabled(true);
            tilusername.setError("Enter Username");

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
        else if(TextUtils.isEmpty(pass)) {
            password.requestFocus();
            tilpassword.setErrorEnabled(true);
            tilpassword.setError("Enter Password");

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
        else{

        }
    }

    private void skip(){
        btn_skip.setOnClickListener((View v) -> {
            Intent intent = new Intent(Login.this, DashBoard.class);
            startActivity(intent);
        });
    }

//    private void logToReg(){
//        btn_logtoreg.setOnClickListener(v -> {
//            Intent intent = new Intent(Login.this, Register.class);
////            Pair[] pairs = new Pair[7];
////            pairs[0] = new Pair<View,String>(imageView , "logo_image");
////            pairs[1] = new Pair<View,String>(slogan_name , "logo_text");
////            pairs[2] = new Pair<View,String>(login , "tran_signUpText");
////            pairs[3] = new Pair<View,String>(tilusername , "tran_username");
////            pairs[4] = new Pair<View,String>(tilpassword , "tran_password");
////            pairs[5] = new Pair<View,String>(btn_login , "tran_btnlogin");
////            pairs[6] = new Pair<View,String>(btn_logtoreg , "tran_logtoreg");
////            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
////            startActivity(intent,options.toBundle());
//            startActivity(intent);
//        });
//    }
}