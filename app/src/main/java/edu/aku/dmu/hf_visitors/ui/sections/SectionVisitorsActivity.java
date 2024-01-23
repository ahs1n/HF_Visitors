package edu.aku.dmu.hf_visitors.ui.sections;

import static edu.aku.dmu.hf_visitors.core.MainApp._EMPTY_;
import static edu.aku.dmu.hf_visitors.core.MainApp.dpr;
import static edu.aku.dmu.hf_visitors.core.MainApp.listingMembers;
import static edu.aku.dmu.hf_visitors.core.MainApp.sharedPref;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import edu.aku.dmu.hf_visitors.R;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.VisitorsTable;
import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.database.DatabaseHelper;
import edu.aku.dmu.hf_visitors.databinding.ActivitySectionVisitorsBinding;
import edu.aku.dmu.hf_visitors.models.Clusters;
import edu.aku.dmu.hf_visitors.models.DPR;
import edu.aku.dmu.hf_visitors.ui.lists.ListingMembersListActivity;

public class SectionVisitorsActivity extends AppCompatActivity {

    private static final String TAG = "SectionDPRActivity";
    ActivitySectionVisitorsBinding bi;
    String st = "";
    private DatabaseHelper db;
    private ArrayList<String> areaNames, clustersCodes;

    private int nMaxSPValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_visitors);
        st = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime());
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        MainApp.dpr = new DPR();
        bi.setForm(dpr);
        checkTodayDate();
        populateSpinner();

        boolean isNew = getIntent().getBooleanExtra("new", false);

        if (!isNew) {
            dpr.setUuid(listingMembers.getUID());
//            bi.hf02.setText(listingMembers.getHhid());
            bi.hf02.setTextColor(ContextCompat.getColor(this, R.color.redDark));
            dpr.setHf02(listingMembers.getHhid());
            nMaxSPValue = Integer.parseInt(listingMembers.getHhid().split("-")[1]);
//            bi.hf03.setText(listingMembers.getHead());
            dpr.setHf03(listingMembers.getHead());
//            bi.hf04.setText(listingMembers.getCellNo());
            dpr.setHf04(listingMembers.getCellNo());
            bi.fldGrpCVhf01a.setVisibility(View.GONE);
            bi.fldGrpCVhf05.setVisibility(View.VISIBLE);
            /*TODO:
            need to set spinner
            * */
//            dpr.setHf01a(listingMembers.getHf01a());
            dpr.setHf01b(listingMembers.getHf01b());
            dpr.setHf02a(listingMembers.getHf02a());
            dpr.setHf06a(listingMembers.getHf06a());
            dpr.setHf07a(listingMembers.getHf07a());

            if (!listingMembers.getPwName().equals("")) {
                bi.hf05.setText(listingMembers.getPwName());
                dpr.setHf05(listingMembers.getPwName());

            } else if (!listingMembers.getChildName().equals("")) {
                bi.hf05.setText(listingMembers.getChildName());
                dpr.setHf05(listingMembers.getChildName());
            } else if (!listingMembers.getNewMemberName().equals("")) {
                bi.hf05.setText(listingMembers.getNewMemberName());
                dpr.setHf05(listingMembers.getNewMemberName());
            }

            if (!listingMembers.getPwName().equals("") || !listingMembers.getChildName().equals("") ||
                    !listingMembers.getNewMemberName().equals("")) {
                dpr.setFlag("1");
            } else if (listingMembers.getPwName().equals("") && listingMembers.getChildName().equals("") &&
                    listingMembers.getNewMemberName().equals("")) {
                dpr.setFlag("2");
                bi.fldGrpCVhf08.setVisibility(View.VISIBLE);
                bi.fldGrpCVhf09.setVisibility(View.VISIBLE);
            }
        } else {
            dpr.setFlag("3");
            bi.fldGrpCVhf07.setVisibility(View.VISIBLE);
            bi.fldGrpCVhf08.setVisibility(View.VISIBLE);
            bi.fldGrpCVhf09.setVisibility(View.VISIBLE);
            bi.hf09y.setMinvalue(18);
        }

