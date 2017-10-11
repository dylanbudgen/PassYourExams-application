package ***REMOVED***gcse;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultipleChoiceFragment extends QuestionFragment implements View.OnClickListener {

    private View view;


    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        super.setUpListener();
        super.setUpQuestion();
        setUpTextView();
        setUpButtons((ViewGroup)view);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    public void setUpTextView() {

        TextView text = (TextView) view.findViewById(R.id.text_multiple);
        text.setText(question.getCorrectAnswer());
    }





    @Override
    public void onClick(View v) {
        Log.d("DEBUG", "Button pressed");

        if (((Button) v).getText().equals(question.getCorrectAnswer()) ) {

            correctAnswer();

        } else {

            wrongAnswer();
        }

    }

    public void correctAnswer() {

        Log.d("DEBUG", "0000P Correct answer selected ");
        mListener.nextQuestion();

    }


    public void wrongAnswer() {

        Log.d("DEBUG", "0000P Wrong answer selected ");
        //mListener.nextQuestion();
    }




}
