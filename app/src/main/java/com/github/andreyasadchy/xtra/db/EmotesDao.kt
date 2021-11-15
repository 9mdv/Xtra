package com.github.andreyasadchy.xtra.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.github.andreyasadchy.xtra.model.helix.user.Emote


@Dao
interface EmotesDao {

    @Query("SELECT * FROM emotes ORDER BY code")
    fun getAll(): LiveData<List<Emote>>

    @Insert
    fun insertAll(emotes: List<Emote>)

    @Query("DELETE FROM emotes")
    fun deleteAll()

    @Transaction
    fun deleteAllAndInsert(emotes: List<Emote>) {
        deleteAll()
        insertAll(emotes)
    }
}
