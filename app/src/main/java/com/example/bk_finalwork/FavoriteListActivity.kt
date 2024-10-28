package com.example.bk_finalwork

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.bk_finalwork.adapter.PeoAdapter_bk
import com.example.bk_finalwork.bean.PeoBean_bk
import com.example.bk_finalwork.dao.PeoDao_bk
import com.example.bk_finalwork.dao.PeoDao_bk.getAllFavorites
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var favoritePeopleList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritelist)

        // 假设在 FavoriteListActivity 中
        val favorites: List<PeoBean_bk> = getAllFavorites()

// 现在你可以遍历 favorites 列表，进行相应的操作
        for (peo in favorites) {
            // 处理每一个 PeoBean_bk 对象
            Log.d(
                "FavoritePeo",
                "ID: ${peo.id}, Name: ${peo.name}, Phone: ${peo.num}, Sex: ${peo.sex}, IsFavorite: ${peo.isFavorite}"
            )
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_shoucang
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
                    val intent = Intent(this, MainActivity_bk::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_shoucang -> {
                    // 处理收藏点击
                    val intent = Intent(this, FavoriteListActivity::class.java)
                    startActivity(intent)
                    bottomNavigationView.selectedItemId = R.id.nav_shoucang
                    true
                }
                else -> false
            }
        }
        val result: List<PeoBean_bk> = getAllFavorites()
        val listView = findViewById<ListView>(R.id.book_list)

        if (result.isEmpty()) {
            listView.adapter = null
        }else {
            val peoAdapter = PeoAdapter_bk(this@FavoriteListActivity, result)
            listView.adapter = peoAdapter
        }
    }
}
