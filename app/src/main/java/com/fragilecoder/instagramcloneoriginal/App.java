package com.fragilecoder.instagramcloneoriginal;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HiPdSpm10HpPNBqwDVhRl9lMjUjCprrShFh47F5o")
                // if defined
                .clientKey("m9Qd6tDzuE7l9ki7Nm1jedtDgFyGITMoQhgX0Qnb")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
