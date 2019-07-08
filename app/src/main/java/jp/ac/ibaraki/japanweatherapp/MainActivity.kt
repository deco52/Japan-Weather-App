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

import rx.Observable
import rx.plugins.RxJavaHooks.onError


class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    private val compositeSubscription = CompositeSubscription()
    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.buttonNext.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            // BackStackを設定
            fragmentTransaction.addToBackStack(null)
            // パラメータを設定
            fragmentTransaction.replace(
                    R.id.container,
                    RequestFragment()
            )
            fragmentTransaction.commit()
        }

        /*
            // ボタンクリックイベントたち
            binding.buttonFragment.setOnClickListener { _ ->
                // Bundle（オブジェクトの入れ物）のインスタンスを作成する
                val bundle = Bundle()
                count++
                // Key/Pairの形で値をセットする
                bundle.putInt("COUNT", count)
                // Fragmentに値をセットする
                val fragment = BlankFragment()
                fragment.setArguments(bundle)
                supportFragmentManager.beginTransaction().add(R.id.layout_fragment, fragment).commit()
            }
            binding.buttonNextFragment.setOnClickListener {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_place, NextFragment.newInstance("a", "i"))
                transaction.addToBackStack(null)
                transaction.commit()
            }
            binding.buttonSubmit.setOnClickListener { _ ->
                val zipcode = binding.editText.text.toString()
                // テキストが空でなければ通信開始
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
            */
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName!!
    }
}