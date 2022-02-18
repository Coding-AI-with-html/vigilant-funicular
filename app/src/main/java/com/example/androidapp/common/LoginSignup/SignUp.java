package com.example.androidapp.common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.androidapp.common.EXTRA_TEXT";
    public static final String EXTRA_TEXT2 = "com.example.androidapp.common.EXTRA_TEXT2";
    public static final String EXTRA_TEXT3 = "com.example.androidapp.common.EXTRA_TEXT3";
    //Variables

    ImageView backBtn;

    Button btnNext,login;
    TextView titleText;
    TextInputEditText username,email,password,confpswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_sign_up);
        bindComponents();

    }

    public void bindComponents() {
        backBtn = findViewById(R.id.signup_back_button);
        btnNext = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        username = findViewById(R.id.register_username);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confpswrd = findViewById(R.id.register_password_confirm);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, RetailerStartUpScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void callNextSignupScreen(View vIew){
        String usrnameReg = username.getText().toString();
        String emailReg = email.getText().toString();
        String passwordReg = password.getText().toString();
        String confPswrd = confpswrd.getText().toString();
        if(confPswrd.equals(passwordReg)) {
            if(passwordReg.length() <=5){
                Toast.makeText(SignUp.this, R.string.more_characters_password,Toast.LENGTH_SHORT).show();
            } else {

                Intent intnt = new Intent(getApplicationContext(), SignUp2ndClass.class);

                intnt.putExtra(EXTRA_TEXT, usrnameReg);
                intnt.putExtra(EXTRA_TEXT2, emailReg);
                intnt.putExtra(EXTRA_TEXT3, passwordReg);
                //adding transition animation

                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
                pairs[1] = new Pair<View, String>(btnNext, "transition_next_btn");
                pairs[2] = new Pair<View, String>(login, "transition_login_btn");
                pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

                ActivityOptions actvtOptions = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                startActivity(intnt, actvtOptions.toBundle());
            }
        } else {
            Toast.makeText(SignUp.this, R.string.not_same_password, Toast.LENGTH_SHORT).show();
        }
    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }




}