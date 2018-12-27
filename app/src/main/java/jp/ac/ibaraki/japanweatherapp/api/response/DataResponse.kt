package jp.ac.ibaraki.japanweatherapp.api.response

import jp.ac.ibaraki.japanweatherapp.data.Data

data class DataResponse(
        var message: String? = null,
        var status: Int? = null,
        var results: ArrayList<Data> = ArrayList()
)