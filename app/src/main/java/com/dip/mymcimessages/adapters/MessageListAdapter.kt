package com.dip.mymcimessages.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dip.mymcimessages.R
import com.dip.mymcimessages.databinding.MessageListItemBinding
import com.dip.mymcimessages.models.Message
import com.dip.mymcimessages.ui.IListListener

class MessageListAdapter(
    private val mList: MutableList<Message>,
    private val iListListener: IListListener
) :
    RecyclerView.Adapter<MessageListAdapter.MyViewHolder>() {

    private lateinit var context: Context
    private var selectionMode: Boolean = false
    private val deleteList: MutableList<Int> = mutableListOf()

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
            if (!item.unread) {
                binding.cardView.setCardBackgroundColor(context.resources.getColor(R.color.readed_color))
            }

            if (!selectionMode) {
                binding.cbItemCheck.visibility = View.GONE
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
            } else {
                binding.cbItemCheck.visibility = View.VISIBLE
                binding.row.setOnLongClickListener(null)
            }

            binding.cbItemCheck.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    deleteList.add(position)
                } else {
                    deleteList.remove(position)
                }
            }

            binding.row.setOnLongClickListener {
                selectionMode = true
                binding.cbItemCheck.isChecked = true
                notifyItemRangeChanged(0, mList.size)
                iListListener.showListDeleteDialog()
                true
            }

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun cancelSelectionMode() {
        selectionMode = false
        notifyItemRangeChanged(0, mList.size)
    }

    fun deleteItems() {
        selectionMode = false
        deleteList.forEach {
            mList.removeAt(it)
            notifyItemRemoved(it)
        }
        notifyItemRangeChanged(0, mList.size)
        iListListener.listChanged(mList.size)
    }

    class MyViewHolder(val binding: MessageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var expanded: Boolean = false
    }

}