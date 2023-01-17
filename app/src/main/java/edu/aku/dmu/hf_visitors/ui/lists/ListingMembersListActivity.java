package edu.aku.dmu.hf_visitors.ui.lists;

import static edu.aku.dmu.hf_visitors.core.MainApp.listingMembers;
import static edu.aku.dmu.hf_visitors.core.MainApp.listingMembersList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONException;

import edu.aku.dmu.hf_visitors.MainActivity;
import edu.aku.dmu.hf_visitors.R;
import edu.aku.dmu.hf_visitors.adapters.RegisteredMembersAdapter;
import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.database.DatabaseHelper;
import edu.aku.dmu.hf_visitors.databinding.ActivityListingMembersListBinding;
import edu.aku.dmu.hf_visitors.models.DPR;
import edu.aku.dmu.hf_visitors.models.ListingMembers;
import edu.aku.dmu.hf_visitors.ui.sections.SectionVisitorsActivity;


public class ListingMembersListActivity extends AppCompatActivity {


    private static final String TAG = "ListingMembersListActivity";
    ActivityListingMembersListBinding bi;
    DatabaseHelper db;
    ActivityResultLauncher<Intent> MemberInfoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && !MainApp.superuser) {
                    Intent data = result.getData();
                    listingMembersList.add(MainApp.listingMembers);
                }

                if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    Toast.makeText(ListingMembersListActivity.this, "No member added.", Toast.LENGTH_SHORT).show();
                }
            });
    private RegisteredMembersAdapter registeredMembersAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_listing_members_list);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        try {
            listingMembersList = db.getAllChilds();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initSearchFilter();

        registeredMembersAdapter = new RegisteredMembersAdapter(this, listingMembersList, member -> {
            try {
                MainApp.listingMembers = db.getSelectedMembers(member.getHead(), member.getPwName(), member.getChildName());
                Toast.makeText(ListingMembersListActivity.this,
                        "Selected Member\n HH No: "
                                + member.getHhid() + "\nHead Name: "
                                + member.getHead(),
                        Toast.LENGTH_LONG).show();
                ListingMembersListActivity.this.startActivity(new Intent(ListingMembersListActivity.this, SectionVisitorsActivity.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        bi.rvMember.setAdapter(registeredMembersAdapter);

        registeredMembersAdapter.notifyDataSetChanged();
        bi.rvMember.setLayoutManager(new LinearLayoutManager(this));

        bi.fab.setOnClickListener(view -> {
            addMoreMember();
        });


        bi.searchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bi.searchByHead.isChecked()) {
                    bi.memberId.setText(null);
                    bi.memberId.setHint("HH Head Name");
                } else if (bi.searchByChild.isChecked()) {
                    bi.memberId.setText(null);
                    bi.memberId.setHint("Child Name");
                } else {
                    bi.memberId.setText(null);
                    bi.memberId.setHint("Cell Number");
                }
            }
        });

        /*String dmuReg = getIntent().getStringExtra("dmureg");
        String reg = getIntent().getStringExtra("reg");*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
        Toast.makeText(this, "Activity Resumed!", Toast.LENGTH_SHORT).show();
    }

    private void addMoreMember() {
        MainApp.dpr = new DPR();
        MainApp.listingMembers = new ListingMembers();
        Intent intent = new Intent(this, SectionVisitorsActivity.class)
                .putExtra("new", true);
//        finish();
        MemberInfoLauncher.launch(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterForms(View view) throws JSONException {

        if (bi.searchByHead.isChecked()) {
            Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

            listingMembersList = db.getAllMembersByHHName(bi.memberId.getText().toString());
            registeredMembersAdapter = new RegisteredMembersAdapter(this, listingMembersList, member -> {

                try {
                    listingMembers = db.getSelectedMembers(member.getHead(), member.getPwName(), member.getChildName());
                    Toast.makeText(ListingMembersListActivity.this,
                            "Selected Member\n HH No: "
                                    + member.getHhid() + "\nHead Name: "
                                    + member.getHead(),
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            registeredMembersAdapter.notifyDataSetChanged();
            bi.rvMember.setAdapter(registeredMembersAdapter);
        } else {
            Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

            listingMembersList = db.getAllMembersByHHID(bi.memberId.getText().toString());
            registeredMembersAdapter = new RegisteredMembersAdapter(this, listingMembersList, member -> {

                try {
                    listingMembers = db.getSelectedMembers(member.getHead(), member.getPwName(), member.getChildName());
                    Toast.makeText(ListingMembersListActivity.this,
                            "Selected Member\n HH No: "
                                    + member.getHhid() + "\nHead Name: "
                                    + member.getHead(),
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            registeredMembersAdapter.notifyDataSetChanged();
            bi.rvMember.setAdapter(registeredMembersAdapter);
        }

    }

    public void btnEnd(View view) {

        finish();
    }

    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


    // Search Filter
    private void initSearchFilter() {
        bi.memberId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (bi.searchByCell.isChecked()) {
                    registeredMembersAdapter.filterByCell(s.toString());
                } else if (bi.searchByChild.isChecked()) {
                    registeredMembersAdapter.filterByChild(s.toString());
                } else registeredMembersAdapter.filterByHead(s.toString());
            }
        });

        bi.memberId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (bi.searchByCell.isChecked()) {
                    registeredMembersAdapter.filterByCell(v.getText().toString());
                } else if (bi.searchByChild.isChecked()) {
                    registeredMembersAdapter.filterByChild(v.getText().toString());
                } else registeredMembersAdapter.filterByHead(v.getText().toString());
                return true;
            }
        });
    }
}