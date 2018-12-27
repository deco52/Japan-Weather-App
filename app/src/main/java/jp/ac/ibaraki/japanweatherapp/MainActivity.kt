package jp.ac.ibaraki.japanweatherapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import jp.ac.ibaraki.japanweatherapp.api.ApiClientManager
import jp.ac.ibaraki.japanweatherapp.api.response.DataResponse
import jp.ac.ibaraki.japanweatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    private val compositeSubscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonSubmit.setOnClickListener { _ ->
            val zipcode = binding.editText.text.toString()
            if (!TextUtils.isEmpty(zipcode)) {
                compositeSubscription.clear()
                compositeSubscription.add(
                        ApiClientManager.apiClient.getZipCode(zipcode)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnNext {
                                    Log.d(TAG, "rx response=$it")
                                    binding.textViewResponse.text = Gson().toJson(it)
                                }
                                .doOnError {
                                }
                                .doOnCompleted {
                                }
                                .subscribe())
            }
        }
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName!!
    }
}
