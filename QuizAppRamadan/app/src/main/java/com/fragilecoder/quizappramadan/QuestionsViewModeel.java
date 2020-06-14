package com.fragilecoder.quizappramadan;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.ListIterator;

public class QuestionsViewModeel extends AndroidViewModel {
    private QuestionsRepository mRepository;
    private LiveData<List<Questions>> mAllQuestion;

    public QuestionsViewModeel(Application application) {

        super(application);

        mRepository = new QuestionsRepository(application);
        mAllQuestion = mRepository.getmAllQuestions();
    }

    LiveData<List<Questions>> getmAllQuestion() {
        return mAllQuestion;
    }

}
