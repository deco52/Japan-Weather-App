package jp.ac.ibaraki.japanweatherapp

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.ac.ibaraki.japanweatherapp.databinding.FragmentBlankBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {

    var count = 0;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding: FragmentBlankBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        var myView: View = binding.root

        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            count = bundle.getInt("COUNT")
        }

        binding.textFragment.text = count.toString()
        binding.buttonDelete.setOnClickListener { _ ->
            getFragmentManager()?.beginTransaction()?.remove(this)?.commit()
        }

        return myView
    }
}
