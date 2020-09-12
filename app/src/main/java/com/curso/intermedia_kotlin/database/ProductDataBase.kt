package com.curso.intermedia_kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME = "evaluation"
@Database(entities = [Product::class], version = 1)

abstract class ProductDataBase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDataBase ? = null

        fun getDatabase(context: Context): ProductDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return  tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    ProductDataBase ::class.java,
                    DATA_BASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}