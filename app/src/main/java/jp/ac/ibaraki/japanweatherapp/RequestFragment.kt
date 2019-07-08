package jp.ac.ibaraki.japanweatherapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import jp.ac.ibaraki.japanweatherapp.MainActivity.Companion.TAG
import jp.ac.ibaraki.japanweatherapp.api.ApiClientManager
import jp.ac.ibaraki.japanweatherapp.api.response.DataResponse
import jp.ac.ibaraki.japanweatherapp.databinding.FragmentRequestBinding
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.lang.reflect.Type

/**
 * 検索文字列を入力するFragment MainActivity内のフラグメント表示部分に追加する形で運用する
 */
class RequestFragment : Fragment() {
    private val compositeSubscription = CompositeSubscription()

    // 通信結果を表す定数
    val HTTP_SUCCESS = "200"
    val HTTP_ERROR = "400"


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // データバインディングの設定・宣言
        var binding = DataBindingUtil.inflate<FragmentRequestBinding>(inflater, R.layout.fragment_request, container, false)

        binding.submitButton.setOnClickListener {
            val zipCode = binding.editText.text.toString()
            if (!isEmpty(zipCode)) { // ボックス内のテキストを引数にして通信を行う
                compositeSubscription.clear()
                compositeSubscription.add(
                        ApiClientManager.apiClient.getZipCode(zipCode)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnNext {
                                    Log.d(TAG, "rx response=$it")
                                    if (it.status.equals(HTTP_ERROR)) {// 不正なリクエスト値なのでエラーを表示
                                        AlertDialog.Builder(context!!)
                                                .setTitle("エラー")
                                                .setMessage(it.message)
                                                .setPositiveButton("ok") { dialog, which ->
                                                }.show()
                                    } else {// 正常処理
                                        // json変換
                                        /*val moshi = Moshi
                                            .Builder()
                                            .add(KotlinJsonAdapterFactory())
                                            .build()
                                        val parser = moshi.adapter(DataResponse::class.java)*/
                                            // val result = parser.fromJson(it)
                                            /*
                                        Log.d("JSON", Gson().toJson(it))
                                        // binding.textViewResponse.text = Gson().toJson(it)

                                        val jsonString : String = Gson().toJson(it)
                                        Log.d("Kotlin", jsonString)
                                        val type : Type = object : TypeToken<MutableMap<String, String>>() {}.type

                                        val map2 : MutableMap<String, String> = Gson().fromJson(jsonString, type)
                                        for (mapValue in map2.values) {
                                            Log.d("Kotlin", mapValue)
                                        }
                                        */

                                       // 値渡しの準備
                                        var bundle = Bundle()
                                        bundle.putString("string", "すとりんぐ")
                                        bundle.putSerializable("response", it)

                                        var nextFragment = ResultFragment()
                                        nextFragment.setArguments(bundle)
                                        // フラグメント差し替え
                                        val transaction = fragmentManager?.beginTransaction()
                                        transaction?.replace(R.id.container, nextFragment)
                                        transaction?.addToBackStack(null)
                                        transaction?.commit()
                                    }

                                }
                                .doOnError {
                                }
                                .doOnCompleted {
                                }
                                .subscribe())
            } else { // 未入力なのでエラーダイアログを表示
                AlertDialog.Builder(context!!)
                        .setTitle("エラー")
                        .setMessage("テキストを入力してください")
                        .setPositiveButton("ok") { dialog, which ->
                        }.show()
            }
        }
        // rootでviewを返している？
        return binding.root
    }
}