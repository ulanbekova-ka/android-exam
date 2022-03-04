package com.kay.prog.exam.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    fun getAllCharacters(): Observable<Response>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Long?): Single<Item>
}