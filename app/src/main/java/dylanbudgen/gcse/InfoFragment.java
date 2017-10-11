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


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private View view;
    private NextQuestionInteractionListener mListener;

    private Question question;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_info, container, false);


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            question = (Question) bundle.getSerializable("QUESTION");
        }


        setUpButtons();
        setUpTextView();


        return view;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (NextQuestionInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }





    public void setUpTextView() {

        TextView text = (TextView) view.findViewById(R.id.text_info);
        text.setText(question.getCorrectAnswer());
    }

    public void setUpButtons() {

        Button correctButton = (Button) view.findViewById(R.id.button_info_next);
        correctButton.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        Log.d("DEBUG", "Button pressed");
        switch (v.getId()) {

            case R.id.button_info_next :
                Log.d("DEBUG", "Button 3 pressed");
                mListener.nextQuestion();
                break;

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface NextQuestionInteractionListener {
        // TODO: Update argument type and name
        void nextQuestion();
    }

}
