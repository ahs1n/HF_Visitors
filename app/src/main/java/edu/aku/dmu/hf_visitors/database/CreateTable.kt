package edu.aku.dmu.hf_visitors.database

import edu.aku.dmu.hf_visitors.contracts.TableContracts.*

object CreateTable {

    const val SQL_CREATE_VISITORS = ("CREATE TABLE "
            + VisitorsTable.TABLE_NAME + "("
            + VisitorsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VisitorsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + VisitorsTable.COLUMN_UID + " TEXT,"
            + VisitorsTable.COLUMN_UUID + " TEXT,"
            + VisitorsTable.COLUMN_USERNAME + " TEXT,"
            + VisitorsTable.COLUMN_SYSDATE + " TEXT,"
            + VisitorsTable.COLUMN_START_TIME + " TEXT,"
            + VisitorsTable.COLUMN_FLAG + " TEXT,"
            + VisitorsTable.COLUMN_HF_CODE + " TEXT,"
            + VisitorsTable.COLUMN_END_TIME + " TEXT,"
            + VisitorsTable.COLUMN_ISTATUS + " TEXT,"
            + VisitorsTable.COLUMN_DEVICEID + " TEXT,"
            + VisitorsTable.COLUMN_SYNCED + " TEXT,"
            + VisitorsTable.COLUMN_SYNCED_DATE + " TEXT,"
            + VisitorsTable.COLUMN_APPVERSION + " TEXT,"
            + VisitorsTable.COLUMN_DPR + " TEXT"
            + " );"
            )

    const val SQL_CREATE_ListingMembers = ("CREATE TABLE "
            + ListingMembersTable.TABLE_NAME + "("
            + ListingMembersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ListingMembersTable.COLUMN_UID + " TEXT,"
            + ListingMembersTable.COLUMN_R_TYPE + " TEXT,"
            + ListingMembersTable.COLUMN_HF_CODE + " TEXT,"
            + ListingMembersTable.COLUMN_GEOAREA + " TEXT,"
            + ListingMembersTable.COLUMN_FAMILY_NO + " TEXT,"
            + ListingMembersTable.COLUMN_HHID + " TEXT,"
            + ListingMembersTable.COLUMN_HEAD + " TEXT,"
            + ListingMembersTable.COLUMN_CELL_NO + " TEXT,"
            + ListingMembersTable.COLUMN_PW_NAME + " TEXT,"
            + ListingMembersTable.COLUMN_CHILD_NAME + " TEXT,"
            + ListingMembersTable.COLUMN_NEW_MEMBER_NAME + " TEXT,"
            + ListingMembersTable.COLUMN_HF01A + " TEXT,"
            + ListingMembersTable.COLUMN_HF01B + " TEXT,"
            + ListingMembersTable.COLUMN_HF02A + " TEXT,"
            + ListingMembersTable.COLUMN_HF06A + " TEXT,"
            + ListingMembersTable.COLUMN_HF07A + " TEXT"
            + " );"
            )


    const val SQL_CREATE_USERS = ("CREATE TABLE "
            + UsersTable.TABLE_NAME + "("
            + UsersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_ENABLED + " TEXT,"
            + UsersTable.COLUMN_DIST_ID + " TEXT,"
            + UsersTable.COLUMN_HF_CODE + " TEXT,"
            + UsersTable.COLUMN_ISNEW_USER + " TEXT,"
            + UsersTable.COLUMN_PWD_EXPIRY + " TEXT,"
            + UsersTable.COLUMN_FULLNAME + " TEXT"
            + " );"
            )

    const val SQL_ALTER_USERS_ENABLED = ("Alter TABLE "
            + UsersTable.TABLE_NAME + " ADD "
            + UsersTable.COLUMN_ENABLED + " TEXT"
            + " ;"
            )

    const val SQL_ALTER_USERS_ISNEW_USER = ("Alter TABLE "
            + UsersTable.TABLE_NAME + " ADD "
            + UsersTable.COLUMN_ISNEW_USER + " TEXT"
            + " ;"
            )
    const val SQL_ALTER_USERS_PWD_EXPIRY = ("Alter TABLE "
            + UsersTable.TABLE_NAME + " ADD "
            + UsersTable.COLUMN_PWD_EXPIRY + " TEXT"
            + " ;"
            )

