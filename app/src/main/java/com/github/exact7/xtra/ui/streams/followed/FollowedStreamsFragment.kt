package com.github.exact7.xtra.ui.streams.followed

import androidx.lifecycle.Observer
import com.github.exact7.xtra.ui.streams.BaseStreamsFragment

class FollowedStreamsFragment : BaseStreamsFragment() {

    override lateinit var viewModel: FollowedStreamsViewModel

    override fun initialize() {
        viewModel = createViewModel(FollowedStreamsViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        getMainViewModel().user.observe(viewLifecycleOwner, Observer {
            viewModel.setUser(it)
        })
    }
}
