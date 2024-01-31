package edu.aku.dmu.hf_visitors.core;

import static edu.aku.dmu.hf_visitors.database.DatabaseHelper.DATABASE_NAME;
import static edu.aku.dmu.hf_visitors.database.DatabaseHelper.DATABASE_PASSWORD;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.edittextpicker.aliazaz.EditTextPicker;
import com.validatorcrawler.aliazaz.Clear;

import net.sqlcipher.database.SQLiteDatabase;

import org.json.JSONArray;

import java.io.File;
import java.util.List;

import edu.aku.dmu.hf_visitors.BuildConfig;
import edu.aku.dmu.hf_visitors.R;
import edu.aku.dmu.hf_visitors.models.DPR;
import edu.aku.dmu.hf_visitors.models.ListingMembers;
import edu.aku.dmu.hf_visitors.models.Users;
import edu.aku.dmu.hf_visitors.models.VisitsCount;
import edu.aku.dmu.hf_visitors.ui.LockActivity;


public class MainApp extends Application {

    public static final String PROJECT_NAME = "HF Visitors";
    public static final String SYNC_LOGIN = "sync_login";
    public static final String _IP = "https://vcoe1.aku.edu";// .LIVE server
    //public static final String _IP = "http://43.245.131.159:8080";// .TEST server
    public static final String _HOST_URL = MainApp._IP + "/hfp/api/";// .TEST server;
    public static final String _SERVER_URL = "syncGCM.php";
    public static final String _SERVER_GET_URL = "getDataGCM.php";
    public static final String _PHOTO_UPLOAD_URL = _HOST_URL + "uploads.php";
    public static final String _UPDATE_URL = MainApp._IP + "/hfp/app/hf_visitors";
    public static final String _USER_URL = "resetpasswordgcm.php";
    public static final String _APP_FOLDER = "../app/hfp/hf_visitors";
    public static final String _EMPTY_ = "";
    private static final String TAG = "MainApp";
    public static int TRATS = 8;
    //COUNTRIES
    public static int PAKISTAN = 1;
    public static int TAJIKISTAN = 3;
    public static File sdDir;
    public static String[] downloadData;
    public static boolean superuser;
    public static DPR dpr;
    public static String DeviceURL = "devices.php";
    public static AppInfo appInfo;
    public static Users user;
    public static Boolean admin = false;
    public static List<JSONArray> uploadData;
    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPref;
    public static String deviceid;
    public static int versionCode = BuildConfig.VERSION_CODE;
    public static String versionName = BuildConfig.VERSION_NAME;
    public static int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 2;
    public static long TWO_MINUTES = 1000 * 60 * 2;
    public static boolean permissionCheck = false;
    public static String IBAHC = "";
    public static CountDownTimer timer;
    public static List<ListingMembers> listingMembersList;
    public static ListingMembers listingMembers;
    public static int memberCount = 0;
    static ToneGenerator toneGen1;
    public static String tabID = "";
    public static int dprNO = 0;
    public static String selectedAreaName;
    public static String selectedClusterCode;
    public static VisitsCount visitsCountList;

    public static void hideSystemUI(View decorView) {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public static String getDeviceId(Context context) {
        String deviceId;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } else {
           /* final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
            } else {
                deviceId = Settings.Secure.getString(
                        context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }*/
        }
        return "deviceId";
    }

    public static void cbCheck(CheckBox cb1, CheckBox cb2, CheckBox cb3, EditTextPicker edt) {
        cb1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(edt, false);
                cb2.setChecked(false);
                cb2.setEnabled(false);
                cb3.setChecked(false);
                cb3.setEnabled(false);
            } else {
                Clear.clearAllFields(edt, true);
                cb2.setEnabled(true);
                cb3.setEnabled(true);
            }
        });
    }

    public static void lockScreen(Context c) {

        if (timer != null) {
            timer.cancel();
        }

        //   Context mContext = c;
        Activity activity = (Activity) c;


        timer = new CountDownTimer(15 * 60 * 1000, 1000) {
            //timer = new CountDownTimer(30 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                //Some code
                //bi.timeLeft.setText((millisUntilFinished / 1000) + " secs left");
                if ((millisUntilFinished / 1000) < 14) {
                    toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
                }

            }

            public void onFinish() {
                //Logout
                //
                //   finish();
                // lockScreen();
                Intent intent = new Intent();
                intent.setClass(c, LockActivity.class);
                c.startActivity(intent);
                timer.cancel();
                //  startActivity(new Intent(((Activity) c).getLocalClassName(), LockActivity.class));
            }
        };
        timer.start();

    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*
        RootBeer rootBeer = new RootBeer(this);
        if (rootBeer.isRooted()) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }*/

        //Initiate DateTime
        //Initializ App info
        appInfo = new AppInfo(this);
        sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPref.edit();
        MainApp.editor
                .putString("mh01", "")
                .apply();
        deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        initSecure();

        toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    }

    private void initSecure() {
        // Initialize SQLCipher library
        SQLiteDatabase.loadLibs(this);
        File databaseFile = getDatabasePath(DATABASE_NAME);
       /* databaseFile.mkdirs();
        databaseFile.delete();*/
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, DATABASE_PASSWORD, null);
        // Prepare encryption KEY
        ApplicationInfo ai = null;
        try {
            ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            TRATS = bundle.getInt("YEK_TRATS");
            //IBAHC = bundle.getString("YEK_REVRES").substring(TRATS, TRATS + 16);
            IBAHC = bundle.getString("YEK_REVRES");
            Log.d(TAG, "onCreate: YEK_REVRES = " + IBAHC);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
