package com.github.andreyasadchy.xtra.ui.search.games

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.github.andreyasadchy.xtra.R
import com.github.andreyasadchy.xtra.model.helix.game.Game
import com.github.andreyasadchy.xtra.ui.common.BaseListAdapter
import com.github.andreyasadchy.xtra.ui.games.GamesFragment
import com.github.andreyasadchy.xtra.util.TwitchApiHelper
import com.github.andreyasadchy.xtra.util.loadImage
import kotlinx.android.synthetic.main.fragment_search_games_list_item.view.*

class GameSearchAdapter(
        private val fragment: Fragment,
        private val listener: GamesFragment.OnGameSelectedListener) : BaseListAdapter<Game>(
        object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return true
            }
        }
) {

    override val layoutId: Int = R.layout.fragment_search_games_list_item

    override fun bind(item: Game, view: View) {
        with(view) {
            setOnClickListener { listener.openGame(item.id, item.name) }
            logo.loadImage(fragment, TwitchApiHelper.getTemplateUrl(item.box_art_url, "medium", true))
            name.text = item.name
        }
    }
}


