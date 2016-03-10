package com.example.zybang.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zybang.myapplication.com.example.zybang.adapter.MyAdapter;
import com.example.zybang.myapplication.com.example.zybang.storeage.AssetsDatabaseManager;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class dbActivity extends ActionBarActivity {
    Button add_bt, change_btn;
    MyAdapter adapter;
    ArrayList<String> datas = new ArrayList<String>();
    EditText et_ui, et_baby;
    ListView lv;
    long before_time;
    boolean isUI = true;
    Thread myThread;
    LinkedBlockingQueue<String> mBlockingQueue = new LinkedBlockingQueue<String>();
    String et_now;
    //boolean isCancle = false;
    int DELAY_TIME = 100;

    SQLiteDatabase db1;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    Log.e("wzb", "子线程刷新时间：" + (System.currentTimeMillis() - before_time) + "ms");
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        // 初始化，只需要调用一次
        AssetsDatabaseManager.initManager(getApplication());
        // 获取管理对象，因为数据库需要通过管理对象才能够获取
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
       /* try {
            mg.copyFileFromAssetsToSDCard("aaa.db");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/
        //通过管理对象获取数据库
        db1 = mg.getDatabase("aaa.db");

        adapter = new MyAdapter(datas, this);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);

        et_ui = (EditText) findViewById(R.id.et_ui);
        et_baby = (EditText) findViewById(R.id.et_baby);
        et_ui.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUI) {
                    clearData();
                    if (et_ui.getText().length() != 0) {
                        before_time = System.currentTimeMillis();
                        getDataFromDB(db1, et_ui.getText().toString());
                        adapter.notifyDataSetChanged();
                        Log.e("wzb", "UI刷新时间：" + (System.currentTimeMillis() - before_time) + "ms");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_baby.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isUI) {
                    try {
                        clearData();
                        before_time = System.currentTimeMillis();
                        if (et_baby.getText().length() != 0)
                            mBlockingQueue.put(et_baby.getText().toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (myThread == null) {
                        myThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    while ((et_now = mBlockingQueue.take()) != null) {
                                        Thread.sleep(DELAY_TIME);

                                        //如果有改变  不查数据库
                                        if (mBlockingQueue.size() > 0) {
                                            while (mBlockingQueue.size() != 1) {
                                                mBlockingQueue.take();
                                            }
                                        } else {
                                            getDataFromDB(db1, et_now);
                                            mHandler.sendEmptyMessage(1);

                                        }

                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                super.run();
                            }
                        };
                        myThread.start();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        add_bt = (Button) findViewById(R.id.add_bt);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToDB(db1, "a");
            }
        });
        change_btn = (Button) findViewById(R.id.change_btn);
        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUI) {
                    isUI = false;
                    change_btn.setText("当前为子线程刷新数据");
                } else {
                    isUI = true;
                    change_btn.setText("当前为UI线程刷新数据");
                }
            }
        });

    }

    void getDataFromDB(SQLiteDatabase sqLiteDatabase, String str) {

        Cursor cursor = sqLiteDatabase.query(true, "cards", new String[]{"card"}, "card like ?", new String[]{str + "%"}, null, null, "card asc", "0,5");

        int j = 0;
        while (cursor.moveToNext() && j < 5) {
            int nameColumnIndex = cursor.getColumnIndex("card");
            String strValue = cursor.getString(nameColumnIndex);
            Log.e("wzb", strValue);
            datas.add(strValue);
            j++;
        }
        cursor.close();
    }

    void clearData() {
        datas.clear();
    }

    void addDataToDB(SQLiteDatabase sqLiteDatabase, String str) {
        sqLiteDatabase.execSQL("insert into cards(card) values('" + str + "')");

    }


}
