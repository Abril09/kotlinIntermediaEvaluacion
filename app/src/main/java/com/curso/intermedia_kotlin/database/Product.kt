package com.curso.intermedia_kotlin.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    val name: String,
    val unitPrice: Int,
    val quantity: Int
){

}