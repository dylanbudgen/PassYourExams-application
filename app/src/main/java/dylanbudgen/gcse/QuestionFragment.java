package ***REMOVED***gcse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by ***REMOVED*** on 11/10/2017.
 */

public abstract class QuestionFragment extends Fragment implements View.OnClickListener {

    protected Question question;

    protected NextQuestionInteractionListener mListener;

    public QuestionFragment() {
        //
    }

    protected void setUpListener() {
        try {
            mListener = (NextQuestionInteractionListener) this.getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(this.getActivity().toString() + " must implement OnArticleSelectedListener");
        }
    }

    protected void setUpQuestion() {

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            question = (Question) bundle.getSerializable("QUESTION");
        } else {
            // TODO Catch and give user warning
            // TODO force a null error by not setting the arguments in questionsactivity
        }
    }

    protected void setUpButtons(ViewGroup v) {

        ArrayList<Button> buttons = getAllButtons(v);
        ArrayList<String> answers = question.getAnswers();

        for (int i = 0; i < answers.size(); i++) {

            buttons.get(i).setText(answers.get(i));
            buttons.get(i).setOnClickListener(this);

        }

    }

    private ArrayList<Button> getAllButtons(ViewGroup v) {

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


    protected abstract void setUpTextView();


    public interface NextQuestionInteractionListener {
        // TODO: Update argument type and name
        void nextQuestion();
    }


}
