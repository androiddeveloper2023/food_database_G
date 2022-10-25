package com.example.food_with_database


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_food")
data class Food(
    @PrimaryKey(autoGenerate = true)
       val id:Int?=null,
       val Txt_foodname: String,
    val Txt_foodprice: String,
    val Txt_fooddistance: String,
    val Txt_foodcity: String,
    val Img: String,
    val Numofrating: Int,
    val Rating_star: Float


)
