package com.github.andreyasadchy.xtra.ui.search.channels

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.github.andreyasadchy.xtra.R
import com.github.andreyasadchy.xtra.model.helix.channel.Channel
import com.github.andreyasadchy.xtra.ui.common.BasePagedListAdapter
import com.github.andreyasadchy.xtra.ui.common.OnChannelSelectedListener
import com.github.andreyasadchy.xtra.util.C
import com.github.andreyasadchy.xtra.util.TwitchApiHelper
import com.github.andreyasadchy.xtra.util.loadImage
import com.github.andreyasadchy.xtra.util.prefs
import kotlinx.android.synthetic.main.fragment_search_channels_list_item.view.*

class ChannelSearchAdapter(
        private val fragment: Fragment,
        private val listener: OnChannelSelectedListener) : BasePagedListAdapter<Channel>(
        object : DiffUtil.ItemCallback<Channel>() {
            override fun areItemsTheSame(oldItem: Channel, newItem: Channel): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Channel, newItem: Channel): Boolean = true
        }) {

    override val layoutId: Int = R.layout.fragment_search_channels_list_item

    override fun bind(item: Channel, view: View) {
        with(view) {
            setOnClickListener { listener.viewChannel(item.id, item.broadcaster_login, item.display_name, item.profileImageURL) }
            logo.loadImage(fragment, TwitchApiHelper.getTemplateUrl(item.profileImageURL, "profileimage", if (context.prefs().getBoolean(C.API_USEHELIX, true) && context.prefs().getString(C.USERNAME, "") != "") "4" else "3"), circle = true)
            name.text = item.display_name
        }
    }
}