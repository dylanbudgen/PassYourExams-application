package ***REMOVED***gcse.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ***REMOVED***gcse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends QuestionFragment implements View.OnClickListener {

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_info, container, false);
        initiateQuestionFragment((ViewGroup)view, R.id.text_info);
        return view;

    }

    @Override
    protected ArrayList<Button> getQuestionButtons() {

        ArrayList<Button>  buttons = new ArrayList<>();
        buttons.add((Button) view.findViewById(R.id.button_info));
        return buttons;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_info:
                mListener.nextQuestion();
                break;

        }
    }

}
