package com.example.androidapp.common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidapp.R;

public class SignUp extends AppCompatActivity {

    //Variables

    ImageView backBtn;

    Button btnNext,login;
    TextView titleText;

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

        Intent intnt = new Intent(getApplicationContext(), SignUp2ndClass.class);

        //adding transition animation

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(btnNext,"transition_next_btn");
        pairs[2] = new Pair<View, String>(login,"transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText,"transition_title_text");

        ActivityOptions actvtOptions = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intnt, actvtOptions.toBundle());
    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }



}