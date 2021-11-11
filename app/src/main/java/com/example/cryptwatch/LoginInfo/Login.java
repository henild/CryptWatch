package com.example.cryptwatch.LoginInfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptwatch.DashBoard;
import com.example.cryptwatch.ChangePassword;
import com.example.cryptwatch.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    ImageView imageView;
    TextView slogan_name , login;
    EditText login_email , login_password;
    TextInputLayout tilusername , tilpassword;
    Button btn_logtoreg , btn_login , btn_forgotpassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //Hooks
        imageView  =(ImageView) findViewById(R.id.img_login);
        slogan_name = (TextView) findViewById(R.id.slogan_name);
        login = (TextView) findViewById(R.id.login);

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);

        tilusername = (TextInputLayout) findViewById(R.id.log_email);
        tilpassword = (TextInputLayout) findViewById(R.id.log_password);

        btn_login = (Button) findViewById(R.id.btnlogin);
        btn_logtoreg = (Button) findViewById(R.id.btn_logtoreg);
        //btn_forgotpassword = (Button) findViewById(R.id.btnforgot);

        logToReg();
        //forgotPassword();
        btn_login.setOnClickListener(this::onClick);
    }

    private void login(){
        String email = login_email.getText().toString().trim();
        String pass = login_password.getText().toString().trim();
        if(!isValidEmail(email)){
            login_email.requestFocus();
            tilusername.setErrorEnabled(true);
            tilusername.setError("Email is required!");

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
        else if(!isValidatePassword(pass)) {
            login_password.requestFocus();
            tilpassword.setErrorEnabled(true);
            tilpassword.setError("Password is required!");

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
            Toast.makeText(Login.this, "Login", Toast.LENGTH_LONG).show();
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(authResult -> {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
                finish();
            }).addOnFailureListener(e -> Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private void logToReg(){
        btn_logtoreg.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
//            Pair[] pairs = new Pair[7];
//            pairs[0] = new Pair<View,String>(imageView , "logo_image");
//            pairs[1] = new Pair<View,String>(slogan_name , "logo_text");
//            pairs[2] = new Pair<View,String>(login , "tran_signUpText");
//            pairs[3] = new Pair<View,String>(tilusername , "tran_username");
//            pairs[4] = new Pair<View,String>(tilpassword , "tran_password");
//            pairs[5] = new Pair<View,String>(btn_login , "tran_btnlogin");
//            pairs[6] = new Pair<View,String>(btn_logtoreg , "tran_logtoreg");
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
//            startActivity(intent,options.toBundle());
            startActivity(intent);
        });
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

    private void onClick(View v) {
        login();
    }

//    private void forgotPassword() {
//        btn_forgotpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, ChangePassword.class);
//                startActivity(intent);
//            }
//        });
//    }

}