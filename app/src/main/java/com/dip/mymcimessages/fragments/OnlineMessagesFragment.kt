package com.dip.mymcimessages.fragments

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
import com.dip.mymcimessages.ui.AnimationHandler
import com.dip.mymcimessages.ui.IListListener
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
                    val messages = it.data?.messages
                    if (messages.isNullOrEmpty()) {
                        binding.root.hideList()
                    } else {
                        adapter = MessageListAdapter(messages, this)
                        binding.root.showList(adapter)
                        sharedViewModel.updateCount(messages.size)
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
        if (!viewModel.messageList.hasActiveObservers())
            viewModel.getMessages()

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

    override fun listChanged(size: Int) {
        sharedViewModel.updateCount(size)
    }


}