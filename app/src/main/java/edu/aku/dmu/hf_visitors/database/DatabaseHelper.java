package edu.aku.dmu.hf_visitors.database;

import static edu.aku.dmu.hf_visitors.core.MainApp.IBAHC;
import static edu.aku.dmu.hf_visitors.core.MainApp.PROJECT_NAME;
import static edu.aku.dmu.hf_visitors.core.MainApp.sharedPref;
import static edu.aku.dmu.hf_visitors.core.UserAuth.checkPassword;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_ALTER_ADD_hf01a;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_ALTER_ADD_hf01b;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_ALTER_ADD_hf02a;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_ALTER_ADD_hf06a;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_ALTER_ADD_hf07a;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_CLUSTERS;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_ENTRYLOGS;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_ListingMembers;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_N_FAMILY_MAX;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_USERS;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_VISITORS;
import static edu.aku.dmu.hf_visitors.database.CreateTable.SQL_CREATE_VISIT_COUNT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import edu.aku.dmu.hf_visitors.contracts.TableContracts;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.ClustersTable;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.EntryLogTable;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.ListingMembersTable;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.UsersTable;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.VisitorsTable;
import edu.aku.dmu.hf_visitors.contracts.TableContracts.VisitsCountTable;
import edu.aku.dmu.hf_visitors.core.MainApp;
import edu.aku.dmu.hf_visitors.models.Clusters;
import edu.aku.dmu.hf_visitors.models.DPR;
import edu.aku.dmu.hf_visitors.models.EntryLog;
import edu.aku.dmu.hf_visitors.models.ListingMembers;
import edu.aku.dmu.hf_visitors.models.NFamilyMax;
import edu.aku.dmu.hf_visitors.models.Users;
import edu.aku.dmu.hf_visitors.models.VisitsCount;

