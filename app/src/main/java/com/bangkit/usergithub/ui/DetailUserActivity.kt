package com.bangkit.usergithub.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bangkit.usergithub.adapter.SectionPagerAdapter
import com.bangkit.usergithub.data.response.ResponseDetail
import com.bangkit.usergithub.databinding.ActivityDetailUserBinding
import com.bangkit.usergithub.viewmodel.DetailUserViewModel
import com.bumptech.glide.Glide

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_AVATAR = "extra_avatar"
    }
    val _user = MutableLiveData<ResponseDetail>()
    val user: LiveData<ResponseDetail> = _user

    private lateinit var bb: ActivityDetailUserBinding
    private lateinit var vm: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(bb.root)

        val uu = intent.getStringExtra(EXTRA_USERNAME)
        val pp = intent.getStringExtra(EXTRA_AVATAR)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, uu)

        vm = ViewModelProvider(this)[DetailUserViewModel::class.java]

        if (uu != null) {
            vm.setDetailUser(uu)
        }

        vm.getDetailUser().observe(this) {
            if (it != null) {
                bb.apply {
                    name.text = it.name
                    username.text = it.login
                    followers.text = "${it.followers} Followers"
                    following.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(pp)
                        .into(avatar)
                }
                showLoading(false)
            }
        }


        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        val viewPager: ViewPager = bb.viewPager
        bb.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            bb.progressBar.visibility = View.VISIBLE
        } else {
            bb.progressBar.visibility = View.GONE
        }
    }
}