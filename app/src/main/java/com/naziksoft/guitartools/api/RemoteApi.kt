package com.naziksoft.guitartools.api

import com.naziksoft.guitartools.models.Song
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("/a/ra/songs.json")
    suspend fun search(@Query("pattern") request: String): Response<List<Song>>

    @GET("/a/wa/song")
    suspend fun getSong(@Query("id") id: Int): Response<ResponseBody>
}