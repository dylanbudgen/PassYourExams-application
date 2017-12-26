package ***REMOVED***gcse.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import ***REMOVED***gcse.R;

/**
 * Created by ***REMOVED*** on 15/10/2017.
 */

public class TrueFalseFragment extends QuestionFragment implements View.OnClickListener {

    public TrueFalseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_true_false, container, false);
        initiateQuestionFragment((ViewGroup)view, R.id.text_true_false);
        return view;

    }

    @Override
    protected ArrayList<Button> getQuestionButtons() {

        ArrayList<Button>  buttons = new ArrayList<>();
        buttons.add((Button) view.findViewById(R.id.button_true));
        buttons.add((Button) view.findViewById(R.id.button_false));
        return buttons;
    }


    @Override
    public void onClick(View v) {

        Button button = ((Button) v);

        if (button.getText().equals(question.getCorrectAnswer()) ) {
            mListener.nextQuestion();
        } else {
            button.setTextColor(Color.RED);
        }


    }

}
