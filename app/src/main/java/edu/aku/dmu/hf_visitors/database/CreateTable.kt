package edu.aku.dmu.hf_visitors.database

import edu.aku.dmu.hf_visitors.contracts.TableContracts.*

object CreateTable {

    const val SQL_CREATE_FORMDPR = ("CREATE TABLE "
            + DPRTable.TABLE_NAME + "("
            + DPRTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DPRTable.COLUMN_PROJECT_NAME + " TEXT,"
            + DPRTable.COLUMN_UID + " TEXT,"
            + DPRTable.COLUMN_USERNAME + " TEXT,"
            + DPRTable.COLUMN_SYSDATE + " TEXT,"
            + DPRTable.COLUMN_START_TIME + " TEXT,"
            + DPRTable.COLUMN_END_TIME + " TEXT,"
            + DPRTable.COLUMN_ISTATUS + " TEXT,"
            + DPRTable.COLUMN_DEVICEID + " TEXT,"
            + DPRTable.COLUMN_SYNCED + " TEXT,"
            + DPRTable.COLUMN_SYNCED_DATE + " TEXT,"
            + DPRTable.COLUMN_APPVERSION + " TEXT,"
            + DPRTable.COLUMN_DPR + " TEXT"
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
            + ListingMembersTable.COLUMN_CHILD_NAME + " TEXT"
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
}