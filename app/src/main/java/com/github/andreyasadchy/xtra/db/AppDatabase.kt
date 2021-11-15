package com.github.andreyasadchy.xtra.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.andreyasadchy.xtra.model.VideoPosition
import com.github.andreyasadchy.xtra.model.chat.RecentEmote
import com.github.andreyasadchy.xtra.model.helix.user.Emote
import com.github.andreyasadchy.xtra.model.offline.OfflineVideo
import com.github.andreyasadchy.xtra.model.offline.Request

@Database(entities = [OfflineVideo::class, Emote::class, Request::class, RecentEmote::class, VideoPosition::class], version = 8)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videos(): VideosDao
    abstract fun emotes(): EmotesDao
    abstract fun requests(): RequestsDao
    abstract fun recentEmotes(): RecentEmotesDao
    abstract fun videoPositons(): VideoPositionsDao
}