package com.example.addressbooksystem.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.addressbooksystem.bean.PeoBean;
import com.example.addressbooksystem.until.DBUntil;
import com.hankcs.hanlp.HanLP;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeoDao {

    public static SQLiteDatabase db= DBUntil.db;

    public static List<PeoBean> getAllPeo(){
        List<PeoBean> list=new ArrayList<>();

        Cursor res = db.rawQuery("select * from d_peo",null);
        while(res.moveToNext()){
            PeoBean peoBean=new PeoBean(res.getString(0),res.getString(1),res.getString(2),res.getString(3),"");
            String name=res.getString(1);//是中文还是英文
            String first=name.substring(0,1);//得到了第一个字符
            boolean re=first.matches("^[a-zA-Z]*");
            if(re==true){
                peoBean.setBeginZ(first.toUpperCase());//是字母则转换成大写存入
            }else {
                //全部都是按钮，其他符号
                String regEx = "[\\u4e00-\\u9fa5]+";
                Pattern p=Pattern.compile(regEx);
                Matcher m=p.matcher(first);
                if(m.find()){
                    //将中文转换成拼音
                    String py= HanLP.convertToPinyinString(first," ",false);//李 li
                    String xm=py.substring(0,1);//l
                    peoBean.setBeginZ(xm.toUpperCase());//是字母则转换成大写存入
                }else{


                    peoBean.setBeginZ("#");//是字母则转换成大写存入

                }




            }


            list.add(peoBean);

        }
        return  list;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    public static void delPeo(String id){
        db.execSQL("DELETE FROM  d_peo where s_id=?",new String[]{id});
    }

    /**
     * 查询一个人的联系方式
     * @param id
     * @return
     */
    public static PeoBean getOnePeo(String id){

        Cursor res = db.rawQuery("select * from d_peo where s_id=?",new String[]{id});
        PeoBean peoBean=null;
        while(res.moveToNext()){
          peoBean=new PeoBean(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4));

        }
        return  peoBean;

    }


    /**
     * 更改
     * @param id
     * @return
     */
    public static void updatePeo(String ...id){
        db.execSQL("update  d_peo set s_name=?,s_phone=?,s_sex=?,s_remark=? where s_id=?",id);


    }

    public static void savePeo(String ...id){


        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES(?,?,?,?)",id);
    }




    public static List<PeoBean> getAllPeo(String id){
        List<PeoBean> list=new ArrayList<>();
        String query = "SELECT * FROM d_peo WHERE s_phone LIKE '%"+ id+"%'  OR s_name LIKE '%"+id+"%'";
        Cursor res = db.rawQuery(query,null);
        while(res.moveToNext()){
            PeoBean peoBean=new PeoBean(res.getString(0),res.getString(1),res.getString(2),res.getString(3),"");
            String name=res.getString(1);//是中文还是英文
            String first=name.substring(0,1);//得到了第一个字符
            boolean re=first.matches("^[a-zA-Z]*");
            if(re==true){
                peoBean.setBeginZ(first.toUpperCase());//是字母则转换成大写存入
            }else {
                //全部都是按钮，其他符号
                String regEx = "[\\u4e00-\\u9fa5]+";
                Pattern p=Pattern.compile(regEx);
                Matcher m=p.matcher(first);
                if(m.find()){
                    //将中文转换成拼音
                    String py= HanLP.convertToPinyinString(first," ",false);//李 li
                    String xm=py.substring(0,1);//l
                    peoBean.setBeginZ(xm.toUpperCase());//是字母则转换成大写存入
                }else{


                    peoBean.setBeginZ("#");//是字母则转换成大写存入

                }




            }


            list.add(peoBean);

        }
        return  list;

    }

}
