package com.naziksoft.guitartools.repository

import com.naziksoft.guitartools.api.RemoteApi
import javax.inject.Inject

class GuitarToolsRepository @Inject constructor(private val api: RemoteApi) {

    suspend fun search(request: String) = api.search(request)

    suspend fun getSong(id: Int) = api.getSong(id)
}