package com.github.andreyasadchy.xtra.ui.follow.channels

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.github.andreyasadchy.xtra.R
import com.github.andreyasadchy.xtra.model.helix.follows.Follow
import com.github.andreyasadchy.xtra.ui.common.BasePagedListAdapter
import com.github.andreyasadchy.xtra.ui.common.OnChannelSelectedListener
import com.github.andreyasadchy.xtra.util.C
import com.github.andreyasadchy.xtra.util.TwitchApiHelper
import com.github.andreyasadchy.xtra.util.loadImage
import com.github.andreyasadchy.xtra.util.prefs
import kotlinx.android.synthetic.main.fragment_search_channels_list_item.view.*

class FollowedChannelsAdapter(
        private val fragment: Fragment,
        private val listener: OnChannelSelectedListener) : BasePagedListAdapter<Follow>(
        object : DiffUtil.ItemCallback<Follow>() {
            override fun areItemsTheSame(oldItem: Follow, newItem: Follow): Boolean =
                    oldItem.to_id == newItem.to_id

            override fun areContentsTheSame(oldItem: Follow, newItem: Follow): Boolean = true
        }) {

    override val layoutId: Int = R.layout.fragment_followed_channels_list_item

    override fun bind(item: Follow, view: View) {
        with(view) {
            setOnClickListener { listener.viewChannel(item.to_id, item.to_login, item.to_name, item.profileImageURL) }
            logo.loadImage(fragment, TwitchApiHelper.getTemplateUrl(item.profileImageURL, "profileimage", if (context.prefs().getBoolean(C.API_USEHELIX, true) && context.prefs().getString(C.USERNAME, "") != "") "4" else "3"))
            name.text = item.to_name
        }
    }
}