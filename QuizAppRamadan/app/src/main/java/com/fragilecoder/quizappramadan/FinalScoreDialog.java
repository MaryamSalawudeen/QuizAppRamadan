package com.fragilecoder.quizappramadan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScoreDialog {

    private Context mContext;
    private Dialog finalScoreDialog;
    private QuizActivity quizActivityob;
    private TextView textview_finalscore;

    public FinalScoreDialog(Context mContext) {
        this.mContext = mContext;
    }
    public void finalScoreDialog(int correctAns, int wrongAns) {

        finalScoreDialog = new Dialog(mContext);
        quizActivityob = new QuizActivity();

        finalScoreDialog.setContentView(R.layout.final_score_dialog);
        final Button btFinalScoreDialog = (Button) finalScoreDialog.findViewById(R.id.btn_finaldialog);

        finalScore(correctAns,wrongAns);
        btFinalScoreDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finalScoreDialog.dismiss();
                Intent intent = new Intent(mContext,QuizActivity.class);
                mContext.startActivity(intent);

            }
        });


        finalScoreDialog.show();
        finalScoreDialog.setCancelable(false);
        finalScoreDialog.setCanceledOnTouchOutside(false);



    }

    private void finalScore(int correctAns, int wrongAns) {

        int tempScore = 0;

        textview_finalscore = (TextView) finalScoreDialog.findViewById(R.id.textview_finalscore);

        if (correctAns > wrongAns) {
            tempScore = (correctAns * 20) - (wrongAns * 5);
            textview_finalscore.setText("Final Score: " + String.valueOf(tempScore));
        } else {
            tempScore =   (wrongAns * 5) - (correctAns * 20);
            textview_finalscore.setText("Final Score: " + String.valueOf(tempScore));
        }


    }
}
