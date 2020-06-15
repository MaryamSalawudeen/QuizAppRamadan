package com.fragilecoder.quizappramadan;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Questions.class},version = 1)
public abstract class QuestionRoomDatabase extends RoomDatabase {
    private static QuestionRoomDatabase INSTANCE;

    public abstract QuestionDao questionDao();
    public static synchronized QuestionRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            QuestionRoomDatabase.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(ROOMDBCallBack)
                    .build();
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback ROOMDBCallBack= new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(INSTANCE).execute();


        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private QuestionDao questionDao;

        private PopulateDbAsyncTask(QuestionRoomDatabase db) {

            questionDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            questionDao.insert(new Questions("In what chapter of the quran did Allah teach us the etiquettes of entering into somebody else’s house?" ,
                    "Suratul Ankabut","Suratul Mujadilah","Suratun Noor","Suratul Maryam",3));
            questionDao.insert(new Questions("What are the names of the idols worshipped in Makkah (before the advent of Islam)  mentioned in the Quran? " ,
                    "Laat, Uzzah, Manaat","Laat , Ghurapath, Manaat","Ghurapath, Lomu, ffagu","Ffagu,Laat,Uzzah",1));
            questionDao.insert(new Questions("Pick 5 prophets sent to Banu Israee." ,
                    "Dawood,Isa,Musa,Shammeel,Hizqeel","Adam,Nuh,Yushau,Shammeel,Dhul Khifl","Muhammad,Musa,Adam,Yusuf,Isa","Isa, Musa, Yusuf, Yushau, Shammeel ",4));
            questionDao.insert(new Questions(" “La ilaha illalah lahu l mulk wa lahu l hamdu yuhyee wa yumeet wa huwa hayyu la yamut biyadihi l khayr wa huwa ala kulli shaeyin Qadeer” is a prayer made when?" ,
                    "when entering the toilet","when wearing new clothe","when entering the market","when waking up",3));



            return null;
        }
    }

}
