package com.example.bk_finalwork.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bk_finalwork.R
import com.example.bk_finalwork.activity.DetailsActivity_bk
import com.example.bk_finalwork.bean.PeoBean_bk

class PeoAdapter_bk(context: Context, private val items: List<PeoBean_bk>) :
    ArrayAdapter<PeoBean_bk>(context, R.layout.book_view, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_view, parent, false)
        }

        val imageView = convertView!!.findViewById<ImageView>(R.id.tp)
        val name = convertView.findViewById<TextView>(R.id.name)
        val zm = convertView.findViewById<TextView>(R.id.zm)
        val peo = items[position]

        name.text = peo.name

        if (peo.sex == "男") {
            imageView.setImageResource(R.drawable.man)
        } else if (peo.sex == "女") {
            imageView.setImageResource(R.drawable.woman)
        }

        if (position == 0 || peo.beginZ != items[position - 1].beginZ) {
            zm.text = peo.beginZ
        } else {
            zm.height = 1
        }

        convertView.setOnClickListener {
            val intent = Intent(context, DetailsActivity_bk::class.java)
            intent.putExtra("id", peo.id)
            context.startActivity(intent)
        }

        return convertView
    }
}
