package com.camark.androidstudy.com.camark.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.camark.androidstudy.R;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_TRUE_INDEX_LIST = "trueIndexList";
    private static final String KEY_FALSE_INDEX_LIST = "falseIndexList";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;
    private ArrayList<Integer> mTrueIndexList;
    private ArrayList<Integer> mFalseIndexList;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mTrueIndexList = savedInstanceState.getIntegerArrayList(KEY_TRUE_INDEX_LIST);
            mFalseIndexList = savedInstanceState.getIntegerArrayList(KEY_FALSE_INDEX_LIST);
        } else {
            mTrueIndexList = new ArrayList<>();
            mFalseIndexList = new ArrayList<>();
        }

        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                //Toast toast = Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.TOP,0,0);//将提示显示在顶部
                //toast.show();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();

            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();

            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(CheatActivity.newIntent(QuizActivity.this, mQuestionBank[mCurrentIndex].isAnswerTrue()), REQUEST_CODE_CHEAT);
            }
        });

        updateQuestion();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() call");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState(Bundle outState) called");
        outState.putInt(KEY_INDEX, mCurrentIndex);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    /**
     * 更新问题TextView的显示
     */
    private void updateQuestion() {
        //Log.d(TAG, "Updating question text ", new Exception());
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        boolean buttonEnabled = !mFalseIndexList.contains(mCurrentIndex) && !mTrueIndexList.contains(mCurrentIndex);

        mFalseButton.setEnabled(buttonEnabled);
        mTrueButton.setEnabled(buttonEnabled);
    }

    /**
     * 检查答案回答是否正确
     */
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;


        if (!mTrueIndexList.contains(mCurrentIndex) && !mFalseIndexList.contains(mCurrentIndex)) {
            if (mIsCheater) {
                mFalseIndexList.add(mCurrentIndex);
                messageResId = R.string.judgment_toast;
            } else {
                if (userPressedTrue == answerIsTrue) {

                    mTrueIndexList.add(mCurrentIndex);


                    messageResId = R.string.correct_toast;

                } else {
                    mFalseIndexList.add(mCurrentIndex);


                    messageResId = R.string.incorrect_toast;

                }
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        }

        updateQuestion();


        if (isFinishQuestion()) {
            Toast.makeText(this, String.format("正确率  %.0f%% ", mTrueIndexList.size() * 1.0 / mQuestionBank.length * 100), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isFinishQuestion() {
        return (mTrueIndexList.size() + mFalseIndexList.size()) >= mQuestionBank.length;
    }
}
