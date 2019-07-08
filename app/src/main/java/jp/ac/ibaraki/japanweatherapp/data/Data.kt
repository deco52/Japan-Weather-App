package jp.ac.ibaraki.japanweatherapp.data

data class Data(
        var address: ArrayList<String>? = ArrayList(),
        var kana: ArrayList<String>? = ArrayList(),
        var address1: String? = null,
        var kana1: String? = null,
        var address2: String? = null,
        var kana2: String? = null,
        var address3: String? = null,
        var kana3: String? = null,
        var prefcode: String? = null,
        var zipcode: String? = null
)