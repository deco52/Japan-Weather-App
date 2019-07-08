package jp.ac.ibaraki.japanweatherapp

import kotlinx.android.synthetic.main.item_result_list.view.*

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 *
 */
class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val mainTextView: TextView = view.main_TextView
    val kanaTextView: TextView = view.kana_TextView
}