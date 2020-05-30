package com.fragilecoder.instagramcloneoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
private Button btnSave;
private EditText edtName, edtpunchpower, edtKickspeed,edtPunchspeed, edtKickpower;
private TextView txtgetdata;
private Button btngetall;
private String allkickboxers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnsave);
        btnSave.setOnClickListener(SignUp.this);
        edtName = findViewById(R.id.edtName);
        edtKickspeed = findViewById(R.id.edtKickspeed);
        edtpunchpower = findViewById(R.id.edtpunchpower);
        edtPunchspeed = findViewById(R.id.edtPunchspeed);
        edtKickpower = findViewById(R.id.edtKickpower);

        txtgetdata = findViewById(R.id.txtgetdata);
        btngetall = findViewById(R.id.btngetall);
        txtgetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kickboxer");
                parseQuery.getInBackground("Lc3LexX4ca", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtgetdata.setText(object.get("name")+ "" + "punch power: " + object.get("punchpower"));

                        }
                    }
                });

            }
        });
        btngetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {

                allkickboxers = " ";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("kickboxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                for (ParseObject kickboxer : objects) {
                                    allkickboxers = allkickboxers + kickboxer.get("name")   + "\n";
                                }

                                FancyToast.makeText(SignUp.this, allkickboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    }
                });

            }
        });

                    }


    @Override
    public void onClick(View v) {
        try {


        final ParseObject kickboxer = new ParseObject("kickboxer");
        kickboxer.put("name", edtName.getText().toString());
        kickboxer.put("punchspeed",Integer.parseInt(edtPunchspeed.getText().toString()));
        kickboxer.put("punchpower",Integer.parseInt(edtpunchpower.getText().toString()) );
        kickboxer.put("kickspeed", Integer.parseInt(edtKickspeed.getText().toString()));
        kickboxer.put("kickpower", Integer.parseInt(edtKickpower.getText().toString()) );
        kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText(SignUp.this,kickboxer.get("name") + "is saved to the server!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();


                } else {

                }

            }

        });
        } catch (Exception e){
            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
        }

    }
    //        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Toast.makeText(SignUp.this, "boxer object is saved successfully", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });

    }

