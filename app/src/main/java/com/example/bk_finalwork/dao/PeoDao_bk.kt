package com.example.bk_finalwork.dao

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.bk_finalwork.bean.PeoBean_bk
import com.example.bk_finalwork.until.DBUntil_bk
import com.hankcs.hanlp.HanLP

object PeoDao_bk {
    private val db: SQLiteDatabase? = DBUntil_bk.db

    val allPeo: List<PeoBean_bk>
        get() {
            val list = mutableListOf<PeoBean_bk>()
            val res = db?.rawQuery("SELECT * FROM d_peo", null)
            res?.use {
                while (it.moveToNext()) {
                    val peoBean = PeoBean_bk(
                        it.getString(0),
                        it.getString(1),
                        it.getString(2),
                        it.getString(3),
                        "",
                        it.getString(5)
                    )
                    val name = it.getString(1)

                    val first = name.substring(0, 1)
                    val re = first.matches(Regex("^[a-zA-Z]*"))
//                    peoBean.beginZ = when {
//                        re -> first.uppercase()
//                        first.matches(Regex("[\\u4e00-\\u9fa5]+")) -> {
//                            val py = HanLP.convertToPinyinString(first, "", false)
//                            py.substring(0, 1).uppercase()
//                        }
//                        else -> "#"
//                    }

                    if (re) {
                        peoBean.beginZ = first.uppercase()
                    } else {
                        val regEx = Regex("[\\u4e00-\\u9fa5]+")
                        if (regEx.containsMatchIn(first)) {
                            val py = HanLP.convertToPinyinString(first, "", false)
                            val xm = py.substring(0, 1)
                            peoBean.beginZ = xm.uppercase()
                        } else {
                            peoBean.beginZ = "#"
                        }
                    }


                    list.add(peoBean)
                }
            }
            return list
        }

    fun delPeo(id: String) {
        db?.execSQL("DELETE FROM d_peo WHERE s_id=?", arrayOf(id))
    }
    fun printAllPeo() {
        val res = db?.rawQuery("SELECT * FROM d_peo", null)
        res?.use {
            while (it.moveToNext()) {
                val id = it.getString(0) // 假设第一列是 s_id
                val name = it.getString(1) // 假设第二列是 s_name
                val phone = it.getString(2) // 假设第三列是 s_phone
                val sex = it.getString(3) // 假设第四列是 s_sex
                val remark = it.getString(4) // 假设第五列是 s_remark
                val isFavorite = it.getString(5) // 假设第六列是 isFavorite

                // 打印每一行的内容
                Log.d("PeoDao", "ID: $id, Name: $name, Phone: $phone, Sex: $sex, Remark: $remark, IsFavorite: $isFavorite")
            }
        } ?: Log.d("PeoDao", "No records found or database is null.")
    }


    fun getOnePeo(id: String): PeoBean_bk? {
        val res = db?.rawQuery("SELECT * FROM d_peo WHERE s_id=?", arrayOf(id))
        var peoBean: PeoBean_bk? = null
        res?.use {
            if (it.moveToNext()) {
                peoBean = PeoBean_bk(
                    it.getString(0),
                    it.getString(1),
                    it.getString(2),
                    it.getString(3),
                    it.getString(4),
                    it.getString(5)
                )
            }
        }
        return peoBean
    }

    fun updatePeo(vararg id: String?) {
        db?.execSQL("UPDATE d_peo SET s_name=?, s_phone=?, s_sex=?, s_remark=? WHERE s_id=?", id)
    }

    fun savePeo(vararg id: String?) {
        db?.execSQL("INSERT INTO d_peo (s_name, s_phone, s_sex, s_remark) VALUES (?, ?, ?, ?)", id)
    }
    fun updateFavoriteStatus(isFavorite: String, id: String) {
        db?.execSQL("UPDATE d_peo SET s_favorite=? WHERE s_id=?", arrayOf(isFavorite, id))
    }
    fun getAllFavorites(): List<PeoBean_bk> {
        val list = mutableListOf<PeoBean_bk>()
        val res = db?.rawQuery(
            "SELECT * FROM d_peo WHERE s_favorite = ?", arrayOf("1"),
            null
        )
        res?.use {
            while (it.moveToNext()) {
                val peoBean = PeoBean_bk(
                    it.getString(0),
                    it.getString(1),
                    it.getString(2),
                    it.getString(3),
                    "",
                    it.getString(5)
                )
                list.add(peoBean)
            }
        }
        return list
    }


    fun getAllPeo(id: String): List<PeoBean_bk> {
        val list = mutableListOf<PeoBean_bk>()
        val res = db?.rawQuery(
            "SELECT * FROM d_peo WHERE s_phone LIKE '%$id%' OR s_name LIKE '%$id%'",
            null
        )
        res?.use {
            while (it.moveToNext()) {
                val peoBean = PeoBean_bk(
                    it.getString(0),
                    it.getString(1),
                    it.getString(2),
                    it.getString(3),
                    "",
                    it.getString(5)
                )
                val name = it.getString(1)
                val first = name.substring(0, 1)
                val re = first.matches(Regex("^[a-zA-Z]*"))
                peoBean.beginZ = when {
                    re -> first.uppercase()
                    first.matches(Regex("[\\u4e00-\\u9fa5]+")) -> {
                        val py = HanLP.convertToPinyinString(first, "", false)
                        py.substring(0, 1).uppercase()
                    }
                    else -> "#"
                }
                list.add(peoBean)
            }
        }
        return list
    }
}