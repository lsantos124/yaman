package com.boardako.yaman.data.expenses

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.boardako.yaman.data.categories.Category

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["localId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL // or CASCADE depending on behavior
        )
    ]
)data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val amount: Double,
    val timestamp: Long = System.currentTimeMillis(),
    val categoryId: Int? = null
)