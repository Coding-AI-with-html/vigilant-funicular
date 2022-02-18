package com.example.androidapp.common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp2ndClass extends AppCompatActivity {

    private static final String TAG = "SignUp2ndClass";


    //Variables
    ImageView backBtn;
    Button btnSignUp,login;
    TextView titleText;

    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    ProgressBar signUpProgBar;
    ImageView fWarrior,fWizard,mWarrior,mWizard;
    RelativeLayout fPick,mPick;
    RadioButton fPickRadio,mPickRadio;

    String genderr = null;



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
        signUpProgBar = findViewById(R.id.progress_bar_signup);
        fWarrior = findViewById(R.id.fwarrior_pick);
        fWizard = findViewById(R.id.fwizard_pick);
        mWarrior = findViewById(R.id.warrior_pick);
        mWizard = findViewById(R.id.wizard_pick);

        fPick = findViewById(R.id.female_pick);
        mPick = findViewById(R.id.male_pick);

        fPickRadio = findViewById(R.id.femaleBut);
        mPickRadio = findViewById(R.id.maleBut);

        fPickRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    fPick.setVisibility(View.VISIBLE);
                    mPick.setVisibility(View.GONE);

                    fPick.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            fWarrior.setBackground(highlight);

                            //identify the character
                            genderr = getResources().getString(R.string.warrior);
                            //Null other background
                            fWizard.setBackground(null);
                            mWarrior.setBackground(null);
                            mWizard.setBackground(null);
                        }
                    });
                    fWizard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            fWizard.setBackground(highlight);
                            //identify the character
                            genderr = getResources().getString(R.string.wizard);
                            //Null other background
                            fWarrior.setBackground(null);
                            mWarrior.setBackground(null);
                            mWizard.setBackground(null);

                        }
                    });
                }
            }
        });


        mPickRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mPick.setVisibility(View.VISIBLE);
                    fPick.setVisibility(View.GONE);

                    mPick.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            mWarrior.setBackground(highlight);
                            //identify the character
                            genderr = getResources().getString(R.string.warrior);
                            //Null other background
                            fWizard.setBackground(null);
                            fWarrior.setBackground(null);
                            mWizard.setBackground(null);
                        }
                    });
                    mWizard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Drawable highlight = getResources().getDrawable( R.drawable.highlight);
                            mWizard.setBackground(highlight);
                            //identify the character
                            genderr = getResources().getString(R.string.wizard);
                            //Null other background
                            fWarrior.setBackground(null);
                            mWarrior.setBackground(null);
                            fWizard.setBackground(null);

                        }
                    });
                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpUser(genderr);
            }
        });
    }

    private void SignUpUser(String gender){

        Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT).show();

        Intent intet = getIntent();
        final String username = intet.getStringExtra(SignUp.EXTRA_TEXT);
        final String email = intet.getStringExtra(SignUp.EXTRA_TEXT2);
        final String password = intet.getStringExtra(SignUp.EXTRA_TEXT3);


        signUpProgBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[4];
                field[0] = "username";
                field[1] = "password";
                field[2] = "email";
                field[3] = "gender";

                //Creating array for data
                String[] data = new String[4];
                data[0] = username;
                data[1] = password;
                data[2] = email;
                data[3] = gender;
                PutData putData = new PutData("http://192.168.122.1/LoginRegister/signup.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        Log.d(TAG, "fieldas: " + field[0] + " DataString" + data[0]);
                        signUpProgBar.setVisibility(View.GONE);
                        String result = putData.getResult();
                        if(result.equals("Sign Up Success")){
                            Intent intnt = new Intent(getApplicationContext(), MainActivity.class);
                            //adding transition animation

                            Pair[] pairs = new Pair[4];

                            pairs[0] = new Pair<View, String>(backBtn,"transition_back_arrow_btn");
                            pairs[1] = new Pair<View, String>(btnSignUp,"transition_next_btn");
                            pairs[2] = new Pair<View, String>(login,"transition_login_btn");
                            pairs[3] = new Pair<View, String>(titleText,"transition_title_text");

                            ActivityOptions actvtOptions = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
                            startActivity(intnt, actvtOptions.toBundle());
                        } else {
                            Toast.makeText(getApplicationContext(),R.string.user_already_exists,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //End Write and Read data with URL
            }
        });

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