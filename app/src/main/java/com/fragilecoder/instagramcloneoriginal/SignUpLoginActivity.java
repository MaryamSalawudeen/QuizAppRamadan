package com.fragilecoder.instagramcloneoriginal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {
    private EditText edtname, edtpassword,edtloginname, edtloginpassword;
    private Button btnuser, btnloginuser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtloginname = findViewById(R.id.edtloginname);
        edtloginpassword = findViewById(R.id.edtloginpassword);
        edtname = findViewById(R.id.edtname);
        edtpassword = findViewById(R.id.edtpassword);
        btnloginuser = findViewById(R.id.btnloginuser);
        btnuser = findViewById(R.id.btnuser);
        btnloginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser.logInInBackground(edtloginname.getText().toString(), edtloginpassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                      if (user != null && e == null) {
                          FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                      }
                      else {
                          FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + "is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                      }
                    }
                });


            }
        });
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser appuser = new ParseUser();
                appuser.setUsername(edtname.getText().toString());
                appuser.setPassword(edtpassword.getText().toString());

                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, appuser.get("username") + "is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
    }
}
