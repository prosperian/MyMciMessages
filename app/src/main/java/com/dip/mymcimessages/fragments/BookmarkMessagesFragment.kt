package com.dip.mymcimessages.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dip.mymcimessages.R
import com.dip.mymcimessages.adapters.MessageListAdapter
import com.dip.mymcimessages.databinding.FragmentBookmarkMessagesBinding
import com.dip.mymcimessages.models.Message
import com.dip.mymcimessages.ui.AnimationHandler
import com.dip.mymcimessages.ui.IListListener
import com.dip.mymcimessages.ui.IListener
import com.dip.mymcimessages.utils.Utils
import com.dip.mymcimessages.viewmodels.MessagesViewModel

class BookmarkMessagesFragment : Fragment(), IListener {

    private lateinit var binding: FragmentBookmarkMessagesBinding
    private lateinit var viewModel: MessagesViewModel
    private lateinit var adapter: MessageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmark_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookmarkMessagesBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(MessagesViewModel::class.java)

        viewModel.bookmarkedList.observe(requireActivity()) {
            if (it.isNullOrEmpty()) {
                binding.root.hideList()
            } else {
                adapter = MessageListAdapter(it, this)
                binding.root.showList(adapter)
            }
        }
        viewModel.getBookmarkedMessages()
    }

    override fun shareMessage(message: Message) {
        Utils.shareMessage(message, requireContext())
    }

    override fun showListDeleteDialog() {
        val animationHandler = AnimationHandler()
        animationHandler.showDeleteDialog(binding.root, binding.animationView)
        binding.btnCancel.setOnClickListener {
            animationHandler.hideDeleteDialog(binding.root, binding.animationView)
            adapter.cancelSelectionMode()
        }
        binding.btnDelete.setOnClickListener {
            animationHandler.hideDeleteDialog(binding.root, binding.animationView)
            adapter.deleteItems()
        }
    }

    override fun updateMessage(message: Message) {
        viewModel.updateMessage(message)

    }

    override fun removeMessage(message: Message) {
        viewModel.deleteMessage(message)
    }
}