package jp.ac.ibaraki.japanweatherapp.api

import jp.ac.ibaraki.japanweatherapp.api.response.DataResponse
import java.util.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Observable

interface ApiClient {
    @GET("api/search")
    fun getZipCode(@Query("zipcode") zipcode: String): Observable<DataResponse>
}