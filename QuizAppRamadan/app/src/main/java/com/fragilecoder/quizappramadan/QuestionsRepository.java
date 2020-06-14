package com.fragilecoder.quizappramadan;

import android.app.Application;
import android.app.DownloadManager;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionsRepository {


    private QuestionDao mQuestionDao;
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionsRepository(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getINSTANCE(application);
        mQuestionDao = db.questionDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

    public LiveData<List<Questions>> getmAllQuestions() {



        return mAllQuestions;
    }
}
