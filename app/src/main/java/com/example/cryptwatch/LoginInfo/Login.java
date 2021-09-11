package com.example.cryptwatch.LoginInfo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
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
    TextInputLayout username , password;
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
        username = (TextInputLayout) findViewById(R.id.log_username);
        password = (TextInputLayout) findViewById(R.id.log_password);
        btn_login = (Button) findViewById(R.id.btnlogin);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_logtoreg = (Button) findViewById(R.id.btn_logtoreg);


        btn_skip.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, DashBoard.class);
            startActivity(intent);
        });


        btn_logtoreg.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);

//            Pair[] pairs = new Pair[7];
//
//            pairs[0] = new Pair<View,String>(imageView , "logo_image");
//            pairs[1] = new Pair<View,String>(slogan_name , "logo_text");
//            pairs[2] = new Pair<View,String>(login , "tran_signUpText");
//            pairs[3] = new Pair<View,String>(username , "tran_username");
//            pairs[4] = new Pair<View,String>(password , "tran_password");
//            pairs[5] = new Pair<View,String>(btn_login , "tran_btnlogin");
//            pairs[6] = new Pair<View,String>(btn_logtoreg , "tran_logtoreg");
//
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
//            startActivity(intent,options.toBundle());
              startActivity(intent);
        });
    }
}