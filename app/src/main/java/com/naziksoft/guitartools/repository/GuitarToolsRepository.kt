package com.naziksoft.guitartools.repository

import com.naziksoft.guitartools.api.RemoteApi

class GuitarToolsRepository(private val api: RemoteApi) {

    suspend fun search(request: String) = runCatching { api.search(request) }

    suspend fun getSong(id: Int) = runCatching { api.getSong(id) }
}