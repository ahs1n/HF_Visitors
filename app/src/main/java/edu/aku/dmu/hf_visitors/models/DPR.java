package edu.aku.dmu.hf_visitors.models;

import static edu.aku.dmu.hf_visitors.core.MainApp.PROJECT_NAME;
import static edu.aku.dmu.hf_visitors.core.MainApp._EMPTY_;

import android.database.Cursor;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.PropertyChangeRegistry;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.dmu.hf_visitors.BR;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.VisitorsTable;
import edu.aku.dmu.hf_visitors.core.MainApp;

public class DPR extends BaseObservable {

    private final String TAG = "ListingMember";
    private final transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    // SECTION VARIABLES
    private final String sH1 = _EMPTY_;
    // APP VARIABLES
    private String projectName = PROJECT_NAME;
    // APP VARIABLES
    private String id = _EMPTY_;
    private String uid = _EMPTY_;
    private String uuid = _EMPTY_;
    private String userName = _EMPTY_;
    private String sysDate = _EMPTY_;
    private String deviceId = _EMPTY_;
    private String appver = _EMPTY_;
    private String endTime = _EMPTY_;
    private String startTime = _EMPTY_;
    private String iStatus = _EMPTY_;
    private String iStatus96x = _EMPTY_;
    private String synced = _EMPTY_;
    private String syncDate = _EMPTY_;
    private String flag = _EMPTY_;
    private String hfCode = _EMPTY_;

    //  Section DPR
    private String hf01 = _EMPTY_;
    private String hf01a = _EMPTY_;
    private String hf01b = _EMPTY_;
    private String hf02 = _EMPTY_;
    private String hf02a = _EMPTY_;
    private String hf03 = _EMPTY_;
    private String hf04 = _EMPTY_;
    private String hf05 = _EMPTY_;
    private String hf06 = _EMPTY_;
    private String hf06a = _EMPTY_;
    private String hf07 = _EMPTY_;
    private String hf07a = _EMPTY_;
    private String hf08 = _EMPTY_;
    private String hf09d = _EMPTY_;
    private String hf09m = _EMPTY_;
    private String hf09y = _EMPTY_;
    private String dPR = _EMPTY_;
    private String nfamily = _EMPTY_;


    public void populateMeta() {

        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setDeviceId(MainApp.deviceid);
        setAppver(MainApp.appInfo.getAppVersion());
        setProjectName(PROJECT_NAME);
        setHfCode(MainApp.user.getHfcode());
        setEndTime(new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));

    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getiStatus() {
        return iStatus;
    }

    public void setiStatus(String iStatus) {
        this.iStatus = iStatus;
    }

    public String getiStatus96x() {
        return iStatus96x;
    }

