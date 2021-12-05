package com.github.andreyasadchy.xtra.repository

import com.github.andreyasadchy.xtra.model.chat.VideoMessagesResponse
import com.github.andreyasadchy.xtra.model.helix.channel.Channel
import com.github.andreyasadchy.xtra.model.helix.clip.Clip
import com.github.andreyasadchy.xtra.model.helix.follows.Follow
import com.github.andreyasadchy.xtra.model.helix.game.Game
import com.github.andreyasadchy.xtra.model.helix.stream.Stream
import com.github.andreyasadchy.xtra.model.helix.stream.StreamsResponse
import com.github.andreyasadchy.xtra.model.helix.user.User
import com.github.andreyasadchy.xtra.model.helix.video.BroadcastType
import com.github.andreyasadchy.xtra.model.helix.video.Sort
import com.github.andreyasadchy.xtra.model.helix.video.Video
import com.github.andreyasadchy.xtra.model.helix.video.VideosResponse
import kotlinx.coroutines.CoroutineScope

interface TwitchService {

    fun loadTopGames(clientId: String?, userToken: String?, coroutineScope: CoroutineScope): Listing<Game>
    suspend fun loadStream(clientId: String?, userToken: String?, channelId: String): StreamsResponse
    fun loadStreams(clientId: String?, userToken: String?, game: String?, languages: String?, coroutineScope: CoroutineScope): Listing<Stream>
    fun loadFollowedStreams(clientId: String?, userToken: String?, user_id: String, thumbnailsEnabled: Boolean, coroutineScope: CoroutineScope): Listing<Stream>
    fun loadClips(clientId: String?, userToken: String?, channelName: String?, gameName: String?, started_at: String?, ended_at: String?, coroutineScope: CoroutineScope): Listing<Clip>
    suspend fun loadVideo(clientId: String?, userToken: String?, videoId: String): VideosResponse
    fun loadVideos(clientId: String?, userToken: String?, game: String?, period: com.github.andreyasadchy.xtra.model.helix.video.Period, broadcastType: BroadcastType, language: String?, sort: Sort, coroutineScope: CoroutineScope): Listing<Video>
    fun loadChannelVideos(clientId: String?, userToken: String?, channelId: String, broadcastType: BroadcastType, sort: Sort, coroutineScope: CoroutineScope): Listing<Video>
    suspend fun loadUserById(clientId: String?, userToken: String?, id: String): User
    suspend fun loadUserByLogin(clientId: String?, userToken: String?, login: String): User
    fun loadGames(clientId: String?, userToken: String?, query: String, coroutineScope: CoroutineScope): Listing<Game>
    fun loadChannels(clientId: String?, userToken: String?, query: String, coroutineScope: CoroutineScope): Listing<Channel>
    suspend fun loadUserFollows(clientId: String?, userToken: String?, userId: String, channelId: String): Boolean
    fun loadFollowedChannels(clientId: String?, userToken: String?, userId: String, coroutineScope: CoroutineScope): Listing<Follow>
    suspend fun loadVideoChatLog(clientId: String?, videoId: String, offsetSeconds: Double): VideoMessagesResponse
    suspend fun loadVideoChatAfter(clientId: String?, videoId: String, cursor: String): VideoMessagesResponse

    fun loadTopGamesGQL(clientId: String?, coroutineScope: CoroutineScope): Listing<Game>
    fun loadTopStreamsGQL(clientId: String?, coroutineScope: CoroutineScope): Listing<Stream>
    fun loadGameStreamsGQL(clientId: String?, game: String?, coroutineScope: CoroutineScope): Listing<Stream>
    fun loadGameVideosGQL(clientId: String?, game: String?, type: String?, coroutineScope: CoroutineScope): Listing<Video>
    fun loadGameClipsGQL(clientId: String?, game: String?, sort: String?, coroutineScope: CoroutineScope): Listing<Clip>
    fun loadChannelVideosGQL(clientId: String?, game: String?, type: String?, sort: String?, coroutineScope: CoroutineScope): Listing<Video>
    fun loadChannelClipsGQL(clientId: String?, game: String?, sort: String?, coroutineScope: CoroutineScope): Listing<Clip>
    fun loadSearchChannelsGQL(clientId: String?, query: String, coroutineScope: CoroutineScope): Listing<Channel>
    fun loadSearchGamesGQL(clientId: String?, query: String, coroutineScope: CoroutineScope): Listing<Game>
}
