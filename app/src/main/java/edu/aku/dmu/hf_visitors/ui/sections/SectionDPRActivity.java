package edu.aku.dmu.hf_visitors.ui.sections;

import static edu.aku.dmu.hf_visitors.core.MainApp.dpr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import edu.aku.dmu.hf_visitors.R;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.DPRTable;
import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.database.DatabaseHelper;
import edu.aku.dmu.hf_visitors.databinding.ActivitySectionDprBinding;

public class SectionDPRActivity extends AppCompatActivity {

    private static final String TAG = "SectionDPRActivity";
    ActivitySectionDprBinding bi;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_dpr);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;

/*        try {
            dpr = db.getListingMembers(listingMembers.getHead(), listingMembers.getPwName(), listingMembers.getChildName()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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
            db.updateMemberColumn(DPRTable.COLUMN_UID, dpr.getUid());
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
            updcount = db.updateMemberColumn(DPRTable.COLUMN_DPR, dpr.dPRtoString());
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
        if (!insertNewRecord()) return;
        if (updateDB()) {
            Intent i;
            i = new Intent(this, SectionDPRActivity.class).putExtra("complete", true);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        finish();
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
    }
}