package ***REMOVED***gcse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ***REMOVED***gcse.Fragments.InfoFragment;
import ***REMOVED***gcse.Fragments.MultipleChoiceFragment;
import ***REMOVED***gcse.Fragments.QuestionFragment;
import ***REMOVED***gcse.Fragments.TrueFalseFragment;
import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Lessons.Module;
import ***REMOVED***gcse.Managers.ProgressManager;
import ***REMOVED***gcse.Managers.ViewManager;
import ***REMOVED***gcse.Question.Question;
import ***REMOVED***gcse.R;

public class QuestionsActivity extends AppCompatActivity implements QuestionFragment.NextQuestionInteractionListener {

    Module module;
    Lesson lesson;
    String lessonId;
    int questionQty;
    int questionNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions);

        lessonId = (String) getIntent().getExtras().getSerializable("LESSON_ID");
        module = (Module) getIntent().getExtras().getSerializable("MODULE");
        lesson = module.getLesson(lessonId);
        // TODO check if lesson is null
        questionQty = lesson.getQuestions().size();
        questionNumber = 0;

        // Inflate the lesson progress bar and icon
        ConstraintLayout item = (ConstraintLayout) findViewById(R.id.bar_container);
        View child = getLayoutInflater().inflate(R.layout.bar_questions, null);
        item.addView(child);

        // Change colour of background
        ViewManager.setActivityBackground(this.getWindow().findViewById(R.id.activity_questions), lesson.getBackgroundColour());

        // Change the notification bar colour
        ViewManager.setNotificationBarColour(this.getWindow(), lesson.getBackgroundColour());

        // #########################################################################################################
        ImageView img = (ImageView)findViewById(R.id.bar_icon);
        img.setColorFilter(lesson.getForegroundColour());
        // #########################################################################################################

        // Check that the activity is using the layout version with the fragment_container FrameLayout
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
        bundle.putSerializable("FOREGROUND_COLOUR", lesson.getForegroundColour());
        fragment.setArguments(bundle);

        // Add the fragment to the 'fragment_container' FrameLayout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Attach a different animation to the very first question
        if (questionNumber == 0) {
            transaction.setCustomAnimations(0, R.anim.exit_to_left, 0, R.anim.exit_to_left);
        } else {
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        }
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, fragment).commit();

    }

    @Override
    public void nextQuestion() {

        questionNumber++;

        if (questionNumber < questionQty) {

            // removes the current fragment
            this.getSupportFragmentManager().popBackStack();
            // update progress bar
            ProgressManager.updateProgressBar((ProgressBar) findViewById(R.id.bar_progressbar), (int) Math.round(((double) questionNumber / (double) questionQty) * 100), 300);

            openNextQuestion(questionNumber);

        } else {
            // End of questions, going back to start
            Intent intent = new Intent(this, EndGameActivity.class);
            intent.putExtra("LESSON_ID", lessonId);
            intent.putExtra("MODULE", module);
            startActivity(intent);
            startActivity(intent);
        }

    }

    // Make the back button work without changing the fragment
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Make the back button work without changing the fragment
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

        // TODO warning box for going back
        Intent intent = new Intent(this, LessonGridActivity.class);
        intent.putExtra("LESSON_ID", lessonId);
        intent.putExtra("MODULE", module);
        startActivity(intent);
    }




}
