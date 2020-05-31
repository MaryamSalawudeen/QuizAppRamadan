package com.fragilecoder.instagramcloneoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtemail, edtusername, edtpassword;
    private Button btnsign, btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign Up Page");


        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        edtusername = findViewById(R.id.edtusername);

        btnlog = findViewById(R.id.btnlog);
        btnsign = findViewById(R.id.btnsign);

        btnsign.setOnClickListener(this);
        btnlog.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

//


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnsign:
                final ParseUser appuser = new ParseUser();
                appuser.setPassword(edtpassword.getText().toString());
                appuser.setUsername(edtusername.getText().toString());
                appuser.setEmail(edtemail.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + edtusername.getText().toString());
                progressDialog.show();
                appuser.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {


                        if (e ==  null) {
                            FancyToast.makeText(SignUp.this, appuser.getUsername() + " is signed up successfully",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(),
                                    Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                       progressDialog.dismiss();
                    }
                });
                break;
            case  R.id.btnlog:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;

        }

    }
}