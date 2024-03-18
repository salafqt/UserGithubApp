package com.bangkit.usergithub.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.usergithub.R
import com.bangkit.usergithub.adapter.UserAdapter
import com.bangkit.usergithub.databinding.FragmentFollowersBinding
import com.bangkit.usergithub.viewmodel.FollowersViewModel

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var _bb: FragmentFollowersBinding? = null
    private val bb get() = _bb

    private lateinit var adapter: UserAdapter
    private lateinit var qq: String
    private lateinit var vm: FollowersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bb = FragmentFollowersBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        qq = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        bb!!.apply {
            rvFollowers.layoutManager = LinearLayoutManager(activity)
            rvFollowers.setHasFixedSize(true)
            rvFollowers.adapter = adapter
        }

        showLoading(true)

        vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        vm.setFollowers(qq)
        vm.getFollowers().observe(viewLifecycleOwner){
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
