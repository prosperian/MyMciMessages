package com.dip.mymcimessages.ui

import android.content.Context
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.dip.mymcimessages.R
import com.dip.mymcimessages.adapters.MessageListAdapter
import com.dip.mymcimessages.databinding.CustomListViewBinding

class CustomListView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var binding: CustomListViewBinding

    init {

        binding = CustomListViewBinding.bind(inflate(context, R.layout.custom_list_view, this))
        (binding.rvMessageList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        binding.rvMessageList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    fun showList(adapter: MessageListAdapter) {
        binding.ivEmptyList.visibility = View.GONE
        binding.tvEmptyList.visibility = View.GONE
        binding.rvMessageList.visibility = View.VISIBLE
        binding.rvMessageList.adapter = adapter
    }

    fun hideList() {
        binding.ivEmptyList.visibility = View.VISIBLE
        binding.tvEmptyList.visibility = View.VISIBLE
        binding.rvMessageList.visibility = View.GONE
    }

}