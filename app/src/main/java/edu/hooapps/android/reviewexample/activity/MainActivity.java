package edu.hooapps.android.reviewexample.activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import edu.hooapps.android.reviewexample.R;
import edu.hooapps.android.reviewexample.fragment.BasicFragment;
import edu.hooapps.android.reviewexample.fragment.FilterDialogFragment;


public class MainActivity extends ActionBarActivity implements
        BasicFragment.OnButtonClickedListeners,
        FilterDialogFragment.FilterDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new BasicFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_filter) {
            // Show filter dialog fragment
            DialogFragment dialog = new FilterDialogFragment();
            dialog.show(getSupportFragmentManager(), "FilterDialogFragment");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFragSwapButtonClicked() {
        // Slide over transition
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BasicFragment next = new BasicFragment();
        ft.add(R.id.container, next);
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.nothing);
        ft.show(next);
        ft.commit();
    }

    public void onActivitySwapButtonClicked() {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        this.finish();
    }

    // Show the dialog fragment
    public void onDialogFragButtonClicked() {
        DialogFragment dialog = new FilterDialogFragment();
        dialog.show(getSupportFragmentManager(), "FilterDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, List<Integer> selectedItems) {
        // Update the ListView here with a filter
        Log.d("=====", selectedItems.toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
