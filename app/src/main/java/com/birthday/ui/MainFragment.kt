package com.birthday.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.birthday.R
import com.birthday.app.App
import javax.inject.Inject

private const val userData = "userData"

class MainFragment : Fragment() {

    private var userData: String? = null

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userData = it.getString(userData)
        }
    }

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mainViewModel.processBirthday()

        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(userData: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(userData, userData)
                }
            }
    }
}