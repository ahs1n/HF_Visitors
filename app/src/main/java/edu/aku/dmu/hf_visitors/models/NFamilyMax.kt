package edu.aku.dmu.hf_visitors.models

import android.database.Cursor
import edu.aku.dmu.hf_visitors.contracts.TableContracts
import edu.aku.dmu.hf_visitors.core.MainApp
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by omar.shoaib on 16/01/2024.
 */

class NFamilyMax {
    var ID: Long = 0
    var hf_code: String = MainApp._EMPTY_
    var cluster_no: String = MainApp._EMPTY_
    var nmax: String = MainApp._EMPTY_

    constructor() {
        // Default Constructor
    }

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): NFamilyMax {
        hf_code = jsonObject.getString(TableContracts.NFamilyMaxTable.COLUMN_HF_CODE)
        cluster_no = jsonObject.getString(TableContracts.NFamilyMaxTable.COLUMN_CLUSTER_NO)
        nmax = jsonObject.getString(TableContracts.NFamilyMaxTable.COLUMN_N_MAX)
        return this
    }

    fun hydrate(cursor: Cursor): NFamilyMax {
        ID =
            cursor.getLong(cursor.getColumnIndexOrThrow(TableContracts.NFamilyMaxTable.COLUMN_ID))
        hf_code = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.NFamilyMaxTable.COLUMN_HF_CODE))
        cluster_no = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.NFamilyMaxTable.COLUMN_CLUSTER_NO))
        nmax = cursor.getString(cursor.getColumnIndexOrThrow(TableContracts.NFamilyMaxTable.COLUMN_N_MAX))
        return this
    }
}