package com.example.namozvaqtlari.data.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.ibroxim.model.Praying

@Dao
interface PrayingDao {

    @Insert
    suspend fun insertPreying(praying: Praying)
}