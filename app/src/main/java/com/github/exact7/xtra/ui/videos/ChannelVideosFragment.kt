package com.github.exact7.xtra.ui.videos

import android.os.Bundle
import android.view.View
import com.github.exact7.xtra.R
import com.github.exact7.xtra.ui.fragment.RadioButtonDialogFragment
import com.github.exact7.xtra.util.FragmentUtils
import kotlinx.android.synthetic.main.fragment_videos.*

class ChannelVideosFragment : BaseVideosFragment(), RadioButtonDialogFragment.OnSortOptionChanged {

    private companion object {
        val sortOptions = listOf(R.string.upload_date, R.string.view_count)
        const val DEFAULT_INDEX = 0
    }

    private lateinit var channelId: Any

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        channelId = arguments?.get("channelId")!!
        sortBar.setOnClickListener { FragmentUtils.showRadioButtonDialogFragment(requireActivity(), childFragmentManager, sortOptions, viewModel.selectedIndex) }
    }

    override fun initializeViewModel() {
        viewModel.sort = Sort.TIME
        viewModel.sortText.postValue(getString(sortOptions[DEFAULT_INDEX]))
    }

//    override fun loadData(override: Boolean) {
//        viewModel.loadChannelVideos(channelId = channelId, reload = override)
//    }

    override fun onChange(index: Int, text: CharSequence, tag: Int?) { //TODO move this to viewmodel and update there
        viewModel.sort = if (tag == R.string.upload_date) Sort.TIME else Sort.VIEWS
        viewModel.sortText.value = text
        viewModel.selectedIndex = index
//        viewModel.loadedInitial.value = null
        adapter.submitList(null)
//        loadData(true)
    }
}
