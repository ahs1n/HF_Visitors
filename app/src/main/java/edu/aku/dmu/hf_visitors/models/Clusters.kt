package edu.aku.dmu.hf_visitors.models

import android.database.Cursor
import edu.aku.dmu.hf_visitors.contracts.TableContracts
import edu.aku.dmu.hf_visitors.contracts.TableContracts.ClustersTable
import edu.aku.dmu.hf_visitors.core.MainApp._EMPTY_
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by muhammad.hussain on 04/01/2024.
 */

class Clusters {
    var ID: Long = 0
    var clusterNo: String = _EMPTY_
    var dist_id: String = _EMPTY_
    var province: String = _EMPTY_
    var dist: String = _EMPTY_
    var city: String = _EMPTY_
    var area: String = _EMPTY_
    var ebCode: String = _EMPTY_
    var hfcode: String = _EMPTY_

    constructor() {
        // Default Constructor
    }

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): Clusters {
        clusterNo = jsonObject.getString(ClustersTable.COLUMN_CLUSTER_NO)
        dist_id = jsonObject.getString(ClustersTable.COLUMN_DIST_ID)
        province = jsonObject.getString(ClustersTable.COLUMN_PROVINCE)
        dist = jsonObject.getString(ClustersTable.COLUMN_DIST)
        city = jsonObject.getString(ClustersTable.COLUMN_CITY)
        area = jsonObject.getString(ClustersTable.COLUMN_AREA)
        ebCode = jsonObject.getString(ClustersTable.COLUMN_EB_CODE)
        hfcode = jsonObject.getString(ClustersTable.COLUMN_HF_CODE)
        return this
    }

    fun hydrate(cursor: Cursor): Clusters {
        ID =
            cursor.getLong(cursor.getColumnIndexOrThrow(TableContracts.ListingMembersTable.COLUMN_ID))
        clusterNo = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_CLUSTER_NO))
        dist_id = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_DIST_ID))
        province = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_PROVINCE))
        dist = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_DIST))
        city = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_CITY))
        area = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_AREA))
        ebCode = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_EB_CODE))
        hfcode = cursor.getString(cursor.getColumnIndexOrThrow(ClustersTable.COLUMN_HF_CODE))
        return this
    }
}