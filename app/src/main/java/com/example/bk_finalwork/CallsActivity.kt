package com.example.bk_finalwork

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CallsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calls) // 这里是你的布局文件

        // 设置选中项为通话

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_calls
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_calls -> {
//                    // 启动 CallsActivity
//                    val intent = Intent(Intent.ACTION_DIAL)
//                    intent.data = Uri.parse("tel:") // 打开拨号键盘
//                    startActivity(intent)
//                    bottomNavigationView.selectedItemId = R.id.nav_contacts
//                    true
//                }
//                R.id.nav_contacts -> {
//                    // 处理联系人点击
//                    val intent = Intent(this, MainActivity_bk::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.nav_settings -> {
//                    // 处理收藏点击
//                    true
//                }
//                else -> false
//            }
//        }
        // 获取悬浮按钮
        val addButton: FloatingActionButton = findViewById(R.id.add)

        // 设置点击事件
//        addButton.setOnClickListener {
//            // 启动拨号器，打开拨号键盘
//            val intent = Intent(this, DialerActivity::class.java)
//            startActivity(intent)
//            bottomNavigationView.selectedItemId = R.id.nav_contacts
//        }

    }
}