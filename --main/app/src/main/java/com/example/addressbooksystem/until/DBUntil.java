package com.example.addressbooksystem.until;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBUntil extends SQLiteOpenHelper {

    private static final   String DB_NAME="db.addBook.db";

    private static final int VERSION=5;

    public static SQLiteDatabase db=null;//用他来操作数据库


    public DBUntil(Context context) {
        super(context, DB_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
            //创建一个表格
        db.execSQL("drop table if exists d_peo");//如果这个表存在则删除
        db.execSQL("CREATE TABLE d_peo (s_id INTEGER primary key AUTOINCREMENT," +
                "s_name varchar(20)," +
                "s_phone varchar(20)," +
                "s_sex varchar(20)," +
                "s_remark varchar(20))");


        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES('隔壁王叔','123456789','男','隔壁开锁的')");
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES('隔壁李姨','123456789','女','楼下早餐店铺')");
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES('C隔壁李姨','123456789','女','楼下早餐店铺')");
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES('A隔壁李姨','123456789','女','楼下早餐店铺')");
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES('C隔壁李姨','123456789','女','楼下早餐店铺')");
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES('11隔壁李姨','123456789','女','楼下早餐店铺')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