//        MainApp.dprNO++;
        bi.hf06.setText(new StringBuilder().append(sharedPref.getString("tabID", "")).append(MainApp.dprNO).toString());
        dpr.setHf06(sharedPref.getString("tabID", "") + MainApp.dprNO);
    }

    private void populateSpinner() {
        Collection<Clusters> clusters = db.getClustersByHF(MainApp.user.getHfcode());

        areaNames = new ArrayList<>();
        clustersCodes = new ArrayList<>();
        areaNames.add("...");
        clustersCodes.add("...");

        for (Clusters c : clusters) {
            areaNames.add(c.getArea());
            clustersCodes.add(c.getClusterNo());
        }

        if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu")) {
            areaNames.add("Test Area 1");
            areaNames.add("Test Area 2");
            areaNames.add("Test Area 3");
            clustersCodes.add("001");
            clustersCodes.add("002");
            clustersCodes.add("003");
        }
        // Apply the adapter to the spinner
        bi.hf01a.setAdapter(new ArrayAdapter<>(SectionVisitorsActivity.this, R.layout.custom_spinner, areaNames));

        bi.hf01a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    MainApp.selectedAreaName = (areaNames.get(bi.hf01a.getSelectedItemPosition()));
                    MainApp.selectedClusterCode = (clustersCodes.get(bi.hf01a.getSelectedItemPosition()));
                    if (dpr != null) {
                        dpr.setHf01a(MainApp.selectedAreaName);

                        if (MainApp.selectedAreaName.equals("Out of UC Area")) {
                            bi.fldGrpCVhf01b.setVisibility(View.VISIBLE);
                        } else {
                            dpr.setHf01b(_EMPTY_);
                            bi.fldGrpCVhf01b.setVisibility(View.GONE);
                        }

                        // Generate Family Id dynamically
                        String nMaxSP = sharedPref.getString("n_family_max", null);
                        HashMap<String, Object> nMaxHMSP = new Gson().fromJson(nMaxSP, new TypeToken<HashMap<String, Object>>() {
                        }.getType());
                        nMaxSPValue = Integer.parseInt((String) Objects.requireNonNull(nMaxHMSP.get(MainApp.selectedClusterCode))) + 1;
                        dpr.setHf02(String.format(Locale.getDefault(), "%04d-%04d-N",
                                Integer.parseInt(MainApp.selectedClusterCode), nMaxSPValue));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void checkTodayDate() {
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
        String savedDate = sharedPref.getString("todayEntryDate", "");
        if (savedDate.equals("") || !savedDate.equals(todayDate)) {
            MainApp.editor.putString("todayEntryDate", todayDate).apply();
            MainApp.dprNO = 1;
            sharedPref.edit().putInt("dpr_no", MainApp.dprNO).apply();
        } else {
            MainApp.dprNO = sharedPref.getInt("dpr_no", 1);
        }

    }


    private boolean insertNewRecord() {
        if (!dpr.getUid().equals("") || MainApp.superuser) return true;
        dpr.populateMeta();
        long rowId = 0;
        try {
            rowId = db.addMember(dpr);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.db_excp_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        MainApp.dpr.setId(String.valueOf(rowId));
        if (rowId > 0) {
            MainApp.dpr.setUid(MainApp.dpr.getDeviceId() + MainApp.dpr.getId());
            db.updateMemberColumn(VisitorsTable.COLUMN_UID, dpr.getUid());
            return true;
        } else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean updateDB() {
        if (MainApp.superuser) return true;
        db = MainApp.appInfo.getDbHelper();
        long updcount = 0;
        try {
            updcount = db.updateMemberColumn(VisitorsTable.COLUMN_DPR, dpr.dPRtoString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, R.string.upd_db + e.getMessage());
            Toast.makeText(this, R.string.upd_db + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (updcount > 0) return true;
        else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void btnContinue(View view) {
        if (!formValidation()) return;
        dpr.setStartTime(st);
        if (!insertNewRecord()) return;
        if (updateDB()) {
            /*Intent i;
            i = new Intent(this, ListingMembersListActivity.class);
            startActivity(i);*/
            sharedPref.edit().putInt("dpr_no", ++MainApp.dprNO).apply();
            // Update Family number in shared preference
            String nMaxSP = sharedPref.getString("n_family_max", null);
            HashMap<String, Object> nMaxHMSP = new Gson().fromJson(nMaxSP, new TypeToken<HashMap<String, Object>>() {
            }.getType());
            nMaxHMSP.put(MainApp.selectedClusterCode, Integer.toString(nMaxSPValue));
            sharedPref.edit().putString("n_family_max", new Gson().toJson(nMaxHMSP)).apply();
            Intent i;
            i = new Intent(this, ListingMembersListActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        MainApp.dprNO--;
        finish();
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    @Override
    public void onBackPressed() {
        MainApp.dprNO--;
        setResult(RESULT_CANCELED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
    }
}