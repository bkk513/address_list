package com.example.addressbooksystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.addressbooksystem.activity.AddActivity;
import com.example.addressbooksystem.activity.DetailsActivity;
import com.example.addressbooksystem.activity.UpdateActivity;
import com.example.addressbooksystem.adapter.PeoAdapter;
import com.example.addressbooksystem.bean.PeoBean;
import com.example.addressbooksystem.dao.PeoDao;
import com.example.addressbooksystem.until.DBUntil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //获取这个ID
      Toolbar toolbar=  this.findViewById(R.id.toolbar);
      this.setSupportActionBar(toolbar);


        //调用数据库
        DBUntil dbUntil=new DBUntil(MainActivity.this);
        dbUntil.db= dbUntil.getWritableDatabase();


//        Intent intent=new Intent(MainActivity.this, UpdateActivity.class);
//        intent.putExtra("id","2");
//        startActivity(intent);



        //加载这里列表
        List<PeoBean> result = PeoDao.getAllPeo();
        ListView listView=findViewById(R.id.book_list);
        if(result.size()==0) {
            listView.setAdapter(null);//让界面啥都不显示

        }else{
            //需要对list进行排序
            result.sort(new Comparator<PeoBean>() {
                @Override
                public int compare(PeoBean peoBean, PeoBean t1) {

                    if(peoBean.getBeginZ().equals("#")||t1.getBeginZ().equals("#")){
                        return  1;
                    }else{
                        return  peoBean.getBeginZ().compareTo(t1.getBeginZ());
                    }


                }
            });


            PeoAdapter peoAdapter=new PeoAdapter(MainActivity.this, result);
            listView.setAdapter(peoAdapter);
        }



        FloatingActionButton floatingActionButton=findViewById(R.id.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开添加界面

                Intent intent=new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);


            }
        });



        //只有这个搜索框内容有东西他就执行一个搜索的共
        EditText id=findViewById(R.id.search_id);
        id.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                listView.setAdapter(null);//让界面啥都不显示
                //需要对list进行排序
                String title=id.getText().toString();
                List<PeoBean> temp=null;
                if(title.isEmpty()){
                    temp= PeoDao.getAllPeo();
                }else{
                    temp= PeoDao.getAllPeo(title);
                }

                temp.sort(new Comparator<PeoBean>() {
                    @Override
                    public int compare(PeoBean peoBean, PeoBean t1) {

                        if(peoBean.getBeginZ().equals("#")||t1.getBeginZ().equals("#")){
                            return  1;
                        }else{
                            return  peoBean.getBeginZ().compareTo(t1.getBeginZ());
                        }


                    }
                });


                PeoAdapter peoAdapter=new PeoAdapter(MainActivity.this, temp);
                listView.setAdapter(peoAdapter);
                return false;
            }
        });

        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listView.setAdapter(null);//让界面啥都不显示
                //需要对list进行排序
                String title=id.getText().toString();
                List<PeoBean> temp=null;
                if(title.isEmpty()){
                    temp= PeoDao.getAllPeo();
                }else{
                    temp= PeoDao.getAllPeo(title);
                }

                temp.sort(new Comparator<PeoBean>() {
                    @Override
                    public int compare(PeoBean peoBean, PeoBean t1) {

                        if(peoBean.getBeginZ().equals("#")||t1.getBeginZ().equals("#")){
                            return  1;
                        }else{
                            return  peoBean.getBeginZ().compareTo(t1.getBeginZ());
                        }


                    }
                });


                PeoAdapter peoAdapter=new PeoAdapter(MainActivity.this, temp);
                listView.setAdapter(peoAdapter);
            }
        });





    }




}