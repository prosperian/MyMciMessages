package com.dip.mymcimessages.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dip.mymcimessages.R
import com.dip.mymcimessages.api.Resource
import com.dip.mymcimessages.databinding.FragmentOnlineMessagesBinding
import com.dip.mymcimessages.viewmodels.MessagesViewModel

class OnlineMessagesFragment : Fragment() {

    private final val TAG = OnlineMessagesFragment::class.simpleName

    private lateinit var binding: FragmentOnlineMessagesBinding
    private lateinit var viewModel: MessagesViewModel

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

        viewModel.messageList.observe(viewLifecycleOwner) {
            when (it) {

                is Resource.Success -> {
                    Log.d(TAG, "size: " + it.data?.messages?.size.toString())
                }

                is Resource.Loading -> {
                    Log.d(TAG, "loading")
                }

                is Resource.Error -> {
                    Log.d(TAG, "error: " + it.message)
                }

                else -> {
//                this one is never gonna happen
                }
            }

        }

        viewModel.getMessages()

    }


}