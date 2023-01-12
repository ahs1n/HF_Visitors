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
import edu.aku.dmu.hf_visitors.contracts.TableContracts.DPRTable;
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
    private String deviceTag = _EMPTY_;
    private String appver = _EMPTY_;
    private String endTime = _EMPTY_;
    private String startTime = _EMPTY_;
    private String iStatus = _EMPTY_;
    private String iStatus96x = _EMPTY_;
    private String synced = _EMPTY_;
    private String syncDate = _EMPTY_;
    // FIELD VARIABLES
    //  Section CR
    private String hf01 = _EMPTY_;
    private String hf02 = _EMPTY_;
    private String hf03 = _EMPTY_;
    private String hf04 = _EMPTY_;
    private String hf05 = _EMPTY_;
    private String hf06 = _EMPTY_;
    private String dPR = _EMPTY_;


    public void populateMeta() {

        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setDeviceId(MainApp.deviceid);
        setDeviceTag(MainApp.appInfo.getTagName());
        setAppver(MainApp.appInfo.getAppVersion());
        setProjectName(PROJECT_NAME);
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

    public String getDeviceTag() {
        return deviceTag;
    }

    public void setDeviceTag(String deviceTag) {
        this.deviceTag = deviceTag;
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
    public String getHf02() {
        return hf02;
    }

    public void setHf02(String hf02) {
        this.hf02 = hf02;
        notifyPropertyChanged(BR.hf02);
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

    public DPR Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_UID));
        this.uuid = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_UUID));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_USERNAME));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_SYSDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_DEVICEID));
        this.deviceTag = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_DEVICETAGID));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_SYNCED_DATE));
        this.endTime = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_END_TIME));
        this.startTime = cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_START_TIME));
        dPRHydrate(cursor.getString(cursor.getColumnIndexOrThrow(DPRTable.COLUMN_DPR)));

        return this;
    }

    public void dPRHydrate(String string) throws JSONException {
        Log.d(TAG, "dPRHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.hf01 = json.getString("hf01");
            this.hf02 = json.getString("hf02");
            this.hf03 = json.getString("hf03");
            this.hf04 = json.getString("hf04");
            this.hf05 = json.getString("hf05");
            this.hf06 = json.getString("hf06");
        }
    }


    public String dPRtoString() throws JSONException {
        Log.d(TAG, "dPRtoString: ");
        JSONObject json = new JSONObject();

        json.put("hf01", hf01)
                .put("hf02", hf02)
                .put("hf03", hf03)
                .put("hf04", hf04)
                .put("hf05", hf05)
                .put("hf06", hf06);

        return json.toString();
    }


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(DPRTable.COLUMN_ID, this.id);
        json.put(DPRTable.COLUMN_UID, this.uid);
        json.put(DPRTable.COLUMN_UUID, this.uuid);
        json.put(DPRTable.COLUMN_USERNAME, this.userName);
        json.put(DPRTable.COLUMN_SYSDATE, this.sysDate);
        json.put(DPRTable.COLUMN_DEVICEID, this.deviceId);
        json.put(DPRTable.COLUMN_DEVICETAGID, this.deviceTag);
        json.put(DPRTable.COLUMN_ISTATUS, this.iStatus);
        json.put(DPRTable.COLUMN_SYNCED, this.synced);
        json.put(DPRTable.COLUMN_SYNCED_DATE, this.syncDate);
        json.put(DPRTable.COLUMN_END_TIME, this.endTime);
        json.put(DPRTable.COLUMN_START_TIME, this.startTime);

        json.put(DPRTable.COLUMN_DPR, new JSONObject(dPRtoString()));
        return json;
    }
}
