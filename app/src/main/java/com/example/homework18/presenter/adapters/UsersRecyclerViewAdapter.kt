package com.example.homework18.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework18.databinding.ItemForUsersRecyclerBinding
import com.example.homework18.presenter.common.User

class UsersRecyclerViewAdapter(val listener:CallBackListener) : ListAdapter<User,RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object{

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }

    }

    inner class UserViewHolder(private val binding: ItemForUsersRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val user = currentList[position]
            bindUserAvatarImage(user.avatar)
            bindUserText(user.firstName,user.lastName,user.email)
            setRootOnClickListener(user.id)
        }

        private fun bindUserAvatarImage(imageUrl:String){
            binding.apply {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .into(avatarImage)
            }
        }

        private fun bindUserText(firstName:String, lastName:String, email:String){
            binding.apply {
                tvFirstName.text = firstName
                tvLastName.text = lastName
                tvEmail.text = email
            }
        }

        private fun setRootOnClickListener(id:Int){
            binding.root.setOnClickListener{
                listener.openUsersPage(id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            ItemForUsersRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is UserViewHolder)holder.bind(position)
    }
}

interface CallBackListener{
    fun openUsersPage(id:Int)
}