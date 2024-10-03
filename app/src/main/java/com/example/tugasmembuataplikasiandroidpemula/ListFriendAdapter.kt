package com.example.tugasmembuataplikasiandroidpemula

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmembuataplikasiandroidpemula.databinding.ItemRowFriendBinding

class ListFriendAdapter(private val listFriend: ArrayList<Friend>) :
    RecyclerView.Adapter<ListFriendAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowFriendBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFriend.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, data) = listFriend[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.binding.tvData.text = data

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFriend[holder.adapterPosition])
            val intentDetail = Intent(holder.itemView.context, FriendActivity::class.java)
            intentDetail.putExtra("key_friend", listFriend[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Friend)
    }

}