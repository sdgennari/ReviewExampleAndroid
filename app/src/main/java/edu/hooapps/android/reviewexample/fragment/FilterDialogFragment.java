package edu.hooapps.android.reviewexample.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import edu.hooapps.android.reviewexample.R;

public class FilterDialogFragment extends DialogFragment {

    public interface FilterDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, List<Integer> selectedItems);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private FilterDialogListener mCallback;
    private List<Integer> selectedItems;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (FilterDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FilterDialogListener");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedItems = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        builder.setTitle("Filter Categories")
                .setMultiChoiceItems(R.array.categories, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            // Cast to integer to prevent removing by index
                            selectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onDialogPositiveClick(FilterDialogFragment.this, selectedItems);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallback.onDialogNegativeClick(FilterDialogFragment.this);
                    }
                });

        return builder.create();
    }
}
