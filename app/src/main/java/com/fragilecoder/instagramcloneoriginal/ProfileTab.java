package com.fragilecoder.instagramcloneoriginal;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtprofilename, edtbio, edtprofession, edthobbies, edtsport;
    private Button btnupdate;
    public ProfileTab() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container,
                false);

        edtbio = view.findViewById(R.id.edtbio);
        edtprofilename = view.findViewById(R.id.edtprofilename);
        edthobbies = view.findViewById(R.id.edthobbies);
        edtsport = view.findViewById(R.id.edtsport);
        edtprofession = view.findViewById(R.id.edtprofession);
        btnupdate = view.findViewById(R.id.btnupdate);
        final ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser.get("profilename") == null ) {
            edtprofilename.setText( " ");
        } else {
            edtprofilename.setText(parseUser.get("profilename") + " ");
        }
        if (parseUser.get("profession") == null) {
            edtprofession.setText(" ");
        } else {
            edtprofession.setText(parseUser.get("profession") + " ");
        }
        if (parseUser.get("edtsport") == null) {
            edtsport.setText(" ");
        } else {
            edtsport.setText(parseUser.get("sport") + " ");
        }
        if (parseUser.get("bio") == null) {
            edtbio.setText(" ");
        } else {
            edtbio.setText(parseUser.get("bio") + " ");
        }
        if (parseUser.get("hobbies") == null) {
            edthobbies.setText(" ");
        } else {
            edthobbies.setText(parseUser.get("hobbies") + " ");
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
               parseUser.put("profilename", edtprofilename.getText().toString());
               parseUser.put("bio", edtbio.getText().toString());
               parseUser.put("profession", edtprofession.getText().toString());
               parseUser.put("hobbies", edthobbies.getText().toString());
               parseUser.put("sport", edtsport.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please wait....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.show();


                   parseUser.saveInBackground(new SaveCallback() {

                       @Override
                       public void done(ParseException e) {


                           if (e == null) {


                               FancyToast.makeText(getContext(), "Info Updated",
                                       Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();


                       }else {
                               FancyToast.makeText(getContext(), "There was an error: " + e.getMessage(),
                                       Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                           }

                           progressDialog.dismiss();
                       }
                   });



            }
        });
        return view;
    }

}
