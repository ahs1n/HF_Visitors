package edu.aku.dmu.hf_visitors.contracts;

import android.provider.BaseColumns;

public class TableContracts {

    public static abstract class VisitorsTable implements BaseColumns {
        public static final String TABLE_NAME = "Visitors";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_DPR = "DPR";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_END_TIME = "end_time";
        public static final String COLUMN_START_TIME = "start_time";
        public static final String COLUMN_FLAG = "flag";
        public static final String COLUMN_HF_CODE = "hf_code";
    }

    public static abstract class ListingMembersTable implements BaseColumns {
        public static final String TABLE_NAME = "listvisitor";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_R_TYPE = "rtype";
        public static final String COLUMN_HF_CODE = "hf_code";
        public static final String COLUMN_GEOAREA = "geoarea";
        public static final String COLUMN_FAMILY_NO = "FamilyNo";
        public static final String COLUMN_HHID = "hhid";
        public static final String COLUMN_HEAD = "head";
        public static final String COLUMN_CELL_NO = "cellno";
        public static final String COLUMN_PW_NAME = "pwname";
        public static final String COLUMN_CHILD_NAME = "childname";
        public static final String COLUMN_NEW_MEMBER_NAME = "newmember";
        public static final String COLUMN_HF01A = "hf01a";
        public static final String COLUMN_HF01B = "hf01b";
        public static final String COLUMN_HF02A = "hf02a";
        public static final String COLUMN_HF06A = "hf06a";
        public static final String COLUMN_HF07A = "hf07a";
    }

    public static abstract class UsersTable implements BaseColumns {
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "passwordEnc";
        public static final String COLUMN_FULLNAME = "full_name";
        public static final String COLUMN_ENABLED = "enabled";
        public static final String COLUMN_ISNEW_USER = "isNewUser";
        public static final String COLUMN_PWD_EXPIRY = "pwdExpiry";
        public static final String COLUMN_DIST_ID = "dist_id";
        public static final String COLUMN_HF_CODE = "hfcode";

    }

    public static abstract class VersionTable implements BaseColumns {
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String TABLE_NAME = "versionApp";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_VERSION_PATH = "elements";
        public static final String COLUMN_VERSION_CODE = "versionCode";
        public static final String COLUMN_VERSION_NAME = "versionName";
        public static final String COLUMN_PATH_NAME = "outputFile";
        public static final String SERVER_URI = "output-metadata.json";

    }

    public static abstract class EntryLogTable implements BaseColumns {
        public static final String TABLE_NAME = "EntryLog";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_EB_CODE = "ebCode";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_ENTRY_DATE = "entryDate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNC_DATE = "sync_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96x = "istatus96x";
    }

    public static abstract class ClustersTable implements BaseColumns {
        public static final String TABLE_NAME = "Clusters";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CLUSTER_NO = "cluster_no";
        public static final String COLUMN_DIST_ID = "dist_id";
        public static final String COLUMN_DIST = "district";
        public static final String COLUMN_PROVINCE = "province";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_AREA = "area";
        public static final String COLUMN_EB_CODE = "ebcode";
        public static final String COLUMN_HF_CODE = "hf_code";
    }

    // View to generate family no. programmatically
    public static abstract class NFamilyMaxTable implements BaseColumns {
        public static final String TABLE_NAME = "NEWFAMILYMAX";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HF_CODE = "hf_code";
        public static final String COLUMN_CLUSTER_NO = "cluster_no";
        public static final String COLUMN_N_MAX = "nmax";
    }

    public static abstract class VisitsCountTable implements BaseColumns {
        public static final String TABLE_NAME = "visitcount";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HF02 = "hf02";
        public static final String COLUMN_HF_CODE = "hf_code";
        public static final String COLUMN_HF03 = "hf03";
        public static final String COLUMN_TOT_VISITS = "tot_visits";
        public static final String COLUMN_MONTH_VISITS = "month_visits";
    }
}
