package com.example.myandroid6;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ListView lv_dialog;
    private ArrayAdapter adapter;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.初始化
        lv_dialog = (ListView) findViewById(R.id.lv_dialog);
        //2.数据
        String names[] = {"1.普通对话框 ", "2.列表对话框  ", "3.单选对话框  ", "4.多选对话框  ",
                "5.时间对话框  ", "6.日期对话框 ", "7.进度条对话框 ", "8.自定义对话框"
        };
        //3.适配器
        adapter = new ArrayAdapter(
                MainActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1,
                names
        );//4.绑定适配器
        lv_dialog.setAdapter(adapter);
        //5.事件监听
        lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        normalDialog();
                        break;
                    case 1:
                        listDialog();
                        break;
                    case 2:
                        singleDialog();
                        break;
                    case 3:
                        multiDialog();
                        break;
                    case 4:
                        timeDialog();
                        break;
                    case 5:
                        dateDialog();
                        break;
                    case 6:
                        progessDialog();
                        break;
                    case 7:
                        defineDialog();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void normalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("更新").setMessage("有新版本了,确定要更新吗?")
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progessDialog();
                    }
                })
                .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
    //    1. 普通对话框


    //    2. 列表对话框
    private void listDialog() {

        final String item[] = {"北京", "长沙", "上海", "广州", "杭州", "深圳", "苏州", "武汉", "南京", "福州", "香港", "澳门"};
        new AlertDialog.Builder(MainActivity.this).setTitle("选择地址").setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, item[which], Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    //    3. 单选对话框
    String mywhich = "5000-8000元";

    private void singleDialog() {

        final String sex[] = {"5000-8000元", "8000-10000元", "10000-15000元", "15000-20000元", "我一般给别人发工资"};
        new AlertDialog.Builder(MainActivity.this).setTitle("选择您的月薪")
                .setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mywhich = sex[which];
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, mywhich, Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    //    4. 多选对话框
    private void multiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("选择您喜欢的游戏");
        final String items[] = {"英雄联盟", "绝地大逃杀", "守望先锋", "Dota2", "穿越火线", "地下城与勇士", "梦幻西游"};
        final boolean checks[] = {true, false, false, false, false, false, false};
        builder.setMultiChoiceItems(items, checks, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sbr = new StringBuilder();
                for (int i = 0; i < items.length; i++) {
                    if (checks[i]) {
                        sbr.append(items[i]).append(",");
                    }
                }
                sbr.deleteCharAt(sbr.length() - 1);
                Toast.makeText(MainActivity.this, sbr, Toast.LENGTH_SHORT).show();

            }
        }).show();

    }

    //    5. 时间对话框
    private void timeDialog() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, true).show();

    }

    //    6. 日期对话框
    private void dateDialog() {
        int year = calendar.get(Calendar.YEAR);
        int mohth = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(MainActivity.this, year + "-" + (month + 1) + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        }, year, mohth, day).show();

    }

    //    7. 进度条对话框

    private void progessDialog() {
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        //设置对话进度条样式为水平
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle("请稍等");
        //设置提示信息
        pd.setMessage("正在玩命下载中......");
        //设置对话进度条显示在屏幕顶部（方便截图）
//        pd.getWindow().setGravity(Gravity.TOP);
        pd.setMax(100);
        pd.show();//调用show方法显示进度条对话框
        //使用匿名内部类实现线程并启动

        new Thread(new Runnable() {
            //初始下载进度
            int initial = 0;
            @Override
            public void run() {
                while (initial < pd.getMax()) {//设置循环条件
                    pd.setProgress(initial += 30);//设置每次完成40
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Looper.prepare();//给当前线程初始化Looper
                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                Looper.loop();
            }
        }).start();


    }

    //    8. 自定义对话框
    private void defineDialog() {
        final View view = View.inflate(MainActivity.this,R.layout.login,null);
        new AlertDialog.Builder(MainActivity.this).setTitle("登录")
                .setView(view)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String name= ((EditText)view.findViewById(R.id.et_name)).getText().toString();
                        String password= ((EditText)view.findViewById(R.id.et_password)).getText().toString();
                        if(!(name.equals("") && password.equals(""))){
                            Toast.makeText(MainActivity.this, "登录成功:   "+name+"    :   "+password, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

}