    const val SQL_CREATE_VERSIONAPP = ("CREATE TABLE "
            + VersionTable.TABLE_NAME + " ("
            + VersionTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VersionTable.COLUMN_VERSION_CODE + " TEXT, "
            + VersionTable.COLUMN_VERSION_NAME + " TEXT, "
            + VersionTable.COLUMN_PATH_NAME + " TEXT "
            + ");"
            )

    const val SQL_CREATE_ENTRYLOGS = ("CREATE TABLE "
            + EntryLogTable.TABLE_NAME + "("
            + EntryLogTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EntryLogTable.COLUMN_PROJECT_NAME + " TEXT,"
            + EntryLogTable.COLUMN_UID + " TEXT,"
            + EntryLogTable.COLUMN_UUID + " TEXT,"
            + EntryLogTable.COLUMN_EB_CODE + " TEXT,"
            + EntryLogTable.COLUMN_USERNAME + " TEXT,"
            + EntryLogTable.COLUMN_SYSDATE + " TEXT,"
            + EntryLogTable.COLUMN_DEVICEID + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_DATE + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS96x + " TEXT,"
            + EntryLogTable.COLUMN_SYNCED + " TEXT,"
            + EntryLogTable.COLUMN_SYNC_DATE + " TEXT,"
            + EntryLogTable.COLUMN_APPVERSION + " TEXT"
            + " );"
            )

    const val SQL_CREATE_CLUSTERS = ("CREATE TABLE "
            + ClustersTable.TABLE_NAME + "("
            + ClustersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ClustersTable.COLUMN_CLUSTER_NO + " TEXT,"
            + ClustersTable.COLUMN_DIST_ID + " TEXT,"
            + ClustersTable.COLUMN_DIST + " TEXT,"
            + ClustersTable.COLUMN_PROVINCE + " TEXT,"
            + ClustersTable.COLUMN_CITY + " TEXT,"
            + ClustersTable.COLUMN_AREA + " TEXT,"
            + ClustersTable.COLUMN_EB_CODE + " TEXT,"
            + ClustersTable.COLUMN_HF_CODE + " TEXT"
            + " );"
            )

    const val SQL_CREATE_N_FAMILY_MAX = ("CREATE TABLE "
            + NFamilyMaxTable.TABLE_NAME + "("
            + NFamilyMaxTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NFamilyMaxTable.COLUMN_HF_CODE + " TEXT,"
            + NFamilyMaxTable.COLUMN_CLUSTER_NO + " TEXT,"
            + NFamilyMaxTable.COLUMN_N_MAX + " TEXT"
            + " );"
            )

    const val SQL_ALTER_ADD_hf01a = ("ALTER TABLE "
            + ListingMembersTable.TABLE_NAME + " ADD COLUMN "
            + ListingMembersTable.COLUMN_HF01A + " TEXT;")

    const val SQL_ALTER_ADD_hf01b = ("ALTER TABLE "
            + ListingMembersTable.TABLE_NAME + " ADD COLUMN "
            + ListingMembersTable.COLUMN_HF01B + " TEXT;")

    const val SQL_ALTER_ADD_hf02a = ("ALTER TABLE "
            + ListingMembersTable.TABLE_NAME + " ADD COLUMN "
            + ListingMembersTable.COLUMN_HF02A + " TEXT;")

    const val SQL_ALTER_ADD_hf06a = ("ALTER TABLE "
            + ListingMembersTable.TABLE_NAME + " ADD COLUMN "
            + ListingMembersTable.COLUMN_HF06A + " TEXT;")

    const val SQL_ALTER_ADD_hf07a = ("ALTER TABLE "
            + ListingMembersTable.TABLE_NAME + " ADD COLUMN "
            + ListingMembersTable.COLUMN_HF07A + " TEXT;")
}