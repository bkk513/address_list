package com.example.bk_finalwork

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.example.bk_finalwork.activity.AddActivity_bk
import com.example.bk_finalwork.adapter.PeoAdapter_bk
import com.example.bk_finalwork.bean.PeoBean_bk
import com.example.bk_finalwork.dao.PeoDao_bk
import com.example.bk_finalwork.until.DBUntil_bk
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.bottomnavigation.BottomNavigationView;
class MainActivity_bk : AppCompatActivity() {
    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val dbUntil = DBUntil_bk(this)
        DBUntil_bk.db = dbUntil.writableDatabase

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_contacts
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_calls -> {
                    // 启动 CallsActivity
                    bottomNavigationView.selectedItemId = R.id.nav_contacts
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:") // 打开拨号键盘

                    startActivity(intent)

                    true
                }
                R.id.nav_contacts -> {
                    // 处理联系人点击
                    true
                }
                R.id.nav_shoucang -> {
                    // 处理收藏点击
                    val intent = Intent(this, FavoriteListActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

//        val result: List<PeoBean_bk> = PeoDao_bk.allPeo
//        val listView = findViewById<ListView>(R.id.book_list)
//
//        if (result.isEmpty()) {
//            listView.adapter = null
//        } else {
//            result.sortedWith(compareBy({ if (it.beginZ == "#") 1 else 0 }, { it.beginZ }))
//            val peoAdapter = PeoAdapter_bk(this@MainActivity_bk, result)
//            listView.adapter = peoAdapter
//        }
        val result: List<PeoBean_bk> = PeoDao_bk.allPeo
        val listView = findViewById<ListView>(R.id.book_list)

        if (result.isEmpty()) {
            listView.adapter = null
        } else {
            val sortedResult = result.sortedWith(compareBy({ if (it.beginZ == "#") 1 else 0 }, { it.beginZ }))
            val peoAdapter = PeoAdapter_bk(this@MainActivity_bk, sortedResult)
            listView.adapter = peoAdapter
        }




        val floatingActionButton = findViewById<FloatingActionButton>(R.id.add)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity_bk, AddActivity_bk::class.java)
            startActivity(intent)
        }

        val id = findViewById<EditText>(R.id.search_id)
//        id.setOnTouchListener { _, _ ->
//            listView.adapter = null
//            val title = id.text.toString()
//            val temp = if (title.isEmpty()) {
//                PeoDao_bk.allPeo
//            } else {
//                PeoDao_bk.getAllPeo(title)
//            }
//            temp.sortedWith(compareBy({ if (it.beginZ == "#") 1 else 0 }, { it.beginZ }))
//            val peoAdapter = PeoAdapter_bk(this@MainActivity_bk, temp)
//            listView.adapter = peoAdapter
//            false
//        }
        id.setOnTouchListener { _, _ ->
            listView.adapter = null
            val title = id.text.toString()
            val temp = if (title.isEmpty()) {
                PeoDao_bk.allPeo
            } else {
                PeoDao_bk.getAllPeo(title)
            }
            val sortedTemp = temp.sortedWith(compareBy({ if (it.beginZ == "#") 1 else 0 }, { it.beginZ }))
            val peoAdapter = PeoAdapter_bk(this@MainActivity_bk, sortedTemp)
            listView.adapter = peoAdapter
            false
        }



        id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                listView.adapter = null
                val title = id.text.toString()
                val temp = if (title.isEmpty()) {
                    PeoDao_bk.allPeo
                } else {
                    PeoDao_bk.getAllPeo(title)
                }
                temp.sortedWith(compareBy({ if (it.beginZ == "#") 1 else 0 }, { it.beginZ }))
                val peoAdapter = PeoAdapter_bk(this@MainActivity_bk, temp)
                listView.adapter = peoAdapter
            }
        })
    }
    override fun onResume() {
        super.onResume()

        // 确保返回时底部导航栏的选中项为“联系人”
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_contacts
    }
}