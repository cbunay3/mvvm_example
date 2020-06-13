package com.tw.mvvm_example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tw.mvvm_example.model.daos.MovieDao
import com.tw.mvvm_example.model.models.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,exportSchema = false
)

abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
