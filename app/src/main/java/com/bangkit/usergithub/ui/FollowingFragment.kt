package com.bangkit.usergithub.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.usergithub.R
import com.bangkit.usergithub.adapter.UserAdapter
import com.bangkit.usergithub.databinding.FragmentFollowingBinding
import com.bangkit.usergithub.viewmodel.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {

    private var _bb: FragmentFollowingBinding? = null
    private val bb get() = _bb

    private lateinit var adapter: UserAdapter
    private lateinit var qq: String
    private lateinit var vm: FollowingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bb = FragmentFollowingBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        qq = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        bb!!.apply {
            rvFollowing.layoutManager = LinearLayoutManager(activity)
            rvFollowing.setHasFixedSize(true)
            rvFollowing.adapter = adapter
        }

        showLoading(true)

        vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
        vm.setFollowing(qq)
        vm.getFollowing().observe(viewLifecycleOwner){
            if (it != null){
                adapter.setData(it)
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            bb!!.progressBar.visibility = View.VISIBLE
        } else {
            bb!!.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bb = null
    }
}