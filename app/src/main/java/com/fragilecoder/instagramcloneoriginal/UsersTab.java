package com.fragilecoder.instagramcloneoriginal;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private ListView listview ;
    private ArrayList<String> arraylist ;
    private ArrayAdapter arrayadapter;

    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_users_tab, container, false);

    listview = view.findViewById(R.id.listview);
    arraylist = new ArrayList();
    arrayadapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arraylist);

       listview.setOnItemClickListener(UsersTab.this);
       listview.setOnItemLongClickListener(UsersTab.this);
        final TextView txtloadingusers = view.findViewById(R.id.txtloadingusers);
    ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    if (users.size() > 0) {
                        for (ParseUser user : users) {
                            arraylist.add(user.getUsername());

                        }
                        listview.setAdapter(arrayadapter );
                        txtloadingusers.animate().alpha(0).setDuration(2000);
                        listview.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent = new Intent(getContext(), UsersPost.class);
        //putExtra is used to send data from one activity to the other.

        intent.putExtra("username", arraylist.get(position));
        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {


       ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
       parseQuery.whereEqualTo("username", arraylist.get(position));
       parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
           @Override
           public void done(ParseUser user, ParseException e) {
               if (user != null && e == null) {
//                   FancyToast.makeText(getContext(),user.get("profileprofession") + " ", Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

                   final PrettyDialog prettyDialog = new PrettyDialog(getContext());
                   prettyDialog.setTitle(user.getUsername() + " 's info")
                           .setMessage((String) user.get("profession") + "\n"
                           + user.get("bio") + "\n"
                                   + user.get("hobbies") + "\n"
                                   + user.get("sport"))
                           .setIcon(R.drawable.person)
                          .addButton(
                                  "OK",
                                  R.color.pdlg_color_white,
                                  R.color.pdlg_color_green,
                                  new PrettyDialogCallback() {
                                      @Override
                                      public void onClick() {
                                          prettyDialog.dismiss();
                                      }
                                  }
                          )
                          .show();
               }
           }
       });

        return true;
    }
}
