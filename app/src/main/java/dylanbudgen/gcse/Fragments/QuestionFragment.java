package ***REMOVED***gcse.Fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ***REMOVED***gcse.Question.Question;

/**
 * Created by ***REMOVED*** on 11/10/2017.
 */

public abstract class QuestionFragment extends Fragment implements View.OnClickListener {

    protected View view;
    protected Question question;
    protected int foregroundColour;
    protected NextQuestionInteractionListener mListener;

    public QuestionFragment() {
        //
    }

    protected void initiateQuestionFragment(ViewGroup v, int id) {

        setUpListener();
        setFields();
        setUpTextView(id);
        setUpQuestionButtons(v);

    }

    protected void setUpListener() {
        try {
            mListener = (NextQuestionInteractionListener) this.getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(this.getActivity().toString() + " must implement NextQuestionInteractionListener");
        }
    }

    protected void setFields() {

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            question = (Question) bundle.getSerializable("QUESTION");
            foregroundColour = (int) bundle.getSerializable("FOREGROUND_COLOUR");
        } else {
            // TODO Catch and give user warning
            // TODO force a null error by not setting the arguments in questionsactivity
        }
    }

    protected void setUpQuestionButtons(ViewGroup v) {

        ArrayList<Button> buttons = getQuestionButtons();
        ArrayList<String> answers = question.getAnswers();

        for (int i = 0; i < answers.size(); i++) {
            buttons.get(i).setText(answers.get(i));
            buttons.get(i).setOnClickListener(this);
            // Set colours
            buttons.get(i).setBackgroundTintList(ColorStateList.valueOf(foregroundColour));
            buttons.get(i).setTextColor(Color.parseColor("#ffffff"));

        }

    }

    // Overridable
    public void correctAnswer() {
        mListener.nextQuestion();
    }

    // Overridable
    public void wrongAnswer() {
        Log.d("DEBUG", "0000P Wrong answer selected ");
        //mListener.nextQuestion();
    }

    protected abstract ArrayList<Button> getQuestionButtons();

    protected void setUpTextView(int id) {
        TextView text = (TextView) view.findViewById(id);
        text.setMovementMethod(new ScrollingMovementMethod());
        text.setText(question.getQuestion());
    }

    public interface NextQuestionInteractionListener {
        // TODO: Update argument type and name
        void nextQuestion();
    }

    protected ArrayList<Button> getAllButtons(ViewGroup v) {

        ArrayList<Button> buttons = new ArrayList();

        for (int i = 0; i < v.getChildCount(); i++) {
            View child = v.getChildAt(i);
            if (child instanceof ViewGroup)
                getAllButtons((ViewGroup) child);
            else if (child instanceof Button)
                buttons.add((Button) child);
        }

        return buttons;
    }


}
