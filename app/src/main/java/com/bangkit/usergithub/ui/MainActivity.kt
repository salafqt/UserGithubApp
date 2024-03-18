package com.bangkit.usergithub.ui

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.usergithub.R
import com.bangkit.usergithub.adapter.UserAdapter
import com.bangkit.usergithub.data.User
import com.bangkit.usergithub.databinding.ActivityMainBinding
import com.bangkit.usergithub.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var bb: ActivityMainBinding
    private lateinit var vm: MainViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bb.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        bb.apply {
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
        }

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                intent.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                startActivity(intent)
            }
        })


        vm = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        vm.getUser().observe(this){
            if (it != null){
                adapter.setData(it)
                showLoading(false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = resources.getString(R.string.searchbar_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    vm.getSearchUser(query)
                    showLoading(true)

                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            bb.progressBar.visibility = View.VISIBLE
        } else {
            bb.progressBar.visibility = View.GONE
        }
    }
}