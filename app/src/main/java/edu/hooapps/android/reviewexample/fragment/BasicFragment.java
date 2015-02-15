package edu.hooapps.android.reviewexample.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.hooapps.android.reviewexample.R;

public class BasicFragment extends Fragment {

    private Context context;
    OnButtonClickedListeners mCallback;

    public interface OnButtonClickedListeners {
        public void onFragSwapButtonClicked();
        public void onActivitySwapButtonClicked();
        public void onDialogFragButtonClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        try {
            mCallback = (OnButtonClickedListeners) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSwapButtonClickedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button fragSwitchButton = (Button) rootView.findViewById(R.id.frag_button_switch);
        fragSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFragSwapButtonClicked();
            }
        });

        Button activitySwitchButton = (Button) rootView.findViewById(R.id.button_activity_switch);
        activitySwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onActivitySwapButtonClicked();
            }
        });

        Button dialogFragButton = (Button) rootView.findViewById(R.id.button_frag_dialog);
        dialogFragButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onDialogFragButtonClicked();
            }
        });


        return rootView;
    }
}
