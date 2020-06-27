package com.tw.mvvm_example.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tw.mvvm_example.model.models.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insert(obj: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<MovieEntity>>

}