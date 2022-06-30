package com.dip.mymcimessages.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dip.mymcimessages.R
import com.dip.mymcimessages.databinding.FragmentBookmarkMessagesBinding

class BookmarkMessagesFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkMessagesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmark_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookmarkMessagesBinding.bind(view)


    }

}