package com.example.taskpost.model

import android.util.Log
import com.example.taskpost.utils.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class PostApiImp (private val client: HttpClient) : PostApi {

    override suspend fun getmeem(): Resource<List<Meme>> {
        return try {
            val response:MemeResponse = client.get {
                url(Routes.POSTS)
            }.body()
            Resource.Success(response.memes)
        } catch (e: RedirectResponseException) {
            Log.e("PostsApi", "3XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: ClientRequestException) {
            Log.e("PostsApi", "4XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: ServerResponseException) {
            Log.e("PostsApi", "5XX Error: ${e.response.status.description}")
            Resource.Error("${e.response}")
        } catch (e: Exception) {
            Log.e("PostsApi", "Error: ${e.message}")
            Resource.Error("${e.message}")
        }
    }
}