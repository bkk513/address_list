package com.example.addressbooksystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addressbooksystem.MainActivity;
import com.example.addressbooksystem.R;
import com.example.addressbooksystem.bean.PeoBean;
import com.example.addressbooksystem.dao.PeoDao;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        TextView name=findViewById(R.id.add_name);//姓名
        TextView phone=findViewById(R.id.add_phone);//手机号

        RadioButton man=findViewById(R.id.add_man);
        RadioButton woman=findViewById(R.id.add_woman);
        man.setChecked(true);
        TextView bz=findViewById(R.id.add_bz);//手机号


        //实现点击
        Button up =findViewById(R.id.add_button);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameT=name.getText().toString().trim();
                String phoneT=phone.getText().toString().trim();
                String bzT=bz.getText().toString().trim();

                if(nameT.isEmpty()){
                    Toast.makeText(AddActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }else if(phoneT.isEmpty()){
                    Toast.makeText(AddActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }else if(bzT.isEmpty()){
                    Toast.makeText(AddActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                }else{

                    String sex="女";
                    if(man.isChecked()){
                        sex="男";
                    }
                    PeoDao.savePeo( nameT,phoneT, sex,bzT);
                    Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

                }

            }
        });



        //开始写返回的功能
        Toolbar toolbar=findViewById(R.id.toolbar_add);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}