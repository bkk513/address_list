package com.example.bk_finalwork.until

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBUntil_bk(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, VERSION, null) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("drop table if exists d_peo")
        db.execSQL(
            "CREATE TABLE d_peo(s_id INTEGER primary key AUTOINCREMENT," +
                    "s_name varchar(20)," +
                    "s_phone varchar(20)," +
                    "s_sex varchar(20)," +
                    "s_remark varchar(20),"+
                    "s_favorite varchar(20))"
        )
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('李雅琪','13336973599','女','这个人很懒，什么都没有留下。','0')")
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('谢方悦','13336973598','女','这个人很懒，什么都没有留下。','0')")
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('李芃睿','13436973598','男','这个人很懒，什么都没有留下。','0')")
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('李宜群','15858812025','女','这个人很懒，什么都没有留下。','0')")
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('卞卡','19357397806','女','这个人很懒，什么都没有留下。','0')")
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('卞溢泉','18058875198','男','这个人很懒，什么都没有留下。','0')")
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark,s_favorite) VALUES('11','18058875199','男','这个人很懒，什么都没有留下。','0')")


//        db.execSQL("INSERT INTO d_admin VALUES('root','123456','卞卡','女','13336973590','20')");
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        if (i < 5) {
            // 新增收藏列
            sqLiteDatabase.execSQL("ALTER TABLE d_peo ADD COLUMN s_favorite varchar(20) DEFAULT '0'")
        }

    }

    companion object {
        private const val DB_NAME = "db.addBook.db"
        private const val VERSION = 5
        var db: SQLiteDatabase? = null
    }
}