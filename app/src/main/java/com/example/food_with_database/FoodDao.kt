package com.example.food_with_database

import androidx.room.*

@Dao
interface FoodDao {

  @Insert
  fun insertfood(food:Food)
  @Insert
  fun insertAllfoods(data:List<Food>)

  @Update
  fun updatefood(food: Food)

  @Query("select*from table_food")
  fun getallfoods():List<Food>
  @Delete
  fun deletefood(food: Food)
  @Query("delete from table_food ")
  fun deleteAllfoods()

  @Query("select *from table_food where Txt_foodname like '%'||:searching||'%'")
  fun searachfoods(searching:String):List<Food>





}