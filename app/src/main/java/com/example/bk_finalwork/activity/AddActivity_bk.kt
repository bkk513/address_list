package com.example.bk_finalwork.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.bk_finalwork.MainActivity_bk
import com.example.bk_finalwork.R
import com.example.bk_finalwork.dao.PeoDao_bk

class AddActivity_bk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val name = findViewById<TextView>(R.id.add_name)
        val phone = findViewById<TextView>(R.id.add_phone)
        val man = findViewById<RadioButton>(R.id.add_man)
        val woman = findViewById<RadioButton>(R.id.add_woman)
        val bz = findViewById<TextView>(R.id.add_bz)
        man.isChecked = true
        val add = findViewById<Button>(R.id.add_button)
        add.setOnClickListener {
            val nameT = name.text.toString().trim { it <= ' ' }
            val phoneT = phone.text.toString().trim { it <= ' ' }
            val bzT = bz.text.toString().trim { it <= ' ' }
            if (nameT.isEmpty()) {
                Toast.makeText(this@AddActivity_bk, "请输入姓名", Toast.LENGTH_SHORT).show()
            } else if (phoneT.isEmpty()) {
                Toast.makeText(this@AddActivity_bk, "请输入手机号", Toast.LENGTH_SHORT).show()
            } else if (bzT.isEmpty()) {
                Toast.makeText(this@AddActivity_bk, "请输入备注", Toast.LENGTH_SHORT).show()
            } else {
                var sex = "女"
                if (man.isChecked) {
                    sex = "男"
                }
                PeoDao_bk.savePeo(nameT, phoneT, sex, bzT)
                Toast.makeText(this@AddActivity_bk, "添加成功", Toast.LENGTH_SHORT).show()
            }
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar_add)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this@AddActivity_bk, MainActivity_bk::class.java)
            startActivity(intent)
        }
    }
}