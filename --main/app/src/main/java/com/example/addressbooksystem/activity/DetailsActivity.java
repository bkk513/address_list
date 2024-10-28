package com.example.addressbooksystem.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.addressbooksystem.MainActivity;
import com.example.addressbooksystem.R;
import com.example.addressbooksystem.bean.PeoBean;
import com.example.addressbooksystem.dao.PeoDao;
import com.example.addressbooksystem.until.DBUntil;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        String id=getIntent().getStringExtra("id");

        //获取电话号

        TextView num=findViewById(R.id. de_phone);

        Button call_phone=findViewById(R.id.de_da_phone);
        call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //判断是否有权限
                int a=ContextCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE);//获取电话权限的ID
                if(a==PackageManager.PERMISSION_GRANTED){
                    //有权限打电话
                    Log.d("AAA","有权限");
                    makePhoneCall( num.getText().toString().trim());
                }else{
                    ActivityCompat.requestPermissions(DetailsActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                    Log.d("AAA","无权限");
                }


            }
        });

        Button de_da_message=findViewById(R.id.de_da_message);
        de_da_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个Intent，指定动作为发送短信
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                // 设置URI，指定短信的"smsto:"协议，这将打开默认的短信应用程序
                Uri uri = Uri.parse("smsto:" + Uri.encode(num.getText().toString().trim()));
                intent.setData(uri);
                // 如果你想在短信应用程序中预填充消息内容，可以使用以下代码
                //intent.putExtra("sms_body", "");
                // 启动短信应用程序
                startActivity(intent);

            }
        });
        //返回功能

        Button  de_back=findViewById(R.id. de_back);
        de_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个Intent，指定动作为发送短信
                Intent intent=new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        Button  de_del=findViewById(R.id. de_del);
        de_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PeoDao.delPeo(id);
                Toast.makeText(DetailsActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


        //点击更改
        Button  de_up=findViewById(R.id.de_up);
        de_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DetailsActivity.this, UpdateActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

        //去写详情的功能
        PeoBean peo = PeoDao.getOnePeo(id);


        //根据性别设置头像
       ImageView tx= findViewById(R.id.de_tx);
        if(peo.getSex().equals("男")){
            tx.setImageResource(R.drawable.peo_man);
        }else{
            tx.setImageResource(R.drawable.peo_woman);
        }
        //设置名字
        TextView name=findViewById(R.id.de_name);
        name.setText(peo.getName());

        TextView phone=findViewById(R.id.de_phone);
        phone.setText(peo.getNum());


        TextView sex=findViewById(R.id.de_sex);
        sex.setText("性别:"+peo.getSex());

        TextView remark=findViewById(R.id.de_remark);
        remark.setText(peo.getRemark());

    }

    /**
     * 电话号
     * @param num
     */
    //打电话的功能
    private void makePhoneCall(String num){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + num));
        startActivity(callIntent);


    }



}