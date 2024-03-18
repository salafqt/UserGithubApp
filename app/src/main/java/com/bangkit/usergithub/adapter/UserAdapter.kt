package com.bangkit.usergithub.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import com.bangkit.usergithub.data.User
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.usergithub.databinding.UserItemBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    private val listUser = ArrayList<User>()

    fun setData(items: ArrayList<User>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(private val bb: UserItemBinding): RecyclerView.ViewHolder(bb.root) {
        fun bind(user: User) {
            bb.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            bb.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .into(avatar)

                username.text = user.login

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}