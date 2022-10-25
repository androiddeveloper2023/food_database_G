package com.example.food_with_database

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class FoodAdapter(private val data: ArrayList<Food>,private val foodevents:FoodEventes) :
    RecyclerView.Adapter<FoodAdapter.viewholder>() {
    inner class viewholder(itemview: View, private val context: Context) :
        RecyclerView.ViewHolder(itemview) {
        //تعریف اطلاعات درون یک ایتم ساخته شده
        val img = itemview.findViewById<ImageView>(R.id.imageView)
        val txt_foodname = itemview.findViewById<TextView>(R.id.txtsubject)
        val txt_foodcity = itemview.findViewById<TextView>(R.id.textView2)
        val txt_foodprice = itemview.findViewById<TextView>(R.id.textView3)
        val txt_fooddistance = itemview.findViewById<TextView>(R.id.textView4)
        val rating_star = itemview.findViewById<RatingBar>(R.id.ratingBar)
        var numofrating = itemview.findViewById<TextView>(R.id.textView5)
        @SuppressLint("SetTextI18n")
        fun onbind(position: Int) {
            //  ست کردن اطلاعات درون ایتم ساخته شده یعنی اطلاعات درون ایتم را برابر بوزیشن متناظرشان در دیتا قرار دادیم
            txt_foodname.text = data[position].Txt_foodname
            txt_foodcity.text = data[position].Txt_foodcity
            txt_foodprice.text = "$ " + data[position].Txt_foodprice + " vip"
            txt_fooddistance.text = data[position].Txt_fooddistance + " miles from you"
            rating_star.rating = data[position].Rating_star
            numofrating.text = "( " + data[position].Numofrating.toString() + " Ratings )"
            Glide.with(context)
                .load(data[position].Img)
                .into(img)
            itemView.setOnClickListener {
                foodevents.Onfoodclicked(data[adapterPosition],adapterPosition)
            }
            itemView.setOnLongClickListener {
                foodevents.Onfoodlongclicked(data[adapterPosition],adapterPosition)

                true
            }
        }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return viewholder(view, parent.context)
    }
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.onbind(position)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    fun addfood(newfood: Food) {
        data.add(0, newfood)
        notifyItemInserted(0)

    }
    fun removefood(oldfood:Food,oldposition:Int){



        data.remove(oldfood)
        notifyItemRemoved(oldposition)
    }
    fun updatefood(newupdatefood:Food,position:Int){
        data.set(position,newupdatefood)
        notifyItemChanged(position)

    }






    @SuppressLint("NotifyDataSetChanged")
    fun setdata(newlist:ArrayList<Food>){

        data.clear()
        data.addAll(newlist)
        notifyDataSetChanged()




    }










    interface FoodEventes{
        fun Onfoodclicked(food:Food,position:Int)
        fun Onfoodlongclicked(food:Food,pos:Int)
    }
}