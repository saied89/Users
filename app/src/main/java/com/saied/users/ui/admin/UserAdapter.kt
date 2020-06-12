package com.saied.users.ui.admin

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saied.users.data.model.User
import com.saied.users.databinding.ItemUserBinding

class UserAdapter(private val onDeleteClick: (User) -> Unit) :
    ListAdapter<User, UserViewHolder>(userDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.fnameET.text = getItem(position).fullName
        getItem(position).picturePath?.let {
            with(BitmapFactory.decodeFile(it)) {
                holder.binding.imageView.setImageBitmap(this)
            }
        }
        holder.binding.deleteButton.setOnClickListener {
            onDeleteClick(getItem(position))
        }
    }
}

class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

private val userDiffCallback = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.email == newItem.email

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem
}
