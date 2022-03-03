package com.naziksoft.guitartools.repository

import com.naziksoft.guitartools.models.Song
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("/a/ra/songs.json")
    suspend fun search(@Query("pattern") query : String): Response<List<Song>>

}