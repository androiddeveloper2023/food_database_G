package com.example.food_with_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(version = 1,entities = [Food::class],exportSchema = false)
abstract class MyDatabase: RoomDatabase() {

    abstract val foodDao:FoodDao

    companion object{

        private var database:MyDatabase?=null

        fun getdatabase(context: Context):MyDatabase{

            if (database==null){

                database= Room.databaseBuilder(context.applicationContext,MyDatabase::class.java,"MyDatabase.db")
                    .allowMainThreadQueries()
                    .build()

            }

            return database!!

        }







    }




    }




