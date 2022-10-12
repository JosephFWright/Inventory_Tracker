package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* Database arguments:
* Item as the class with entities.
* Version must be increased when db schema changes
* exportSchema T/F for version history backup
* */

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase() {
    //The database needs to know about the DAO
    abstract fun itemDao(): ItemDao

    //The companion object allows access to the methods for creating or getting the database using the class name as the qualifier.
    companion object{
        //The INSTANCE variable will keep a reference to the database, when one has been created.
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration().build()
                return instance
            }
        }

    }
}