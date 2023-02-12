package com.example.taskpost.model

import com.example.taskpost.utils.Resource

interface PostApi {
    suspend fun getmeem():Resource<List<Meme>>
}