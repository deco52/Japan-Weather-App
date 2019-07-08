package jp.ac.ibaraki.japanweatherapp.api.response

import jp.ac.ibaraki.japanweatherapp.data.Data
import java.io.Serializable

data class DataResponse (
        var message: String? = null,
        var status: String? = null,
        var results: ArrayList<Data> = ArrayList()
): Serializable