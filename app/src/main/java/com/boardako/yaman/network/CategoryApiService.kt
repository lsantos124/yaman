package com.boardako.yaman.network

import com.boardako.yaman.data.categories.Category
import com.boardako.yaman.model.FirebasePostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoryApiService {

    @GET("categories.json")
    suspend fun getCategories(): Response<Map<String, Category>?>

    @POST("categories.json")
    suspend fun addCategory(@Body category: Category): Response<FirebasePostResponse>

    @PATCH("categories/{firebaseId}.json")
    suspend fun updateCategory(
        @Path("firebaseId") firebaseId: String,
        @Body updatedCategory: Category
    ): Response<Void>

    @DELETE("categories/{firebaseId}.json")
    suspend fun deleteCategory(
        @Path("firebaseId") firebaseId: String
    ): Response<Void>
}