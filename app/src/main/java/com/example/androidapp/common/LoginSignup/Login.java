package com.example.androidapp.common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidapp.R;

public class Login extends AppCompatActivity {

    ImageView bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        bindComponents();
    }

    public void bindComponents(){

        bckBtn = findViewById(R.id.login_back_button);
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RetailerStartUpScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void callSignUpFromLogin(View view){
        startActivity(new Intent(getApplicationContext(), SignUp.class));
        finish();

    }
}