    public void setiStatus96x(String iStatus96x) {
        this.iStatus96x = iStatus96x;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHfCode() {
        return hfCode;
    }

    public void setHfCode(String hfCode) {
        this.hfCode = hfCode;
    }

    public String getdPR() {
        return dPR;
    }

    public void setdPR(String dPR) {
        this.dPR = dPR;
    }

    @Bindable
    public String getHf01() {
        return hf01;
    }

    public void setHf01(String hf01) {
        this.hf01 = hf01;
        notifyPropertyChanged(BR.hf01);
    }

    @Bindable
    public String getHf01a() {
        return hf01a;
    }

    public void setHf01a(String hf01a) {
        this.hf01a = hf01a;
        notifyPropertyChanged(BR.hf01a);
    }

    @Bindable
    public String getHf01b() {
        return hf01b;
    }

    public void setHf01b(String hf01b) {
        this.hf01b = hf01b;
        notifyPropertyChanged(BR.hf01b);
    }

    @Bindable
    public String getHf02() {
        return hf02;
    }

    public void setHf02(String hf02) {
        this.hf02 = hf02;
        notifyPropertyChanged(BR.hf02);
    }

    @Bindable
    public String getHf02a() {
        return hf02a;
    }

    public void setHf02a(String hf02a) {
        this.hf02a = hf02a;
        notifyPropertyChanged(BR.hf02a);
    }

    @Bindable
    public String getHf03() {
        return hf03;
    }

    public void setHf03(String hf03) {
        this.hf03 = hf03;
        notifyPropertyChanged(BR.hf03);
    }

    @Bindable
    public String getHf04() {
        return hf04;
    }

    public void setHf04(String hf04) {
        this.hf04 = hf04;
        notifyPropertyChanged(BR.hf04);
    }

    @Bindable
    public String getHf05() {
        return hf05;
    }

    public void setHf05(String hf05) {
        this.hf05 = hf05;
        notifyPropertyChanged(BR.hf05);
    }

    @Bindable
    public String getHf06() {
        return hf06;
    }

    public void setHf06(String hf06) {
        this.hf06 = hf06;
        notifyPropertyChanged(BR.hf06);
    }

    @Bindable
    public String getHf06a() {
        return hf06a;
    }

    public void setHf06a(String hf06a) {
        this.hf06a = hf06a;
        notifyPropertyChanged(BR.hf06a);
    }

    @Bindable
    public String getHf07() {
        return hf07;
    }

    public void setHf07(String hf07) {
        this.hf07 = hf07;
        notifyPropertyChanged(BR.hf07);
    }

    @Bindable
    public String getHf07a() {
        return hf07a;
    }

    public void setHf07a(String hf07a) {
        this.hf07a = hf07a;
        notifyPropertyChanged(BR.hf07a);
    }

    @Bindable
    public String getHf08() {
        return hf08;
    }

    public void setHf08(String hf08) {
        this.hf08 = hf08;
        notifyPropertyChanged(BR.hf08);
    }

    @Bindable
    public String getHf09m() {
        return hf09m;
    }

    public void setHf09m(String hf09m) {
        this.hf09m = hf09m;
        notifyPropertyChanged(BR.hf09m);
    }

    @Bindable
    public String getHf09d() {
        return hf09d;
    }

    public void setHf09d(String hf09d) {
        this.hf09d = hf09d;
        notifyPropertyChanged(BR.hf09d);
    }

    @Bindable
    public String getHf09y() {
        return hf09y;
    }

    public void setHf09y(String hf09y) {
        this.hf09y = hf09y;
        notifyPropertyChanged(BR.hf09y);
    }

    @Bindable
    public String getNfamily() {
        return nfamily;
    }

    public void setNfamily(String nfamily) {
        this.nfamily = nfamily;
        notifyPropertyChanged(BR.nfamily);
    }

    public DPR Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_UID));
        this.uuid = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_UUID));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_USERNAME));
        this.projectName = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_PROJECT_NAME));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_SYSDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_DEVICEID));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_SYNCED_DATE));
        this.endTime = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_END_TIME));
        this.startTime = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_START_TIME));
        this.flag = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_FLAG));
        this.hfCode = cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_HF_CODE));
        dPRHydrate(cursor.getString(cursor.getColumnIndexOrThrow(VisitorsTable.COLUMN_DPR)));

        return this;
    }

    public void dPRHydrate(String string) throws JSONException {
        Log.d(TAG, "dPRHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.hf01 = json.getString("hf01");
            this.hf01a = json.has("hf01a") ? json.getString("hf01a") : "";
            this.hf01b = json.has("hf01b") ? json.getString("hf01b") : "";
            this.hf02 = json.getString("hf02");
            this.hf02a = json.has("hf02a") ?  json.getString("hf02a") : "";
            this.hf03 = json.getString("hf03");
            this.hf04 = json.getString("hf04");
            this.hf05 = json.getString("hf05");
            this.hf06 = json.getString("hf06");
            this.hf06a = json.has("hf06a") ?  json.getString("hf06a") : "";
            this.hf07 = json.getString("hf07");
            this.hf07a = json.has("hf07a") ?  json.getString("hf07a") : "";
            this.hf08 = json.getString("gender");
            this.hf09m = json.getString("agem");
            this.hf09d = json.has("aged") ?  json.getString("aged") : "";
            this.hf09y = json.getString("agey");
            this.nfamily = json.has("nfamily") ?  json.getString("nfamily") : "";
        }
    }


    public String dPRtoString() throws JSONException {
        Log.d(TAG, "dPRtoString: ");
        JSONObject json = new JSONObject();

        json.put("hf01", hf01)
                .put("hf01a", hf01a)
                .put("hf01b", hf01b)
                .put("hf02", hf02)
                .put("hf02a", hf02a)
                .put("hf03", hf03)
                .put("hf04", hf04)
                .put("hf05", hf05)
                .put("hf06", hf06)
                .put("hf06a", hf06a)
                .put("hf07", hf07)
                .put("hf07a", hf07a)
                .put("gender", hf08)
                .put("agem", hf09m)
                .put("aged", hf09d)
                .put("agey", hf09y)
                .put("nfamily", nfamily);

        return json.toString();
    }


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(VisitorsTable.COLUMN_ID, this.id);
        json.put(VisitorsTable.COLUMN_UID, this.uid);
        json.put(VisitorsTable.COLUMN_UUID, this.uuid);
        json.put(VisitorsTable.COLUMN_USERNAME, this.userName);
        json.put(VisitorsTable.COLUMN_PROJECT_NAME, this.projectName);
        json.put(VisitorsTable.COLUMN_SYSDATE, this.sysDate);
        json.put(VisitorsTable.COLUMN_DEVICEID, this.deviceId);
        json.put(VisitorsTable.COLUMN_APPVERSION, this.appver);
        json.put(VisitorsTable.COLUMN_ISTATUS, this.iStatus);
        json.put(VisitorsTable.COLUMN_SYNCED, this.synced);
        json.put(VisitorsTable.COLUMN_SYNCED_DATE, this.syncDate);
        json.put(VisitorsTable.COLUMN_END_TIME, this.endTime);
        json.put(VisitorsTable.COLUMN_START_TIME, this.startTime);
        json.put(VisitorsTable.COLUMN_FLAG, this.flag);
        json.put(VisitorsTable.COLUMN_HF_CODE, this.hfCode);

        json.put(VisitorsTable.COLUMN_DPR, new JSONObject(dPRtoString()));
        return json;
    }
}
