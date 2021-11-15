package com.github.andreyasadchy.xtra.ui.search.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.github.andreyasadchy.xtra.model.helix.game.Game
import com.github.andreyasadchy.xtra.repository.LoadingState
import com.github.andreyasadchy.xtra.repository.TwitchService
import com.github.andreyasadchy.xtra.ui.common.BaseViewModel
import javax.inject.Inject

class GameSearchViewModel @Inject constructor(
        private val repository: TwitchService) : BaseViewModel() {

    private val query = MutableLiveData<String>()
    private var clientId = MutableLiveData<String>()
    private var token = MutableLiveData<String>()
    val list: LiveData<List<Game>> = Transformations.switchMap(query) {
        liveData {
            try {
                _loadingState.postValue(LoadingState.LOADING)
                val games = repository.loadGames(clientId.value, token.value, it)
                emit(games)
            } catch (e: Exception) {
                shouldRetry = true
            } finally {
                _loadingState.postValue(LoadingState.LOADED)
            }
        }
    }
    private var shouldRetry = false

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    fun setQuery(clientId: String?, token: String?, query: String) {
        if (this.clientId.value != clientId) {
            this.clientId.value = clientId
        }
        if (this.token.value != token) {
            this.token.value = token
        }
        if (this.query.value != query) {
            this.query.value = query
        }
    }

    fun retry() {
        if (shouldRetry) {
            shouldRetry = false
            query.value = query.value
        }
    }
}