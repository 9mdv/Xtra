package com.github.andreyasadchy.xtra.ui.follow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.github.andreyasadchy.xtra.R
import com.github.andreyasadchy.xtra.model.NotLoggedIn
import com.github.andreyasadchy.xtra.model.User
import com.github.andreyasadchy.xtra.ui.common.Scrollable
import com.github.andreyasadchy.xtra.ui.login.LoginActivity
import com.github.andreyasadchy.xtra.ui.main.MainActivity
import com.github.andreyasadchy.xtra.ui.settings.SettingsActivity
import com.github.andreyasadchy.xtra.util.visible
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_follow.*
import kotlinx.android.synthetic.main.fragment_follow.menu
import kotlinx.android.synthetic.main.fragment_follow.view.*

class FollowValidationFragment : Fragment(), Scrollable {

    private var mediaFragment: FollowMediaFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as MainActivity
        if (User.get(activity) !is NotLoggedIn) {
            mediaFragment = childFragmentManager.findFragmentById(R.id.container) as FollowMediaFragment?
                    ?: FollowMediaFragment().also { childFragmentManager.beginTransaction().replace(R.id.container, it).commit() }
        } else {
            notLoggedInLayout.visible()
            notLoggedInLayout.search.setOnClickListener { activity.openSearch() }
            notLoggedInLayout.login.setOnClickListener { activity.startActivityForResult(Intent(activity, LoginActivity::class.java), 1) }
            menu.setOnClickListener { it ->
                PopupMenu(activity, it).apply {
                    inflate(R.menu.top_menu)
                    setOnMenuItemClickListener {
                        when(it.itemId) {
                            R.id.settings -> { activity.startActivityFromFragment(this@FollowValidationFragment, Intent(activity, SettingsActivity::class.java), 3) }
                            R.id.login -> { activity.startActivityForResult(Intent(activity, LoginActivity::class.java), 1) }
                        }
                        true
                    }
                    show()
                }
            }
        }
    }

    override fun scrollToTop() {
        mediaFragment?.scrollToTop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            requireActivity().recreate()
        }
    }
}