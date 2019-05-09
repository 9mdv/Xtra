package com.github.exact7.xtra.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.exact7.xtra.model.offline.Request

@Dao
interface RequestsDao {

    @Query("SELECT * FROM requests")
    fun getAll(): List<Request>

    @Insert
    fun insert(request: Request)

    @Delete
    fun delete(request: Request)
}
