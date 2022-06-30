package com.dip.mymcimessages.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dip.mymcimessages.R
import com.dip.mymcimessages.databinding.CustomListViewBinding
import com.dip.mymcimessages.databinding.MessageListItemBinding
import com.dip.mymcimessages.models.Message

class MessageListAdapter(private val mList: List<Message>) :
    RecyclerView.Adapter<MessageListAdapter.MyViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding =
            MessageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class MyViewHolder(binding: MessageListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}