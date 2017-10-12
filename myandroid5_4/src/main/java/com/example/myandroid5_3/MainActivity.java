package com.example.myandroid5_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myandroid5_3.adapter.LvClassInfoAdapter;
import com.example.myandroid5_3.entity.ContactsInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv_info;
    private LvClassInfoAdapter infoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        //准备适配器
        initAdapter();


        initBinder();
    }

    private void initAdapter() {
    }

    private void initData() {
    }

    private void initBinder() {
        lv_info.setAdapter(new LvClassInfoAdapter(getAllClassInfo(),MainActivity.this) {
        });
        infoAdapter = new LvClassInfoAdapter(getAllClassInfo(),MainActivity.this);
        lv_info.setAdapter(infoAdapter);
        lv_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactsInfo info = (ContactsInfo) infoAdapter.getItem(position);
                Toast.makeText(MainActivity.this, info.getName()+":"+info.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        lv_info = (ListView) findViewById(R.id.lv_info);
    }

    public List<ContactsInfo> getAllClassInfo(){
        List<ContactsInfo> list = new ArrayList<>();
        int src[] ={R.mipmap.icon_1,R.mipmap.icon_2,R.mipmap.icon_3,R.mipmap.icon_4,R.mipmap.icon_5,R.mipmap.icon_6,
                R.mipmap.icon_8,R.mipmap.icon_9,R.mipmap.icon_10,R.mipmap.icon_11,R.mipmap.icon_12,R.mipmap.icon_13
        };
        String name [] ={"鬼吹灯之黄皮子坟","大胡子带你砍传奇","峡谷重案组","集结吧王者","明日之子","双世宠妃","醉玲珑",
                "我的前半生","楚乔传","美好的意外","我爱二次元","饭局的诱惑",};
        for (int i = 0; i < 11; i++) {
            ContactsInfo info = new ContactsInfo();
            info.setHead(src[i]);
            info.setName(name[i]);
            info.setPhone("100"+(i+233)+(i+4511));
            list.add(info);
        }

        return list;
    }
}
