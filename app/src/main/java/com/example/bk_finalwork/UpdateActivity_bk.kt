package com.example.bk_finalwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.bk_finalwork.bean.PeoBean_bk
import com.example.bk_finalwork.dao.PeoDao_bk

class UpdateActivity_bk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val intent = intent
        val id = intent.getStringExtra("id").toString()
        val peo: PeoBean_bk? = PeoDao_bk.getOnePeo(id)

        val name = findViewById<TextView>(R.id.up_name)
        val phone = findViewById<TextView>(R.id.up_phone)
        val man = findViewById<RadioButton>(R.id.man)
        val woman = findViewById<RadioButton>(R.id.woman)
        val bz = findViewById<TextView>(R.id.up_bz)

        name.text = peo?.name
        phone.text = peo?.num

        if (peo?.sex == "男") {
            man.isChecked = true
        } else {
            woman.isChecked = true
        }

        bz.text = peo?.remark

        val up = findViewById<Button>(R.id.up_button)
        up.setOnClickListener {
            val nameT = name.text.toString().trim()
            val phoneT = phone.text.toString().trim()
            val bzT = bz.text.toString().trim()

            when {
                nameT.isEmpty() -> Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
                phoneT.isEmpty() -> Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show()
                bzT.isEmpty() -> Toast.makeText(this, "请输入备注", Toast.LENGTH_SHORT).show()
                else -> {
                    val sex = if (man.isChecked) "男" else "女"
                    PeoDao_bk.updatePeo(nameT, phoneT, sex, bzT, id)
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar_up)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity_bk::class.java)
            startActivity(intent)
        }
    }
}
