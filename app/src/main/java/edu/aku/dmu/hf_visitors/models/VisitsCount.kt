package edu.aku.dmu.hf_visitors.models

import android.database.Cursor
import edu.aku.dmu.hf_visitors.contracts.TableContracts.VisitsCountTable
import edu.aku.dmu.hf_visitors.core.MainApp
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by hussain.siddiqui on 31/01/2024.
 */

class VisitsCount {
    var ID: Long = 0
    var hf02: String = MainApp._EMPTY_
    var hf_code: String = MainApp._EMPTY_
    var hf03: String = MainApp._EMPTY_
    var tot_visits: String = MainApp._EMPTY_
    var month_visits: String = MainApp._EMPTY_

    constructor() {
        // Default Constructor
    }

    @Throws(JSONException::class)
    fun sync(jsonObject: JSONObject): VisitsCount {
        hf02 = jsonObject.getString(VisitsCountTable.COLUMN_HF02)
        hf_code = jsonObject.getString(VisitsCountTable.COLUMN_HF_CODE)
        hf03 = jsonObject.getString(VisitsCountTable.COLUMN_HF03)
        tot_visits = jsonObject.getString(VisitsCountTable.COLUMN_TOT_VISITS)
        month_visits = jsonObject.getString(VisitsCountTable.COLUMN_MONTH_VISITS)
        return this
    }

    fun hydrate(cursor: Cursor): VisitsCount {
        ID =
            cursor.getLong(cursor.getColumnIndexOrThrow(VisitsCountTable.COLUMN_ID))
        hf02 = cursor.getString(cursor.getColumnIndexOrThrow(VisitsCountTable.COLUMN_HF02))
        hf_code = cursor.getString(cursor.getColumnIndexOrThrow(VisitsCountTable.COLUMN_HF_CODE))
        hf03 = cursor.getString(cursor.getColumnIndexOrThrow(VisitsCountTable.COLUMN_HF03))
        tot_visits =
            cursor.getString(cursor.getColumnIndexOrThrow(VisitsCountTable.COLUMN_TOT_VISITS))
        month_visits =
            cursor.getString(cursor.getColumnIndexOrThrow(VisitsCountTable.COLUMN_MONTH_VISITS))
        return this
    }
}