package com.dip.mymcimessages.adapters

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dip.mymcimessages.R
import com.dip.mymcimessages.databinding.CustomListViewBinding
import com.dip.mymcimessages.databinding.MessageListItemBinding
import com.dip.mymcimessages.models.Message
import kotlin.math.exp

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
        val item = mList[position]
        with(holder) {
            if (item.image != null) {
                Glide.with(binding.ivMessageImage).load(item.image)
                    .placeholder(R.drawable.ic_image_placeholder).into(binding.ivMessageImage)
                binding.ivMessageImage.visibility = View.VISIBLE
            } else {
                binding.ivMessageImage.visibility = View.GONE
            }
            binding.tvMessageTitle.text = item.title
            binding.tvMessageDescription.text = item.description


            if (expanded) {
                binding.tvMessageDescription.maxLines = Integer.MAX_VALUE
                expanded = true
                binding.ivExpandBtn.setImageResource(R.drawable.ic_arrow_up)
                if (item.image != null)
                    binding.motionLayout.transitionToEnd()
            } else {
                binding.tvMessageDescription.maxLines = 1
                binding.tvMessageDescription.ellipsize = TextUtils.TruncateAt.END
                binding.ivExpandBtn.setImageResource(R.drawable.ic_arrow_down)
                if (item.image != null)
                    binding.motionLayout.transitionToStart()
            }

            binding.ivExpandBtn.setOnClickListener {
                expanded = !expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class MyViewHolder(val binding: MessageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var expanded: Boolean = false

    }

}