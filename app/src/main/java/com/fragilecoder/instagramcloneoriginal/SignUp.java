package com.fragilecoder.instagramcloneoriginal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        edtpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                if (keycode == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN) {
                    onClick(btnsign );
                }
                return false;
            }
        });
        edtusername = findViewById(R.id.edtusername);

        btnlog = findViewById(R.id.btnlog);
        btnsign = findViewById(R.id.btnsign);

        btnsign.setOnClickListener(this);
        btnlog.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
            transitiontosocialmediaactivity();

        }

//


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnsign:
                if (edtemail.getText().toString().equals("") ||
                        edtusername.getText().toString().equals(" ") ||
                        edtpassword.getText().toString().equals(" ")) {
                    FancyToast.makeText(SignUp.this, "Email, Password, Username is required",
                            Toast.LENGTH_SHORT, FancyToast.INFO, true).show();

                } else {


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



                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appuser.getUsername() + " is signed up successfully",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitiontosocialmediaactivity();
                            } else {
                                FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                    break;
                    case R.id.btnlog:
                        Intent intent = new Intent(SignUp.this, LoginActivity.class);
                        startActivity(intent);

                        break;

                }

    }

    public void rootLayoutTapped (View  view) {
        try {


            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitiontosocialmediaactivity () {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }

}