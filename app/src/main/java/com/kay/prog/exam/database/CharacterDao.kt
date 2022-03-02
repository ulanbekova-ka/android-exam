package com.kay.prog.exam.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Observable

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAll(): Observable<List<Character>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getById(id: Long?): Single<Character>

    @Insert
    fun insert(character: Character) : Completable
}