package com.dip.mymcimessages.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dip.mymcimessages.R
import com.dip.mymcimessages.adapters.MessageListAdapter
import com.dip.mymcimessages.api.Resource
import com.dip.mymcimessages.databinding.FragmentOnlineMessagesBinding
import com.dip.mymcimessages.models.Message
import com.dip.mymcimessages.ui.AnimationHandler
import com.dip.mymcimessages.ui.IListListener
import com.dip.mymcimessages.utils.Utils
import com.dip.mymcimessages.viewmodels.MessagesViewModel
import com.dip.mymcimessages.viewmodels.SharedViewModel

class OnlineMessagesFragment : Fragment(), IListListener {

    private final val TAG = OnlineMessagesFragment::class.simpleName

    private lateinit var binding: FragmentOnlineMessagesBinding
    private lateinit var viewModel: MessagesViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var adapter: MessageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_online_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnlineMessagesBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(MessagesViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        viewModel.messageList.observe(requireActivity()) {
            when (it) {
                is Resource.Success -> {
                    Log.d(TAG, "size: " + it.data?.messages?.size.toString())
                    val messages = viewModel.sortList(it.data?.messages)
                    if (messages.isNullOrEmpty()) {
                        binding.root.hideList()
                    } else {
                        viewModel.insertAllMessages(messages)
                    }
                }

                is Resource.Loading -> {
                    Log.d(TAG, "loading")
                    binding.root.hideList()
                }

                is Resource.Error -> {
                    Log.d(TAG, "error: " + it.message)
                    binding.root.hideList()
                }

                else -> {
//                this one is never gonna happen
                }
            }

        }

        viewModel.messagesInserted.observe(requireActivity()) {
            adapter = MessageListAdapter(it, this)
            binding.root.showList(adapter)
            sharedViewModel.updateCount(it.size)
        }

        if (!viewModel.messageList.hasActiveObservers())
            viewModel.getOnlineMessages()
        else
            viewModel.getAllMessages()
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

    override fun shareMessage(message: Message) {
        Utils.shareMessage(message, requireContext())
    }

    override fun listChanged(size: Int) {
        sharedViewModel.updateCount(size)
    }

    override fun removeMessage(message: Message) {
        viewModel.deleteMessage(message)
    }


}