package com.example.homework18.presenter.adapters

import android.graphics.Color
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework18.databinding.ItemForUsersRecyclerBinding
import com.example.homework18.presenter.model_presenter.UserPresenter

class UsersRecyclerViewAdapter(val listener:CallBackListener) : ListAdapter<UserPresenter,RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object{

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserPresenter>() {
            override fun areItemsTheSame(oldItem: UserPresenter, newItem: UserPresenter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserPresenter, newItem: UserPresenter): Boolean {
                d("tag123","$oldItem and $newItem")
                return oldItem == newItem
            }
        }

    }

    inner class UserViewHolder(private val binding: ItemForUsersRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) { d("tag123","bind -> $position")
            val user = currentList[position]
            bindUserAvatarImage(user.avatar)
            bindUserText(user.firstName,user.lastName,user.email)
            setRootOnClickListener(user.id)
            setRootOnLongClickListener(user)
            bindUserBackgroundColor(user.isSelected)
        }

        private fun bindUserBackgroundColor(isSelected:Boolean){
            if(isSelected) binding.root.setBackgroundColor(Color.GRAY)
            else binding.root.setBackgroundColor(Color.WHITE)
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

        private fun setRootOnLongClickListener(user:UserPresenter){
            binding.root.setOnLongClickListener{
                val userId = user.id
                if(user.isSelected){
                    listener.removeUserToDeletedList(userId)
                    bindUserBackgroundColor(false)
                }else{
                    listener.addUserToDeletedList(userId)
                    bindUserBackgroundColor(true)
                }
                true
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
        d("tag123","$position")
        if(holder is UserViewHolder)holder.bind(position)
    }
}

interface CallBackListener{
    fun openUsersPage(id:Int)
    fun addUserToDeletedList(id:Int)
    fun removeUserToDeletedList(id:Int)
}