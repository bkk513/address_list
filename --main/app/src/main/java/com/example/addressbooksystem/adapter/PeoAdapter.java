package com.example.addressbooksystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.addressbooksystem.MainActivity;
import com.example.addressbooksystem.R;
import com.example.addressbooksystem.activity.DetailsActivity;
import com.example.addressbooksystem.bean.PeoBean;

import java.util.List;

public class PeoAdapter extends ArrayAdapter<PeoBean> {

    List<PeoBean> items;

    public PeoAdapter(Context context, List<PeoBean> items) {
        super(context, R.layout.book_view,items);
        this.items=items;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, @NonNull ViewGroup parent) {

        if( convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.book_view,parent,false);
        }

        ImageView imageView=convertView.findViewById(R.id.tp);//图片



        TextView name =convertView.findViewById(R.id.name);//名字
        TextView zm=convertView.findViewById(R.id.zm);//名字

        PeoBean peo = items.get(position);
        name.setText(peo.getName());

        if(peo.getSex().equals("男")){
            imageView.setImageResource(R.drawable.man);
        }else if(peo.getSex().equals("女")){
            imageView.setImageResource(R.drawable.wuman);
        }
        //他俩的首字母都是一样
        if(position==0){
            zm.setText(peo.getBeginZ());
        }else{

            PeoBean temp = items.get(position-1);
            if(!temp.getBeginZ().equals( peo.getBeginZ())){
                zm.setText(peo.getBeginZ());
            }else{
                zm.setHeight(1);
            }

        }

        //实现点击跳转


        View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id",peo.getId());
                getContext().startActivity( intent);
            }
        });

        return convertView;

    }
}
