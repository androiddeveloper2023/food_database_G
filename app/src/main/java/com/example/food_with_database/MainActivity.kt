package com.example.food_with_database

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener

import androidx.recyclerview.widget.LinearLayoutManager


import com.example.food_with_database.databinding.ActivityMainBinding
import com.example.food_with_database.databinding.ItemDialogAddBinding
import com.example.food_with_database.databinding.ItemPakkardanFoodBinding
import com.example.food_with_database.databinding.ItemUpdatedialogBinding


class MainActivity : AppCompatActivity(), FoodAdapter.FoodEventes {
    lateinit var binding: ActivityMainBinding
    lateinit var myadapter: FoodAdapter
    lateinit var foodDao: FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        foodDao = MyDatabase.getdatabase(this).foodDao

        val sharedprefrences = getSharedPreferences("bestfood", Context.MODE_PRIVATE)
        if (sharedprefrences.getBoolean("firstRun", true)) {
            firstRun()
            sharedprefrences.edit().putBoolean("firstRun", false).apply()
        }


        showAllData()
        binding.btnRemoveall.setOnClickListener {

            removeAllData()


        }



        binding.btnadd.setOnClickListener {

            addNewFood()


        }



        binding.edtsearch.addTextChangedListener { EditTextInput ->

            searchOnDatabase(EditTextInput!!.toString())





        }
    }

    private fun searchOnDatabase(EditTextInput: String?) {
        if (EditTextInput!!.length > 0) {

            val searchedData = foodDao.searachfoods(EditTextInput.toString())
            myadapter.setdata(ArrayList(searchedData))
        } else {

            val data = foodDao.getallfoods()
            myadapter.setdata(ArrayList(data))

        }
    }


    private fun addNewFood() {


        val maindialog = AlertDialog.Builder(this).create()
        val dialogbinding = ItemDialogAddBinding.inflate(layoutInflater)
        maindialog.setView(dialogbinding.root)
        maindialog.setCancelable(true)
        maindialog.show()


        dialogbinding.dialogBtndone.setOnClickListener {


            //اگر همه ادیت ها بر بودند انوقت کلید دان عمل کند
            if (dialogbinding.dialogEdtfoodname.length() > 0
                &&
                dialogbinding.dialogEdtfoodcity.length() > 0
                &&
                dialogbinding.dialogEdtfooddistance.length() > 0
                &&
                dialogbinding.dialogEdtfoodprice.length() > 0
                && dialogbinding.dialogEdtfoodUrl.length() > 0
            ) {

                val txtnamefood = dialogbinding.dialogEdtfoodname.text.toString()
                val txtpricefood = dialogbinding.dialogEdtfoodprice.text.toString()
                val txtcityfood = dialogbinding.dialogEdtfoodcity.text.toString()
                val txtdiastancefood = dialogbinding.dialogEdtfooddistance.text.toString()
                val textratingstar: Float = (1..5).random().toFloat()
                val numofrating: Int = (1..2000).random()

                val url_pic = dialogbinding.dialogEdtfoodUrl.text.toString()

                //val img = (0..11).random()
                // val url_pic = foodlist[img].Img
                //غذای جدید را بسازیم

                val newfood = Food(
                    Txt_foodname = txtnamefood,
                    Txt_foodprice = txtpricefood,
                    Txt_fooddistance = txtdiastancefood,
                    Txt_foodcity = txtcityfood,
                    Img = url_pic,
                    Numofrating = numofrating,
                    Rating_star = textratingstar
                )

                //باید کلاس newfood را به foodadapter اضافه کنیم


                maindialog.dismiss()
                binding.recyclerMain.scrollToPosition(0)
                myadapter.addfood(newfood)
                foodDao.insertfood(newfood)
                //باید کلاس newfood را به foodadapter اضافه کنیم
            } else {
                Toast.makeText(this, "همه فیلدها را بر کنید", Toast.LENGTH_SHORT).show()
            }


        }
        dialogbinding.dialogBtncancel.setOnClickListener {
            maindialog.dismiss()


        }

    }

    private fun removeAllData() {
        foodDao.deleteAllfoods()
        showAllData()
    }


    private fun firstRun() {

        val foodList = arrayListOf(
            Food(
                Txt_foodname = "Hamburger",
                Txt_foodprice = "15",
                Txt_fooddistance = "3",
                Txt_foodcity = "Isfahan, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                Numofrating = 20,
                Rating_star = 4.5f
            ),
            Food(
                Txt_foodname = "Grilled fish",
                Txt_foodprice = "20",
                Txt_fooddistance = "2.1",
                Txt_foodcity = "Tehran, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                Numofrating = 10,
                Rating_star = 4f
            ),
            Food(
                Txt_foodname = "Lasania",
                Txt_foodprice = "40",
                Txt_fooddistance = "1.4",
                Txt_foodcity = "Isfahan, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                Numofrating = 30,
                Rating_star = 2f
            ),
            Food(
                Txt_foodname = "pizza",
                Txt_foodprice = "10",
                Txt_fooddistance = "2.5",
                Txt_foodcity = "Zahedan, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                Numofrating = 80,
                Rating_star = 1.5f
            ),
            Food(
                Txt_foodname = "Sushi",
                Txt_foodprice = "20",
                Txt_fooddistance = "3.2",
                Txt_foodcity = "Mashhad, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                Numofrating = 200,
                Rating_star = 3f
            ),
            Food(
                Txt_foodname = "Roasted Fish",
                Txt_foodprice = "40",
                Txt_fooddistance = "3.7",
                Txt_foodcity = "Jolfa, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                Numofrating = 50,
                Rating_star = 3.5f
            ),
            Food(
                Txt_foodname = "Fried chicken",
                Txt_foodprice = "70",
                Txt_fooddistance = "3.5",
                Txt_foodcity = "NewYork, USA",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                Numofrating = 70,
                Rating_star = 2.5f
            ),
            Food(
                Txt_foodname = "Vegetable salad",
                Txt_foodprice = "12",
                Txt_fooddistance = "3.6",
                Txt_foodcity = "Berlin, Germany",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                Numofrating = 40,
                Rating_star = 4.5f
            ),
            Food(
                Txt_foodname = "Grilled chicken",
                Txt_foodprice = "10",
                Txt_fooddistance = "3.7",
                Txt_foodcity = "Beijing, China",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                Numofrating = 15,
                Rating_star = 5f
            ),
            Food(
                Txt_foodname = "Baryooni",
                Txt_foodprice = "16",
                Txt_fooddistance = "10",
                Txt_foodcity = "Ilam, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                Numofrating = 28,
                Rating_star = 4.5f
            ),
            Food(
                Txt_foodname = "Ghorme Sabzi",
                Txt_foodprice = "11.5",
                Txt_fooddistance = "7.5",
                Txt_foodcity = "Karaj, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                Numofrating = 27,
                Rating_star = 5f
            ),
            Food(
                Txt_foodname = "Rice",
                Txt_foodprice = "12.5",
                Txt_fooddistance = "2.4",
                Txt_foodcity = "Shiraz, Iran",
                Img = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                Numofrating = 35,
                Rating_star = 2.5f
            ),
        )

        foodDao.insertAllfoods(foodList)


    }

    private fun showAllData() {

        val fooddata = foodDao.getallfoods()

        myadapter = FoodAdapter(ArrayList(fooddata), this)

        binding.recyclerMain.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerMain.adapter = myadapter

        Log.v("test", "is ready")

    }

    override fun Onfoodclicked(food: Food, position: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val updatedialog = ItemUpdatedialogBinding.inflate(layoutInflater)
        dialog.setView(updatedialog.root)
        dialog.setCancelable(true)
        dialog.show()
        updatedialog.updatedialogEdtfoodname.setText(food.Txt_foodname)
        updatedialog.updatedialogEdtfoodprice.setText(food.Txt_foodprice)
        updatedialog.updatedialogEdtfoodcity.setText(food.Txt_foodcity)
        updatedialog.updatedialogEdtfooddistance.setText(food.Txt_fooddistance)
        if (updatedialog.updatedialogEdtfoodname.length() > 0
            &&
            updatedialog.updatedialogEdtfoodprice.length() > 0
            &&
            updatedialog.updatedialogEdtfoodcity.length() > 0
            &&
            updatedialog.updatedialogEdtfooddistance.length() > 0
        ) {
            updatedialog.updatedialogBtncancel.setOnClickListener {
                dialog.dismiss()
            }
            updatedialog.updatedialogBtndone.setOnClickListener {
                val txtfoodname = updatedialog.updatedialogEdtfoodname.text.toString()
                val txtfoodprice = updatedialog.updatedialogEdtfoodprice.text.toString()
                val txtfoodcity = updatedialog.updatedialogEdtfoodcity.text.toString()
                val txtfooddistance = updatedialog.updatedialogEdtfooddistance.text.toString()


                val newupdatefood = Food(
                    id = food.id,
                    Txt_foodname = txtfoodname,
                    Txt_foodprice = txtfoodprice,
                    Txt_fooddistance = txtfooddistance,
                    Txt_foodcity = txtfoodcity,
                    Img = food.Img,
                    Numofrating = food.Numofrating,
                    Rating_star = food.Rating_star
                )

                myadapter.updatefood(newupdatefood, position)
                foodDao.updatefood(newupdatefood)
                dialog.dismiss()
            }


        }


    }

    override fun Onfoodlongclicked(food: Food, pos: Int) {


        val dialog = AlertDialog.Builder(this).create()

        val dialogbinding = ItemPakkardanFoodBinding.inflate(layoutInflater)

        dialog.setView(dialogbinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogbinding.btncancel.setOnClickListener {

            dialog.dismiss()
        }
        dialogbinding.btnpakkardan.setOnClickListener {

            dialog.dismiss()
            myadapter.removefood(food, pos)
            foodDao.deletefood(food)
        }

    }


}















