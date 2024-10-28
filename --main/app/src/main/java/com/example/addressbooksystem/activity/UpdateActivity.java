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

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Intent intent=getIntent();
        String id=intent.getStringExtra("id");


        PeoBean peo = PeoDao.getOnePeo(id);//得到一个人的信息

       TextView name=findViewById(R.id.up_name);//姓名
        TextView phone=findViewById(R.id.up_phone);//手机号
        RadioButton man=findViewById(R.id.man);
        RadioButton woman=findViewById(R.id.woman);
        TextView bz=findViewById(R.id.up_bz);//手机号

        name.setText( peo.getName());
        phone.setText(peo.getNum());
        if(peo.getSex().equals("男")){
            man.setChecked(true);
        }else{
            woman.setChecked(true);
        }
        bz.setText(peo.getRemark());

        //实现点击
        Button up =findViewById(R.id.up_button);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameT=name.getText().toString().trim();
                String phoneT=phone.getText().toString().trim();
                String bzT=bz.getText().toString().trim();

                if(nameT.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }else if(phoneT.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }else if(bzT.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                }else{

                    String sex="女";
                    if(man.isChecked()){
                        sex="男";
                    }
                    PeoDao.updatePeo( nameT,phoneT, sex,bzT,id);
                    Toast.makeText(UpdateActivity.this, "更改成功", Toast.LENGTH_SHORT).show();

                }

            }
        });



        //开始写返回的功能
        Toolbar toolbar=findViewById(R.id.toolbar_up);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });




    }
}