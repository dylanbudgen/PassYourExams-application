package ***REMOVED***gcse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ***REMOVED***gcse.Fragments.InfoFragment;
import ***REMOVED***gcse.Fragments.MultipleChoiceFragment;
import ***REMOVED***gcse.Fragments.QuestionFragment;
import ***REMOVED***gcse.Fragments.TrueFalseFragment;
import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Question.Question;
import ***REMOVED***gcse.R;

public class QuestionsActivity extends AppCompatActivity implements QuestionFragment.NextQuestionInteractionListener {

    Lesson lesson;
    int questionQty;
    int questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        lesson = (Lesson) getIntent().getExtras().getSerializable("LESSON");
        questionQty = lesson.getQuestions().size();
        questionNumber = 0;

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            openNextQuestion(questionNumber);

        }

    }


    public void openNextQuestion(int questionNumber) {

        Question question = lesson.getQuestions().get(questionNumber);
        QuestionFragment fragment = null;

        switch (question.getQuestionType()) {

            case "MULT_CHOICE" :
                fragment = new MultipleChoiceFragment();
                break;
            case "INFO" :
                fragment = new InfoFragment();
                break;
            case "TRUE_FALSE" :
                fragment = new TrueFalseFragment();
                break;
        }

        // TODO Catch the null here

        Bundle bundle = new Bundle();
        bundle.putSerializable("QUESTION", question);
        fragment.setArguments(bundle);

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

    }

    @Override
    public void nextQuestion() {
        Log.d("DEBUG", "000P The main activity listener method is activated ");

        questionNumber++;

        if (questionNumber < questionQty) {

            // removes the current fragment
            for(Fragment fragment:getSupportFragmentManager().getFragments()){
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            openNextQuestion(questionNumber);

        } else {

            // End of questions, going back to start
            Intent intent = new Intent(this, EndGameActivity.class);
            intent.putExtra("LESSON", lesson);
            startActivity(intent);
        }


    }



}
