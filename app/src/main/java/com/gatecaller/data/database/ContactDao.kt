package com.gatecaller.data.database

import androidx.room.*
import com.gatecaller.data.entity.ApiContactDatabase

@Dao
interface ContactDao {
    @Insert
    suspend fun insertAll(list: List<ApiContactDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apiContactDatabase: ApiContactDatabase)

    @Query("DELETE FROM contacts")
    suspend fun deleteAll()

    @Query("SELECT * FROM contacts")
    suspend fun getAll(): List<ApiContactDatabase>

    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun size(): Int
}