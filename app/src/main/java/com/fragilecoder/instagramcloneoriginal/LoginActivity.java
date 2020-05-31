package com.fragilecoder.instagramcloneoriginal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


        private EditText edtloginemail, edtloginpassword;
    private Button btnlogin1, btnsignup1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setTitle("LogIn Page");

        edtloginemail = findViewById(R.id.edtloginemail);
        edtloginpassword = findViewById(R.id.edtloginpassword);

        btnlogin1 = findViewById(R.id.btnlogin1);
        btnsignup1 = findViewById(R.id.btnsignup1);

        btnsignup1.setOnClickListener(this);
        btnlogin1.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
       case  R.id.btnlogin1:
           final ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Logging In " + edtloginemail.getText().toString());
           progressDialog.show();
           ParseUser.logInInBackground(edtloginemail.getText().toString(), edtloginpassword.getText().toString(),
                   new LogInCallback() {
                       @Override
                       public void done(ParseUser user, ParseException e) {
                           if (user != null && e == null ) {
                               FancyToast.makeText(LoginActivity.this, user.getUsername() + " is logged in successfully",
                                       Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                           } else {
                               FancyToast.makeText(LoginActivity.this, "There was an error: " + e.getMessage(),
                                       Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                           }progressDialog.dismiss();

                       }
                       });
           break;
            case R.id.btnsignup1:


                break;

        }



    }
}
