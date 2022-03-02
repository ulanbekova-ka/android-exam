package com.kay.prog.exam.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}