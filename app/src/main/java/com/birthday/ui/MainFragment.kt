package com.birthday.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.birthday.R
import com.birthday.app.App
import com.birthday.helper.CustomItemClickListener
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

private const val userData = "userData"

class MainFragment : Fragment() {

    private var userData: String? = null

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var mainAdapter: MainAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userData = it.getString(userData)
        }

        mainAdapter = setUpRecyclerView()
    }

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootView = inflater.inflate(R.layout.fragment_main, container, false)

        rootView.birthdayRecycler.adapter = mainAdapter

        rootView.setOnClickListener {
            mainViewModel.processBirthday()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        mainViewModel.birthdayLiveData.observe(this, Observer {

            rootView.refresh.isRefreshing = false

            birthdayRecycler.animate().alpha(1.0f)
            errorTxt.animate().alpha(0.0f)

            mainAdapter.setPosts(it)

            rootView.birthdayRecycler.adapter = mainAdapter

        })

        mainViewModel.errorLiveData.observe(this, Observer {

            rootView.refresh.isRefreshing = false

            it?.let {
                errorTxt.animate().alpha(1.0f)
                birthdayRecycler.animate().alpha(0.0f)
            }

        })



        super.onActivityCreated(savedInstanceState)
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

    private fun setUpRecyclerView(): MainAdapter {

        return MainAdapter(object : CustomItemClickListener {

            override fun <T> onItemClick(item: T, position: Int) {
                val summary = item as String
                Toast.makeText(requireContext(), summary, Toast.LENGTH_LONG).show()
            }

            override fun onItemLongClick(v: View, position: Int) {
            }
        })

    }
}