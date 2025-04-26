package com.example.yaman.repository

import com.example.yaman.data.categories.Category
import com.example.yaman.network.CategoryApiService

class CategoryNetworkRepository(private val api: CategoryApiService) {

    suspend fun getCategories(): List<Category> {
        val response = api.getCategories()

        if (response.isSuccessful) {
            return response.body()?.values?.toList() ?: emptyList()
        } else {
            throw Exception("Failed to fetch categories: ${response.code()}")
        }
    }

    suspend fun addCategory(category: Category): String? {
        val response = api.addCategory(category)

        if (response.isSuccessful) {
            return response.body()?.name
        } else {
            throw Exception("Failed to add category: ${response.code()}")
        }
    }

    suspend fun updateCategory(firebaseId: String, updatedCategory: Category) {
        api.updateCategory(firebaseId, updatedCategory)
    }

    suspend fun deleteCategory(firebaseId: String) {
        api.deleteCategory(firebaseId)
    }
}