/**
 * @author muhammad.hussain on 11/01/2023.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = PROJECT_NAME + ".db";
    public static final String DATABASE_COPY = PROJECT_NAME + "_copy.db";
    public static final String DATABASE_PASSWORD = IBAHC;
    private static final int DATABASE_VERSION = 3;
    private final String TAG = "DatabaseHelper";
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_VISITORS);
        db.execSQL(SQL_CREATE_ListingMembers);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_ENTRYLOGS);
        db.execSQL(SQL_CREATE_CLUSTERS);
        db.execSQL(SQL_CREATE_N_FAMILY_MAX);
        db.execSQL(SQL_CREATE_VISIT_COUNT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(SQL_CREATE_CLUSTERS);
            case 2:
                db.execSQL(SQL_ALTER_ADD_hf01a);
                db.execSQL(SQL_ALTER_ADD_hf01b);
                db.execSQL(SQL_ALTER_ADD_hf02a);
                db.execSQL(SQL_ALTER_ADD_hf06a);
                db.execSQL(SQL_ALTER_ADD_hf07a);
            case 3:
                db.execSQL(SQL_CREATE_N_FAMILY_MAX);
                db.execSQL(SQL_CREATE_VISIT_COUNT);
        }
    }


    //ADDITION in DB
    public Long addMember(DPR dpr) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(VisitorsTable.COLUMN_PROJECT_NAME, dpr.getProjectName());
        values.put(VisitorsTable.COLUMN_UID, dpr.getUid());
        values.put(VisitorsTable.COLUMN_UUID, dpr.getUuid());
        values.put(VisitorsTable.COLUMN_USERNAME, dpr.getUserName());
        values.put(VisitorsTable.COLUMN_SYSDATE, dpr.getSysDate());
        values.put(VisitorsTable.COLUMN_ISTATUS, dpr.getiStatus());
        values.put(VisitorsTable.COLUMN_DEVICEID, dpr.getDeviceId());
        values.put(VisitorsTable.COLUMN_APPVERSION, dpr.getAppver());
        values.put(VisitorsTable.COLUMN_START_TIME, dpr.getStartTime());
        values.put(VisitorsTable.COLUMN_END_TIME, dpr.getEndTime());
        values.put(VisitorsTable.COLUMN_FLAG, dpr.getFlag());
        values.put(VisitorsTable.COLUMN_HF_CODE, dpr.getHfCode());
        values.put(VisitorsTable.COLUMN_DPR, dpr.dPRtoString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                VisitorsTable.TABLE_NAME,
                VisitorsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addEntryLog(EntryLog entryLog) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(EntryLogTable.COLUMN_PROJECT_NAME, entryLog.getProjectName());
        values.put(EntryLogTable.COLUMN_UUID, entryLog.getUuid());
        values.put(EntryLogTable.COLUMN_EB_CODE, entryLog.getEbCode());
        values.put(EntryLogTable.COLUMN_USERNAME, entryLog.getUserName());
        values.put(EntryLogTable.COLUMN_SYSDATE, entryLog.getSysDate());
        values.put(EntryLogTable.COLUMN_ISTATUS, entryLog.getiStatus());
        values.put(EntryLogTable.COLUMN_ISTATUS96x, entryLog.getiStatus96x());
        values.put(EntryLogTable.COLUMN_ENTRY_DATE, entryLog.getEntryDate());
        values.put(EntryLogTable.COLUMN_DEVICEID, entryLog.getDeviceId());
        values.put(EntryLogTable.COLUMN_SYNCED, entryLog.getSynced());
        values.put(EntryLogTable.COLUMN_SYNC_DATE, entryLog.getSyncDate());
        values.put(EntryLogTable.COLUMN_APPVERSION, entryLog.getAppver());

        long newRowId;
        newRowId = db.insertOrThrow(
                EntryLogTable.TABLE_NAME,
                EntryLogTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    //UPDATE in DB
    public int updateMemberColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = VisitorsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.dpr.getId())};

        return db.update(VisitorsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public int updatesEntryLogColumn(String column, String value, String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = EntryLogTable._ID + " =? ";
        String[] selectionArgs = {id};

        return db.update(EntryLogTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(VisitorsTable.COLUMN_ISTATUS, MainApp.dpr.getiStatus());

        // Which row to update, based on the ID
        String selection = VisitorsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.dpr.getId())};

        return db.update(VisitorsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    /*
     * Functions that dealing with table data
     * */
    public boolean doLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause = UsersTable.COLUMN_USERNAME + "=? ";
        String[] whereArgs = {username};
        String groupBy = null;
        String having = null;
        String orderBy = UsersTable.COLUMN_ID + " ASC";

        Users loggedInUser = new Users();
        c = db.query(
                UsersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            loggedInUser = new Users().hydrate(c);

        }

        c.close();

        if (loggedInUser.getPassword().equals("")) {
            Toast.makeText(mContext, "Stored password is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkPassword(password, loggedInUser.getPassword())) {
            MainApp.user = loggedInUser;
            //  MainApp.selectedDistrict = loggedInUser.getDist_id();
            return c.getCount() > 0;
        } else {
            return false;
        }
    }


    public ArrayList<DPR> getFormsByDate(String sysdate) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = {
                VisitorsTable._ID,
                VisitorsTable.COLUMN_UID,
                VisitorsTable.COLUMN_SYSDATE,
                VisitorsTable.COLUMN_USERNAME,
                VisitorsTable.COLUMN_ISTATUS,
                VisitorsTable.COLUMN_SYNCED,

        };
        String whereClause = VisitorsTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
        String groupBy = null;
        String having = null;
        String orderBy = VisitorsTable.COLUMN_ID + " ASC";
        ArrayList<DPR> allForms = new ArrayList<>();
        try {
            c = db.query(
                    VisitorsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                DPR forms = new DPR();
                forms.setId(c.getString(c.getColumnIndexOrThrow(VisitorsTable.COLUMN_ID)));
                forms.setUid(c.getString(c.getColumnIndexOrThrow(VisitorsTable.COLUMN_UID)));
                forms.setSysDate(c.getString(c.getColumnIndexOrThrow(VisitorsTable.COLUMN_SYSDATE)));
                forms.setUserName(c.getString(c.getColumnIndexOrThrow(VisitorsTable.COLUMN_USERNAME)));
                allForms.add(forms);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return allForms;
    }


    // istatus examples: (1) or (1,9) or (1,3,5)
    public DPR getFormByAssessNo(String uid, String istatus) throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        whereClause = VisitorsTable.COLUMN_UID + "=? AND " +
                VisitorsTable.COLUMN_ISTATUS + " in " + istatus;

        String[] whereArgs = {uid};

        String groupBy = null;
        String having = null;

        String orderBy = VisitorsTable.COLUMN_ID + " ASC";

        DPR allFC = null;
        try {
            c = db.query(
                    VisitorsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allFC = new DPR().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return allFC;
    }

    public ArrayList<Cursor> getDatabaseManagerData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase(DATABASE_PASSWORD);
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(Query, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (Exception sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    /*public int updateTemp(String assessNo, String temp) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD)

        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_TSF305, temp);
        values.put(FormsTable.COLUMN_SYSDATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()) + "-Updated");
        values.put(FormsTable.COLUMN_ISTATUS, "1");
        values.put(FormsTable.COLUMN_SYNCED, (byte[]) null);

        String selection = FormsTable.COLUMN_ASSESMENT_NO + " =? AND " + FormsTable.COLUMN_ISTATUS + " =? ";
        // String selection = FormsTable.COLUMN_ASSESMENT_NO + " =? ";
        String[] selectionArgs = {assessNo, "9"};
        // String[] selectionArgs = {assessNo};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }*/


    public int syncVersionApp(JSONArray VersionList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        long count = 0;

        JSONObject jsonObjectVersion = ((JSONArray) VersionList.getJSONObject(0).get("elements")).getJSONObject(0);

        String appPath = jsonObjectVersion.getString("outputFile");
        String versionCode = jsonObjectVersion.getString("versionCode");

        MainApp.editor.putString("outputFile", jsonObjectVersion.getString("outputFile"));
        MainApp.editor.putString("versionCode", jsonObjectVersion.getString("versionCode"));
        MainApp.editor.putString("versionName", jsonObjectVersion.getString("versionName") + ".");
        MainApp.editor.apply();
        count++;
          /*  VersionApp Vc = new VersionApp();
            Vc.sync(jsonObjectVersion);

            ContentValues values = new ContentValues();

            values.put(VersionTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
            db.close();
        }*/

        return (int) count;
    }

    public int syncUsers(JSONArray userList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        for (int i = 0; i < userList.length(); i++) {

            JSONObject jsonObjectUser = userList.getJSONObject(i);

            Users user = new Users();
            user.sync(jsonObjectUser);
            ContentValues values = new ContentValues();

            values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
            values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
            values.put(UsersTable.COLUMN_FULLNAME, user.getFullname());
            values.put(UsersTable.COLUMN_ENABLED, user.getEnabled());
            values.put(UsersTable.COLUMN_ISNEW_USER, user.getNewUser());
            values.put(UsersTable.COLUMN_PWD_EXPIRY, user.getPwdExpiry());
            values.put(UsersTable.COLUMN_DIST_ID, user.getDist_id());
            values.put(UsersTable.COLUMN_HF_CODE, user.getHfcode());
            long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;
        }
        return insertCount;
    }

    public int syncClusters(JSONArray clustersList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(ClustersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        for (int i = 0; i < clustersList.length(); i++) {

            JSONObject jsonObjectCluster = clustersList.getJSONObject(i);

            Clusters clusters = new Clusters();
            clusters.sync(jsonObjectCluster);
            ContentValues values = new ContentValues();

            values.put(ClustersTable.COLUMN_CLUSTER_NO, clusters.getClusterNo());
            values.put(ClustersTable.COLUMN_DIST_ID, clusters.getDist_id());
            values.put(ClustersTable.COLUMN_DIST, clusters.getDist());
            values.put(ClustersTable.COLUMN_PROVINCE, clusters.getProvince());
            values.put(ClustersTable.COLUMN_CITY, clusters.getCity());
            values.put(ClustersTable.COLUMN_AREA, clusters.getArea());
            values.put(ClustersTable.COLUMN_EB_CODE, clusters.getEbCode());
            values.put(ClustersTable.COLUMN_HF_CODE, clusters.getHfcode());
            long rowID = db.insert(ClustersTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;
        }
        return insertCount;
    }

    /*Sync ListingMembers*/
    public int synclistvisitor(JSONArray formCR) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(ListingMembersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        for (int i = 0; i < formCR.length(); i++) {

            JSONObject jsonObjectUser = formCR.getJSONObject(i);

            ListingMembers listingMembers = new ListingMembers();
            listingMembers.sync(jsonObjectUser);
            ContentValues values = new ContentValues();

            values.put(ListingMembersTable.COLUMN_UID, listingMembers.getUID());
            values.put(ListingMembersTable.COLUMN_R_TYPE, listingMembers.getRtype());
            values.put(ListingMembersTable.COLUMN_HF_CODE, listingMembers.getHf_code());
            values.put(ListingMembersTable.COLUMN_GEOAREA, listingMembers.getGeoarea());
            values.put(ListingMembersTable.COLUMN_FAMILY_NO, listingMembers.getFamilyNo());
            values.put(ListingMembersTable.COLUMN_HHID, listingMembers.getHhid());
            values.put(ListingMembersTable.COLUMN_HEAD, listingMembers.getHead());
            values.put(ListingMembersTable.COLUMN_CELL_NO, listingMembers.getCellNo());
            values.put(ListingMembersTable.COLUMN_PW_NAME, listingMembers.getPwName());
            values.put(ListingMembersTable.COLUMN_CHILD_NAME, listingMembers.getChildName());
            values.put(ListingMembersTable.COLUMN_NEW_MEMBER_NAME, listingMembers.getNewMemberName());
            values.put(ListingMembersTable.COLUMN_HF01A, listingMembers.getHf01a());
            values.put(ListingMembersTable.COLUMN_HF01B, listingMembers.getHf01b());
            values.put(ListingMembersTable.COLUMN_HF02A, listingMembers.getHf02a());
            values.put(ListingMembersTable.COLUMN_HF06A, listingMembers.getHf06a());
            values.put(ListingMembersTable.COLUMN_HF07A, listingMembers.getHf07a());

            long rowID = db.insert(ListingMembersTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;
        }
        return insertCount;
    }


    //get UnSyncedTables
    public JSONArray getUnsyncedVisitors() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        whereClause = VisitorsTable.COLUMN_SYNCED + " is null ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = VisitorsTable.COLUMN_ID + " ASC";

        JSONArray allCR = new JSONArray();
        c = db.query(
                VisitorsTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedFormCR: " + c.getCount());
            DPR cr = new DPR();
            allCR.put(cr.Hydrate(c).toJSONObject());
        }
        Log.d(TAG, "getUnsyncedFormCR: " + allCR.toString().length());
        Log.d(TAG, "getUnsyncedFormCR: " + allCR);
        return allCR;
    }


    public JSONArray getUnsyncedEntryLog() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause;
        whereClause = EntryLogTable.COLUMN_SYNCED + " = '' ";

        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = EntryLogTable.COLUMN_ID + " ASC";

        JSONArray all = new JSONArray();
        c = db.query(
                EntryLogTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            Log.d(TAG, "getUnsyncedEntryLog: " + c.getCount());
            EntryLog entryLog = new EntryLog();
            all.put(entryLog.Hydrate(c).toJSONObject());
        }
        Log.d(TAG, "getUnsyncedEntryLog: " + all.toString().length());
        Log.d(TAG, "getUnsyncedEntryLog: " + all);
        return all;
    }

    //update SyncedTables
    public void updateSyncedVisitors(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(VisitorsTable.COLUMN_SYNCED, true);
        values.put(VisitorsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = VisitorsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                VisitorsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedEntryLog(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(EntryLogTable.COLUMN_SYNCED, true);
        values.put(EntryLogTable.COLUMN_SYNC_DATE, new Date().toString());

        String where = EntryLogTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                EntryLogTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase(DATABASE_PASSWORD);
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }


    public int updatePassword(String hashedPassword) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(UsersTable.COLUMN_PASSWORD, hashedPassword);
        values.put(UsersTable.COLUMN_ISNEW_USER, "");

        String selection = UsersTable.COLUMN_USERNAME + " =? ";
        String[] selectionArgs = {MainApp.user.getUserName()};

        return db.update(UsersTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    /*Get all forms in list from FormCRFollowUP*/
    public List<ListingMembers> getAllChilds() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c;
        String[] columns = null;
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = ListingMembersTable.COLUMN_HHID + ", " + ListingMembersTable.COLUMN_R_TYPE + " ASC";

        List<ListingMembers> allForm = new ArrayList<>();

        c = db.query(
                ListingMembersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            ListingMembers listingMembers = new ListingMembers().hydrate(c);
            allForm.add(listingMembers);
        }
        c.close();
        return allForm;
    }

    /*Open form from list OnClick*/
    public ListingMembers getSelectedMembers(String hhid, String head, String pwName, String childName, String newMember) throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c;
        String[] columns = null;
        String whereClause = ListingMembersTable.COLUMN_HHID + " = ? AND " + ListingMembersTable.COLUMN_HEAD + " = ? AND " +
                ListingMembersTable.COLUMN_PW_NAME + " = ? AND " + ListingMembersTable.COLUMN_CHILD_NAME + " = ? AND " +
                ListingMembersTable.COLUMN_NEW_MEMBER_NAME + " = ?";
        String[] whereArgs = new String[]{hhid, head, pwName, childName, newMember};
        String groupBy = null;
        String having = null;
        String orderBy = ListingMembersTable.COLUMN_HHID + ", " + ListingMembersTable.COLUMN_R_TYPE + " ASC";
        ListingMembers listingMembers = new ListingMembers();
        c = db.query(
                ListingMembersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) listingMembers = new ListingMembers().hydrate(c);
        c.close();
        return listingMembers;
    }

    /*Search Member from list by Head Name*/
    public List<ListingMembers> getAllMembersByHHName(String hhName) throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c;
        String[] columns = null;

        String whereClause = ListingMembersTable.COLUMN_HEAD + " = ? ";
        String[] whereArgs = new String[]{hhName};
        String groupBy = null;
        String having = null;

        String orderBy = ListingMembersTable.COLUMN_HHID + ", " + ListingMembersTable.COLUMN_R_TYPE + " ASC";

        List<ListingMembers> allForm = new ArrayList<>();

        c = db.query(
                ListingMembersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            ListingMembers listingMembers = new ListingMembers().hydrate(c);
            allForm.add(listingMembers);
        }
        c.close();
        return allForm;
    }

    /*Search Member from list by HHID*/
    public List<ListingMembers> getAllMembersByHHID(String hhID) throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c;
        String[] columns = null;

        String whereClause = ListingMembersTable.COLUMN_HHID + " = ? ";
        String[] whereArgs = new String[]{hhID};
        String groupBy = null;
        String having = null;

        String orderBy = ListingMembersTable.COLUMN_HHID + ", " + ListingMembersTable.COLUMN_R_TYPE + " ASC";

        List<ListingMembers> allForm = new ArrayList<>();

        c = db.query(
                ListingMembersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            ListingMembers cr = new ListingMembers().hydrate(c);
            allForm.add(cr);
        }
        c.close();
        return allForm;
    }

    public Collection<Clusters> getClustersByHF(String hfCode) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause = ClustersTable.COLUMN_HF_CODE + " = ? ";
        String[] whereArgs = {hfCode};
        String groupBy = null;
        String having = null;

        String orderBy = ClustersTable.COLUMN_HF_CODE + " ASC";
        List<Clusters> clusters = new ArrayList<>();

        c = db.query(
                ClustersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            clusters.add(new Clusters().hydrate(c));
        }
        return clusters;
    }

    public int syncNEWFAMILYMAX(JSONArray nFamilyMaxList) throws JSONException {
        Gson gson = new Gson();
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(TableContracts.NFamilyMaxTable.TABLE_NAME, null, null);
        int insertCount = 0;
        LinkedHashMap<String, Object> nMaxHMDB = new LinkedHashMap<>();
        for (int i = 0; i < nFamilyMaxList.length(); i++) {


            NFamilyMax nMax = gson.fromJson(nFamilyMaxList.getJSONObject(i).toString(), NFamilyMax.class);
            nMaxHMDB.put(nMax.getCluster_no(), nMax.getNmax() != null && !nMax.getNmax().equals("") ? nMax.getNmax() : "0");

            ContentValues values = new ContentValues();

            values.put(TableContracts.NFamilyMaxTable.COLUMN_HF_CODE, nMax.getHf_code());
            values.put(TableContracts.NFamilyMaxTable.COLUMN_CLUSTER_NO, nMax.getCluster_no());
            values.put(TableContracts.NFamilyMaxTable.COLUMN_N_MAX, nMax.getNmax() != null && !nMax.getNmax().equals("") ? nMax.getNmax() : "0");
            long rowID = db.insert(TableContracts.NFamilyMaxTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;
        }

        if (nMaxHMDB.size() > 0) {
            String prevNMaxHM = sharedPref.getString("n_family_max", null);
            if (prevNMaxHM != null) {
                LinkedHashMap<String, Object> nMaxHMSP = gson.fromJson(prevNMaxHM, new TypeToken<LinkedHashMap<String, Object>>() {
                }.getType());
                for (int i = 0; i < nMaxHMDB.size(); i++) {
                    // This check is for the case if any new cluster is added
                    String clusterKeyDB = (String) nMaxHMDB.keySet().toArray()[i];
                    boolean isSPKeyExists = nMaxHMSP.containsKey(clusterKeyDB);
                    if (isSPKeyExists) {
                        int nMaxValueSP = Integer.parseInt((String) Objects.requireNonNull(nMaxHMSP.get(clusterKeyDB)));
                        int nMaxValueDB = Integer.parseInt(getNFamilyMax(MainApp.user.getHfcode(), clusterKeyDB));
                        nMaxHMSP.put(clusterKeyDB, Math.max(nMaxValueSP, nMaxValueDB));
                    } else {
                        String nMaxValueDB = (String) Objects.requireNonNull(nMaxHMDB.get(clusterKeyDB));
                        nMaxHMSP.put(clusterKeyDB, Integer.parseInt(nMaxValueDB));
                    }
                    /*if (i < nMaxHMSP.size()) {
                        // Now check which of the number is max. DB or Temporary Storage(Shared Preference).
                        // Whichever is max just save it
                        String clusterKeySP = (String) nMaxHMSP.keySet().toArray()[i];
                        int nMaxValueSP = Integer.parseInt((String) Objects.requireNonNull(nMaxHMSP.get(clusterKeySP)));

                        int nMaxValueDB = Integer.parseInt(getNFamilyMax(MainApp.user.getHfcode(), clusterKeySP));
                        nMaxHMSP.put(clusterKeySP, Math.max(nMaxValueSP, nMaxValueDB));
                    } else {
                        // If there is an additional record which was not present earlier
                        // then save it
                        String clusterKeyDB = (String) nMaxHMDB.keySet().toArray()[i];
                        String nMaxValueDB = (String) Objects.requireNonNull(nMaxHMDB.get(clusterKeyDB));
                        nMaxHMSP.put(clusterKeyDB, Integer.parseInt(nMaxValueDB));
                    }*/
                }
            } else {
                // Save in temporary storage for comparison
                sharedPref.edit().putString("n_family_max", gson.toJson(nMaxHMDB)).apply();
            }
        }
        return insertCount;
    }

    /*Open form from list OnClick*/
    public String getNFamilyMax(String hfCode, String clusterNo) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c;
        String[] columns = null;
        String whereClause = TableContracts.NFamilyMaxTable.COLUMN_HF_CODE + " = ? AND "
                + TableContracts.NFamilyMaxTable.COLUMN_CLUSTER_NO + " = ? ";
        String[] whereArgs = new String[]{hfCode, clusterNo};
        String groupBy = null;
        String having = null;
        String orderBy = TableContracts.NFamilyMaxTable.COLUMN_ID + " DESC";
        NFamilyMax nMax = new NFamilyMax();
        c = db.query(
                TableContracts.NFamilyMaxTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) nMax = new NFamilyMax().hydrate(c);
        c.close();
        return nMax.getNmax() != null && !nMax.getNmax().equals("") ? nMax.getNmax() : "0";
    }

    public int syncvisitcount(JSONArray visitsList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(VisitsCountTable.TABLE_NAME, null, null);
        int insertCount = 0;
        for (int i = 0; i < visitsList.length(); i++) {

            JSONObject jsonObjectVisits = visitsList.getJSONObject(i);

            VisitsCount visitsCount = new VisitsCount();
            visitsCount.sync(jsonObjectVisits);
            ContentValues values = new ContentValues();

            values.put(VisitsCountTable.COLUMN_HF02, visitsCount.getHf02());
            values.put(VisitsCountTable.COLUMN_HF_CODE, visitsCount.getHf_code());
            values.put(VisitsCountTable.COLUMN_HF03, visitsCount.getHf03());
            values.put(VisitsCountTable.COLUMN_TOT_VISITS, visitsCount.getTot_visits());
            values.put(VisitsCountTable.COLUMN_MONTH_VISITS, visitsCount.getMonth_visits());
            long rowID = db.insert(VisitsCountTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;
        }
        return insertCount;
    }

    public VisitsCount getVisitsCountByHFANDHHID(String hfCode, String hhid, String head) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause = VisitsCountTable.COLUMN_HF_CODE + " = ? AND "
                 + VisitsCountTable.COLUMN_HF02 + " = ? AND "
                 + VisitsCountTable.COLUMN_HF03 + " = ? ";
        String[] whereArgs = {hfCode, hhid, head};
        String groupBy = null;
        String having = null;

        String orderBy = VisitsCountTable.COLUMN_HF_CODE + " ASC";
        VisitsCount visitsCounts = new VisitsCount();

        c = db.query(
                VisitsCountTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            visitsCounts = new VisitsCount().hydrate(c);
        }
        return visitsCounts;
    }
}