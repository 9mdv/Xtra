package com.github.andreyasadchy.xtra.ui.streams.followed

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.andreyasadchy.xtra.model.User
import com.github.andreyasadchy.xtra.model.helix.stream.Stream
import com.github.andreyasadchy.xtra.ui.common.BasePagedListAdapter
import com.github.andreyasadchy.xtra.ui.main.MainActivity
import com.github.andreyasadchy.xtra.ui.streams.BaseStreamsFragment
import com.github.andreyasadchy.xtra.ui.streams.StreamsCompactAdapter
import com.github.andreyasadchy.xtra.util.C
import com.github.andreyasadchy.xtra.util.prefs

class FollowedStreamsFragment : BaseStreamsFragment<FollowedStreamsViewModel>() {

    override val viewModel by viewModels<FollowedStreamsViewModel> { viewModelFactory }

    override val adapter: BasePagedListAdapter<Stream> by lazy {
        if (!compactStreams) {
            super.adapter
        } else {
            val activity = requireActivity() as MainActivity
            StreamsCompactAdapter(this, activity, activity)
        }
    }

    private var compactStreams = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compactStreams = requireContext().prefs().getBoolean(C.COMPACT_STREAMS, false)
    }

    override fun initialize() {
        super.initialize()
        viewModel.init(requireContext().prefs().getString(C.HELIX_CLIENT_ID, ""), User.get(requireContext()), !compactStreams)
    }
}
