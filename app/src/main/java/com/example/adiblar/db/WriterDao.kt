package com.example.adiblar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adiblar.models.Writer

@Dao
interface WriterDao {

    @Insert
    fun insertWriter(writerEntity: WriterEntity)

    @Query("select * from writerentity")
    fun getAllWriter(): LiveData<List<WriterEntity>>

    @Delete
    fun deleteImage(writerEntity: WriterEntity)

    @Query("select * from writerentity where id=:id")
    fun getImageById(id: Int): Boolean

}