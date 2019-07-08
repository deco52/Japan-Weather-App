package jp.ac.ibaraki.japanweatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * リストのアダプタークラス
 * クリックイベントを呼び出しフラグメントでオーバーライドするのでopenを記述している
 * データバインディングで実装したかったけど挫折(20190703)
 */
open class RecyclerAdapter(var dataListModel: ArrayList<ListDataModel>) : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_result_list, parent, false)
        return RecyclerViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return dataListModel.count()
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.mainTextView.text = dataListModel[position].mainString
        holder.kanaTextView.text = dataListModel[position].kanaString

        holder.itemView.setOnClickListener { recyclerOnClick(it, position) }
    }

    /**
     * リストのクリックイベント　呼び出しフラグメント(ResultFragment)でオーバーライドを行う？
     */
    open fun recyclerOnClick(view: View, position: Int) {
    }

}