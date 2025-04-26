package com.example.yaman.data.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val localId: Int = 0,
    val firebaseId: String? = null,
    val name: String,
)