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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.androidapp.ui.MainOld;
import com.example.androidapp.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp2ndClass extends AppCompatActivity {


    //Variables
    ImageView backBtn;
    Button btnSignUp,login;
    TextView titleText;

    RadioGroup radioGroup;
    RadioButton selectedRadioButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);

        bindComponents();
    }


    public void bindComponents() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        backBtn = findViewById(R.id.signup_back_button);
        btnSignUp = findViewById(R.id.signup_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        Intent intet = getIntent();
        String username = intet.getStringExtra(SignUp.EXTRA_TEXT);
        String email = intet.getStringExtra(SignUp.EXTRA_TEXT2);
        String password = intet.getStringExtra(SignUp.EXTRA_TEXT3);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRadioButton  = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                String gender = selectedRadioButton.getText().toString();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[4];
                        field[0] = "username";
                        field[1] = "email";
                        field[2] = "password";
                        field[3] = "gender";

                        //Creating array for data
                        String[] data = new String[2];
                        data[0] = username;
                        data[1] = email;
                        data[2] = password;
                        data[3] = gender;
                        PutData putData = new PutData("https://projects.vishnusivadas.com/AdvancedHttpURLConnection/putDataTest.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result);
                            }
                        }
                        //End Write and Read data with URL
                    }
                });

            }
        });




    }




    public void callFinishSignupScreen(View vIew){

        Intent intnt = new Intent(getApplicationContext(), MainOld.class);

        //adding transition animation

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(btnSignUp,"transition_next_btn");
        pairs[2] = new Pair<View, String>(login,"transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText,"transition_title_text");

        ActivityOptions actvtOptions = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
        startActivity(intnt, actvtOptions.toBundle());
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    public void callLoginFromSignUp2(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}