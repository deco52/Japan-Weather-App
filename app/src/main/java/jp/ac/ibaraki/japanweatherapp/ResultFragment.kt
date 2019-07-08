package jp.ac.ibaraki.japanweatherapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import jp.ac.ibaraki.japanweatherapp.api.response.DataResponse
import jp.ac.ibaraki.japanweatherapp.data.Data
import jp.ac.ibaraki.japanweatherapp.databinding.FragmentResultBinding
import rx.subscriptions.CompositeSubscription

/**
 * 検索文字列を入力するFragment MainActivity内のフラグメント表示部分に追加する形で運用する
 */
class ResultFragment : Fragment() {
    private val compositeSubscription = CompositeSubscription()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // 値渡しの受け取り
        val bundle = arguments
        val response = bundle!!.getSerializable("response") as DataResponse
        // データバインディングの設定・宣言
        var binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater, R.layout.fragment_result, container, false)
        var listData :ArrayList<ListData> = ArrayList()

        var dataArrayListModel: ArrayList<ListDataModel> = ArrayList()
        if(response.results.get(0).address1 != null) {
            dataArrayListModel.add(ListDataModel(response.results.get(0).address1!!, response.results.get(0).kana1!!))
        }
        if(response.results.get(0).address2 != null) {
            dataArrayListModel.add(ListDataModel(response.results.get(0).address2!!, response.results.get(0).kana2!!))
        }
        if(response.results.get(0).address3 != null) {
            dataArrayListModel.add(ListDataModel(response.results.get(0).address3!!, response.results.get(0).kana3!!))
        }

        dataArrayListModel.forEach{
            Log.d("ramuda", it.mainString + it.kanaString)
        }


        binding.resultRecyclerView.setHasFixedSize(true)
        binding.resultRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.resultRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL)) // リストアイテムの区切り線を追加
        // アダプターの宣言とタッチイベントのオーバーライド処理
        val adapter = object: RecyclerAdapter(dataArrayListModel) {
            override fun recyclerOnClick(view: View, position: Int) {
                this@ResultFragment.recyclerOnClick(view, position)
                Log.d("onClick", dataArrayListModel.get(position).mainString + dataArrayListModel.get(position).kanaString)
            }

        }
        binding.resultRecyclerView.adapter = adapter
        return binding.root
    }

    fun recyclerOnClick(view: View, position: Int){
        /*var wkData1 = data[position].Name
        var wkData2 = list[position].Memo
        Toast.makeText(applicationContext, "position $position was tapped $wkData1 $wkData2", Toast.LENGTH_SHORT).show()*/
        Toast.makeText(context, "position $position", Toast.LENGTH_SHORT).show()
    }

    // リスト要素のクラス
    class ListData(var address: String, var kana: String)
}