package com.aspirant.clicker.boost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aspirant.clicker.R

private const val ARG_PARAM_TITLE = "title"

class BoostView : Fragment() {
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_PARAM_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boost_view, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            BoostView().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, title)
                }
            }
    }
}