package com.github.andreyasadchy.xtra.ui.videos.top


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.github.andreyasadchy.xtra.R
import com.github.andreyasadchy.xtra.model.helix.video.BroadcastType
import com.github.andreyasadchy.xtra.model.helix.video.Period
import com.github.andreyasadchy.xtra.model.helix.video.Video
import com.github.andreyasadchy.xtra.repository.Listing
import com.github.andreyasadchy.xtra.repository.PlayerRepository
import com.github.andreyasadchy.xtra.repository.TwitchService
import com.github.andreyasadchy.xtra.ui.videos.BaseVideosViewModel
import javax.inject.Inject

class TopVideosViewModel @Inject constructor(
        context: Application,
        private val repository: TwitchService,
        playerRepository: PlayerRepository) : BaseVideosViewModel(playerRepository) {

    val sortOptions = listOf(R.string.today, R.string.this_week, R.string.this_month, R.string.all_time)
    private val _sortText = MutableLiveData<CharSequence>()
    val sortText: LiveData<CharSequence>
        get() = _sortText
    private val filter = MutableLiveData<Filter>()
    override val result: LiveData<Listing<Video>> = Transformations.map(filter) {
        repository.loadTopVideosGQL(it.clientId, viewModelScope)
    }
    var selectedIndex = 1
        private set

    init {
        _sortText.value = context.getString(sortOptions[selectedIndex])
        filter.value = Filter()
    }

    fun filter(clientId: String?, period: Period, index: Int, text: CharSequence) {
        filter.value?.copy(clientId = clientId, period = period).let {
            if (filter.value != it) {
                filter.value = it
                selectedIndex = index
                _sortText.value = text
            }
        }
    }

    private data class Filter(
            val clientId: String? = "",
            val period: Period = Period.WEEK,
            val broadcastType: BroadcastType = BroadcastType.ALL,
            val language: String? = null)
}
