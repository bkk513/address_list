package com.example.bk_finalwork.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bk_finalwork.MainActivity_bk
import com.example.bk_finalwork.R
import com.example.bk_finalwork.UpdateActivity_bk
import com.example.bk_finalwork.bean.PeoBean_bk
import com.example.bk_finalwork.dao.PeoDao_bk
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailsActivity_bk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val ID = intent.getStringExtra("id").toString()

        val call_phone = findViewById<ImageView>(R.id.de_da_phone)
        val num = findViewById<TextView>(R.id.de_phone)
        call_phone.setOnClickListener {
            val a = ContextCompat.checkSelfPermission(
                this@DetailsActivity_bk,
                Manifest.permission.CALL_PHONE
            )
            if (a == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(num.text.toString().trim { it <= ' ' })
            } else {
                ActivityCompat.requestPermissions(
                    this@DetailsActivity_bk,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            }
        }
        val de_da_message = findViewById<ImageView>(R.id.de_da_message)
        de_da_message.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            val uri = Uri.parse(
                "smsto:" + Uri.encode(
                    num.text.toString().trim { it <= ' ' })
            )
            intent.data = uri
            startActivity(intent)
        }
        val de_back = findViewById<ImageView>(R.id.de_back)
        de_back.setOnClickListener {
            val intent = Intent(this@DetailsActivity_bk, MainActivity_bk::class.java)
            startActivity(intent)
            //                finish();
        }

////////////////////////////////////////////////
//        val de_del = findViewById<Button>(R.id.de_del)
//        de_del.setOnClickListener {
//            PeoDao_bk.delPeo(ID)
//            Toast.makeText(this@DetailsActivity_bk, "删除成功", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this@DetailsActivity_bk, MainActivity_bk::class.java)
//            startActivity(intent)
//            //                finish();
//        }
//        val de_up = findViewById<Button>(R.id.de_up)
//        de_up.setOnClickListener {
//            val intent = Intent(this@DetailsActivity_bk, UpdateActivity_bk::class.java)
//            intent.putExtra("id", ID)
//            startActivity(intent)
//            //                finish();
//        }
/////////////////////////////////
        val currentPeo = PeoDao_bk.getOnePeo(ID)
        currentPeo?.let {
            it.isFavorite?.let { it1 -> updateBottomNavigationViewUI(it1) } // 根据 isFavorite 更新图标和标题
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_details)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.de_del -> {
                    PeoDao_bk.delPeo(ID)
                    Toast.makeText(this@DetailsActivity_bk, "删除成功", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@DetailsActivity_bk, MainActivity_bk::class.java)
                    startActivity(intent)
                    true
                }
                R.id.de_up -> {
                    // 处理联系人点击
                    val intent = Intent(this@DetailsActivity_bk, UpdateActivity_bk::class.java)
                    intent.putExtra("id", ID)
                    startActivity(intent)
                    true
                }
                R.id.de_shoucang -> {
                    // 用方法 getPeoById 来获取当前记录
                    val currentPeo = PeoDao_bk.getOnePeo(ID) // currentId 是当前记录的 ID

                    if (currentPeo != null) {
                        val newFavoriteStatus = if (currentPeo.isFavorite != "1") {
                            // 如果不是1，设置为1
                            "1"
                        } else {
                            // 如果是1，设置为0
                            "0"
                        }
                        // 更新数据库中的 isFavorite 状态
                        PeoDao_bk.updateFavoriteStatus(newFavoriteStatus, ID) // sId 是当前记录的 ID
                        updateBottomNavigationViewUI(newFavoriteStatus)

                    }
                    true
                }

                else -> false
            }
        }


        val peo: PeoBean_bk? = PeoDao_bk.getOnePeo(ID)
        val tx = findViewById<ImageView>(R.id.de_tx)
        peo?.let {
            if (it.sex == "男") {
                tx.setImageResource(R.drawable.peo_man)
            } else {
                tx.setImageResource(R.drawable.peo_woman)
            }

            val name = findViewById<TextView>(R.id.de_name)
            name.text = it.name

            val phone = findViewById<TextView>(R.id.de_phone)
            phone.text = it.num

            val sex = findViewById<TextView>(R.id.de_sex)
            sex.text = "性别：" + it.sex

            val remark = findViewById<TextView>(R.id.de_remark)
            remark.text = it.remark
        }
    }
    private fun makePhoneCall(num: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$num")
        startActivity(callIntent)
    }
    private fun updateBottomNavigationViewUI(isFavorite: String) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_details)
        val menuItem = bottomNavigationView.menu.findItem(R.id.de_shoucang)

        val iconDrawable = ContextCompat.getDrawable(this, R.drawable.shoucang)

        if (isFavorite == "1") {
            // 设置为“取消收藏”的文字和实心图标
            menuItem.title = "取消收藏"
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.noshoucang) // 实心图标
        } else {
            // 设置为“收藏”的文字和空心图标
            menuItem.title = "收藏"
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.shoucang) // 空心图标
        }
    }

}