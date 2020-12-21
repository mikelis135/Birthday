package com.birthday.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.birthday.R
import com.birthday.app.App
import com.birthday.database.entity.Birthday
import com.birthday.helper.CustomItemClickListener
import com.birthday.helper.Tools
import com.birthday.model.UserBirthday
import com.birthday.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

const val userBirthdayKey = "userBirthday"

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
                val birthday = item as UserBirthday

                val userBirthday = bundleOf(userBirthdayKey to birthday)

                val profileFragment = ProfileFragment()
                profileFragment.arguments = userBirthday

                Tools.gotoFragment(requireActivity(), profileFragment)
            }

            override fun onItemLongClick(v: View, position: Int) {
            }
        })

    }
}