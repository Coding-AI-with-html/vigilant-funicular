package com.example.androidapp.common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";

    ImageView bckBtn;
    Button lognBtn;
    Button forgPaswrd;

    ProgressBar loginProgBar;

    TextInputEditText emailLogin,passwordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);
        bindComponents();

    }

    public void bindComponents(){

        bckBtn = findViewById(R.id.login_back_button);
        loginProgBar = findViewById(R.id.progress_bar_login);
        emailLogin = findViewById(R.id.login_email);
        passwordLogin = findViewById(R.id.login_password);
        lognBtn = findViewById(R.id.letTheUserLogIn);
        forgPaswrd = findViewById(R.id.forgotPassword);

        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RetailerStartUpScreen.class);
                startActivity(intent);
                finish();
            }
        });
        lognBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email, password;
                email = String.valueOf(emailLogin.getText());
                password = String.valueOf(passwordLogin.getText());

                if(!email.equals("") && !password.equals("")) {
                    loginProgBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";


                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.122.1/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    Log.d(TAG, "fieldas: " + field[0] + " DataString" + data[0]);
                                    loginProgBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")) {
                                        Intent intet = new Intent(Login.this, MainActivity.class);
                                        startActivity(intet);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), R.string.empty_fields, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void callSignUpFromLogin(View view){
        startActivity(new Intent(getApplicationContext(), SignUp.class));
        finish();

    }
}