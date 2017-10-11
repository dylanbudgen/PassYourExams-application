package ***REMOVED***gcse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity implements MultipleChoiceFragment.NextQuestionInteractionListener, InfoFragment.NextQuestionInteractionListener {

    int questionQty;
    int questionNumber;
    ArrayList<Question> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questions = (ArrayList<Question>) getIntent().getExtras().getSerializable("QUESTIONS");
        questionQty = questions.size();
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



    public void openInfoQuestion(Question question) {

        InfoFragment fragment = new InfoFragment();
        //InfoFragment fragment = new InfoFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments

        // TODO WE MIGHT ABLE TO USE THIS INSTEAD
        //fragment.setArguments(getIntent().getExtras());


        Bundle bundle = new Bundle();
        bundle.putSerializable("QUESTION", question);
        fragment.setArguments(bundle);

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();


    }


    public void openMultipleChoiceQuestion(Question question) {

        QuestionFragment fragment = new MultipleChoiceFragment();
        //InfoFragment fragment = new InfoFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments

        // TODO WE MIGHT ABLE TO USE THIS INSTEAD
        //fragment.setArguments(getIntent().getExtras());




        Bundle bundle = new Bundle();
        bundle.putSerializable("QUESTION", question);
        fragment.setArguments(bundle);

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();


    }

    public void openNextQuestion(int questionNumber) {

        Question question = questions.get(questionNumber);
        String questionType = question.getQuestionType();

        Log.d("DEBUG", "----------------------- " + questionType);

        if (questionType.equals("MULT_CHOICE")) {

            openMultipleChoiceQuestion(question);

        } else if (questionType.equals("INFO")) {

            openInfoQuestion(question);
        }

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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //endQuestions();
        }


    }

}
