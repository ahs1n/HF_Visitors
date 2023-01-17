package edu.aku.dmu.hf_visitors.models

import android.database.Cursor
import edu.aku.dmu.hf_visitors.contracts.TableContracts.ListingMembersTable
import edu.aku.dmu.hf_visitors.core.MainApp._EMPTY_
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by muhammad.hussain on 11/01/2023.
 */
class ListingMembers {
    var ID: Long = 0
    var uID: String = _EMPTY_
    var rtype: String = _EMPTY_
    var hf_code: String = _EMPTY_
    var geoarea: String = _EMPTY_
    var familyNo: String = _EMPTY_
    var hhid: String = _EMPTY_
    var head: String = _EMPTY_
    var cellNo: String = _EMPTY_
    var pwName: String = _EMPTY_
    var childName: String = _EMPTY_
    var newMemberName: String = _EMPTY_

    constructor() {
        // Default Constructor
    }

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): ListingMembers {
        uID = jsonObject.getString(ListingMembersTable.COLUMN_UID)
        rtype = jsonObject.getString(ListingMembersTable.COLUMN_R_TYPE)
        hf_code = jsonObject.getString(ListingMembersTable.COLUMN_HF_CODE)
        geoarea = jsonObject.getString(ListingMembersTable.COLUMN_GEOAREA)
        familyNo = jsonObject.getString(ListingMembersTable.COLUMN_FAMILY_NO)
        hhid = jsonObject.getString(ListingMembersTable.COLUMN_HHID)
        head = jsonObject.getString(ListingMembersTable.COLUMN_HEAD)
        cellNo = jsonObject.getString(ListingMembersTable.COLUMN_CELL_NO)
        pwName = jsonObject.getString(ListingMembersTable.COLUMN_PW_NAME)
        childName = jsonObject.getString(ListingMembersTable.COLUMN_CHILD_NAME)
        newMemberName = jsonObject.getString(ListingMembersTable.COLUMN_NEW_MEMBER_NAME)
        return this
    }

    fun hydrate(cursor: Cursor): ListingMembers {
        ID = cursor.getLong(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_ID))
        rtype = cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_R_TYPE))
        uID =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_UID))
        hf_code =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_HF_CODE))
        geoarea =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_GEOAREA))
        familyNo =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_FAMILY_NO))
        hhid =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_HHID))
        head =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_HEAD))
        cellNo =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_CELL_NO))
        pwName =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_PW_NAME))
        childName =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_CHILD_NAME))
        newMemberName =
            cursor.getString(cursor.getColumnIndexOrThrow(ListingMembersTable.COLUMN_NEW_MEMBER_NAME))
        return this
    }
}