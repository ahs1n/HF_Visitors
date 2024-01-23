package edu.aku.dmu.hf_visitors;

import static edu.aku.dmu.hf_visitors.core.MainApp.sharedPref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.database.AndroidManager;
import edu.aku.dmu.hf_visitors.databinding.ActivityMainBinding;
import edu.aku.dmu.hf_visitors.models.DPR;
import edu.aku.dmu.hf_visitors.models.ListingMembers;
import edu.aku.dmu.hf_visitors.ui.ChangePasswordActivity;
import edu.aku.dmu.hf_visitors.ui.LoginActivity;
import edu.aku.dmu.hf_visitors.ui.SyncActivity;
import edu.aku.dmu.hf_visitors.ui.lists.ListingMembersListActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(bi.toolbar);
        bi.toolbar.setSubtitle("Welcome, " + MainApp.user.getFullname() + (MainApp.admin ? " (Admin)" : "") + "!");
        bi.setCallback(this);
        tabletID();

        bi.adminView.setVisibility(MainApp.admin ? View.VISIBLE : View.GONE);
        bi.username.setText("Welcome, " + MainApp.user.getFullname() + "!");
    }

    private void tabletID() {
        String tab_ID = sharedPref.getString("tabID", "");

        if (tab_ID.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter Tablet ID");
            builder.setCancelable(false);
            // Inflate the custom layout for the dialog
            View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_tablet_id, null);
            builder.setView(dialogLayout);

            // Find the radio buttons in the custom layout
            RadioButton radioButtonA = dialogLayout.findViewById(R.id.radioButtonA);
            RadioButton radioButtonB = dialogLayout.findViewById(R.id.radioButtonB);

            // Set up the buttons
            builder.setPositiveButton("OK", (dialog, which) -> {
                String selectedTabID;
                if (radioButtonA.isChecked()) {
                    selectedTabID = "A";
                } else if (radioButtonB.isChecked()) {
                    selectedTabID = "B";
                } else {
                    // Handle the case where neither radio button is checked
                    selectedTabID = ""; // Set a default value or handle accordingly
                }

                MainApp.editor.putString("tabID", selectedTabID).apply();

                /*Setup the input*/
            /*final EditText tabID = new EditText(this);
            tabID.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
            tabID.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            builder.setView(tabID);

                // Set up the buttons
            builder.setPositiveButton("OK", (dialog, which) -> {
                MainApp.editor.putString("tabID", tabID.getText().toString()).apply();*/
            });
            // Make the dialog not cancelable
            builder.setOnCancelListener(dialog -> {
                // Do nothing when the cancel button is pressed
            });
            AlertDialog alertDialog = builder.create();
//            alertDialog.setCancelable(false); // This prevents clicking outside the dialog to dismiss it
            alertDialog.setCanceledOnTouchOutside(false); // This prevents clicking outside the dialog to dismiss it
//            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        }
    }

    public void sectionPress(View view) {
        switch (view.getId()) {

            case R.id.openForm:
                MainApp.dpr = new DPR();
                MainApp.listingMembers = new ListingMembers();
                if (sharedPref.getString("tabID", "").equals("")) {
                    tabletID();
                } else {
                    finish();
                    startActivity(new Intent(this, ListingMembersListActivity.class));
                }
                break;
            case R.id.dbm:
                startActivity(new Intent(this, AndroidManager.class));
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_database:
                intent = new Intent(MainActivity.this, AndroidManager.class);
                startActivity(intent);
                break;
            case R.id.action_data_sync:
                intent = new Intent(MainActivity.this, SyncActivity.class);
                startActivity(intent);
                break;
            case R.id.changePassword:
                intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem action_database = menu.findItem(R.id.action_database);

        action_database.setVisible(MainApp.admin);
        return true;

    }

    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}