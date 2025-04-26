package com.example.yaman.data.categories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

    @Query("UPDATE categories SET name = :newName WHERE localId = :categoryId")
    suspend fun updateCategoryName(categoryId: Int, newName: String)

    @Query("UPDATE categories SET firebaseId = :newId WHERE localId = :localId")
    suspend fun updateCategoryFirebaseID(localId: Int, newId: String)
}