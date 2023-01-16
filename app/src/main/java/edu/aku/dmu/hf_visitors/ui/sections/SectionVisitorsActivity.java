package edu.aku.dmu.hf_visitors.ui.sections;

import static edu.aku.dmu.hf_visitors.core.MainApp.dpr;
import static edu.aku.dmu.hf_visitors.core.MainApp.listingMembers;
import static edu.aku.dmu.hf_visitors.core.MainApp.sharedPref;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.dmu.hf_visitors.R;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.VisitorsTable;
import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.database.DatabaseHelper;
import edu.aku.dmu.hf_visitors.databinding.ActivitySectionVisitorsBinding;
import edu.aku.dmu.hf_visitors.models.DPR;

public class SectionVisitorsActivity extends AppCompatActivity {

    private static final String TAG = "SectionDPRActivity";
    ActivitySectionVisitorsBinding bi;
    String st = "";
    private DatabaseHelper db;

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

        boolean isNew = getIntent().getBooleanExtra("new", false);

        if (!isNew) {
            dpr.setUuid(listingMembers.getUID());
            bi.hf02.setText(listingMembers.getHhid());
            dpr.setHf02(listingMembers.getHhid());
            bi.hf03.setText(listingMembers.getHead());
            dpr.setHf03(listingMembers.getHead());
            bi.hf04.setText(listingMembers.getCellNo());
            dpr.setHf04(listingMembers.getCellNo());

            if (!listingMembers.getPwName().equals("")) {
                bi.hf05.setText(listingMembers.getPwName());
                dpr.setHf05(listingMembers.getPwName());

            } else if (!listingMembers.getChildName().equals("")) {
                bi.hf05.setText(listingMembers.getChildName());
                dpr.setHf05(listingMembers.getChildName());
            }

            if (!listingMembers.getPwName().equals("") || !listingMembers.getChildName().equals("")) {
                dpr.setFlag("1");
            } else if (listingMembers.getPwName().equals("") && listingMembers.getChildName().equals("")) {
                dpr.setFlag("2");
            }

        } else {
            dpr.setFlag("3");
            bi.fldGrpCVhf07.setVisibility(View.VISIBLE);
        }

//        MainApp.dprNO++;
        bi.hf06.setText(new StringBuilder().append(sharedPref.getString("tabID", "")).append(MainApp.dprNO).toString());
        dpr.setHf06(sharedPref.getString("tabID", "") + MainApp.dprNO);
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
            finish();
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