package com.example.myandroid5_3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myandroid5_3.R;
import com.example.myandroid5_3.entity.ContactsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by O on 2017-10-10.
 */

public class LvClassInfoAdapter extends BaseAdapter {
    //List集合Bean
    private List<ContactsInfo> classInfoList = new ArrayList<>();
    ContactsInfo classInfo =new ContactsInfo();
    //布局加载器
    LayoutInflater mLayoutInflater;
    private Map<Integer,Boolean> map=new HashMap<>();

    public LvClassInfoAdapter(List<ContactsInfo> classInfoList, Context context) {
        this.classInfoList = classInfoList;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return classInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return classInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        final ViewHolder mHolder;

        if (convertView == null) {
            Log.i(TAG,"第一次加载666666");
            view = mLayoutInflater.inflate(R.layout.lv_info_item, null);
            mHolder = new ViewHolder();
            mHolder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
            mHolder.tv_lv_name = (TextView) view.findViewById(R.id.tv_lv_name);
            mHolder.tv_lv_phone = (TextView) view.findViewById(R.id.lv_phone);
            mHolder.cb_select = (CheckBox) view.findViewById(R.id.cb_select);
            view.setTag(mHolder);

        } else {
            Log.i(TAG,"重复加载666666");
            view = convertView;
            mHolder = (ViewHolder) view.getTag();
        }

        classInfo = classInfoList.get(position);
        //1.加载布局文件
        //2.从集合中取出数据
        //3.绑定数据
        mHolder.iv_head.setImageResource(classInfo.getHead());
        mHolder.tv_lv_name.setText(classInfo.getName());
        mHolder.tv_lv_phone.setText(classInfo.getPhone());

//        mHolder.cb_select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mHolder.cb_select.isChecked()){
//                    classInfo.setmChecked(true);
//                }else{
//                    classInfo.setmChecked(false);
//                }
//            }
//        });
//        mHolder.cb_select.setChecked(classInfo.ismChecked());
        mHolder.cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    map.put(position,true);
                }else {
                    map.remove(position);
                }
            }
        });

        if(map!=null&&map.containsKey(position)){
            mHolder.cb_select.setChecked(true);
        }else {
            mHolder.cb_select.setChecked(false);
        }


        return view;
    }

    private static class ViewHolder {
        ImageView iv_head;
        TextView tv_lv_name;
        TextView tv_lv_phone;
        CheckBox cb_select;
    }
}
