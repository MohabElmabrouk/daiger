package com.example.taskpost.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemeResponse(
    @SerialName("count")
    val count: Int?,
    @SerialName("memes")
    val memes: List<Meme>
)