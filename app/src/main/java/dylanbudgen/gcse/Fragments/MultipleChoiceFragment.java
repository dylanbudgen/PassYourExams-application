package ***REMOVED***gcse.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import ***REMOVED***gcse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultipleChoiceFragment extends QuestionFragment implements View.OnClickListener {

    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        initiateQuestionFragment((ViewGroup)view, R.id.text_multiple);
        return view;
    }

    @Override
    protected ArrayList<Button>  getQuestionButtons() {

        ArrayList<Button>  buttons = new ArrayList<>();

        buttons.add((Button) view.findViewById(R.id.button_1_multiple));
        buttons.add((Button) view.findViewById(R.id.button_2_multiple));
        buttons.add((Button) view.findViewById(R.id.button_3_multiple));
        buttons.add((Button) view.findViewById(R.id.button_4_multiple));

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





