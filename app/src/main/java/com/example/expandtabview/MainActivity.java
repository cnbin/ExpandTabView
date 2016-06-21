package com.example.expandtabview;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.MainListAdapter;
import com.example.model.HttpUtils;
import com.example.view.ExpandTabView;
import com.example.view.ViewMiddle;
import com.example.view.ViewLeft;
import com.example.view.ViewRight;

import org.json.JSONArray;
import org.json.JSONObject;

import model.HomeModel;

public class MainActivity extends Activity {

    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ViewMiddle viewMiddle;
    private ViewLeft viewLeft;
    private ViewRight viewRight;

    private ListView listView;
    private ArrayList<HomeModel> homeList;
    private MainListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVaule();
        initListener();

    }

    private void initView() {

        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);

        viewLeft = new ViewLeft(this);
        viewMiddle = new ViewMiddle(this);
        viewRight = new ViewRight(this);

        listView = (ListView) findViewById(R.id.listView3);
        homeList = new ArrayList<HomeModel>();

        adapter = new MainListAdapter(this, homeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListenerImpl()); // 单击选项

        request("1");
        showProgressDialog("提示", "正在加载中");
    }

    public void request(final String iPageNo) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    String GET_NEWS_URL = String.format("http://192.168.1.90:10089/DWTong/Webservice/MobileService.asmx/GetUsers?iPageNo=%s", iPageNo);
                    System.out.println(GET_NEWS_URL);
                    HttpUtils.getNewsJSON(GET_NEWS_URL, getListHandler);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();


    }

    private Handler getListHandler = new Handler() {


        public void handleMessage(Message msg) {

            homeList.clear();//清空所有数组

            hideProgressDialog();

            String jsonData = (String) msg.obj;

            String substring = jsonData.substring(74, jsonData.length());
            String resultString = substring.substring(0, substring.length() - 9);
            //System.out.printf("resultString is %s",resultString);
            try {
                JSONArray jsonArray = new JSONArray(resultString);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    String UserName = object.getString("UserName");
                    String UserPwd = object.getString("UserPwd");
                    String TrueName = object.getString("TrueName");
                    String Serils = object.getString("Serils");
                    String Department = object.getString("Department");
                    String JiaoSe = object.getString("JiaoSe");
                    String ActiveTime = object.getString("ActiveTime");
                    String ZhiWei = object.getString("ZhiWei");
                    String ZaiGang = object.getString("ZaiGang");
                    String GroupName = object.getString("GroupName");
                    homeList.add(new HomeModel(TrueName, Department, JiaoSe, GroupName));
                }

                Toast toast = Toast.makeText(getApplicationContext(),
                        "请求成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                toast.show();

                adapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    /*
  * 提示加载
  */
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {

            progressDialog = ProgressDialog.show(this, title,
                    message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }

        progressDialog.show();

    }

    /*
     * 隐藏提示加载
     */
    public void hideProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            HomeModel model = homeList.get(position);

            if (model.getTvUserName().equals("赖丽城")) {

                Toast.makeText(MainActivity.this, "选择赖丽城", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            String GET_NEWS_URL = String.format("http://192.168.1.90:10089/DWTong/Webservice/MobileService.asmx/Login?uname=%s&pass=%s", "admin", "admin");
                            System.out.println(GET_NEWS_URL);
                            HttpUtils.getNewsJSON(GET_NEWS_URL, getListHandler);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        }
    }

    private void initVaule() {

        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);

        ArrayList<String> mTextArray = new ArrayList<String>();
        //此处用法暂时不知道
        mTextArray.add("");
        mTextArray.add("");
        mTextArray.add("");
        expandTabView.setValue(mTextArray, mViewArray);

        expandTabView.setTitle(viewLeft.getShowText(), 0);
        expandTabView.setTitle(viewMiddle.getShowText(), 1);
        expandTabView.setTitle(viewRight.getShowText(), 2);

    }

    private void initListener() {

        viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {

            @Override
            public void getValue(String showText) {

                onRefresh(viewMiddle, showText);

            }
        });

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });

    }

    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        //Toast.makeText(MainActivity.this, showText, Toast.LENGTH_SHORT).show();


        String str1 = viewLeft.getShowText();
        String str2 = viewMiddle.getShowText();
        String str3 = viewRight.getShowText();
        String str = str1 + str2 + str3;


        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();

        if (str1 == "外砂医院") {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        String GET_NEWS_URL = String.format("http://192.168.1.90:10089/DWTong/Webservice/MobileService.asmx/Login?uname=%s&pass=%s", "admin", "admin");
                        System.out.println(GET_NEWS_URL);
                        HttpUtils.getNewsJSON(GET_NEWS_URL, getListHandler);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        if (str1 == "汕头市中心医院") {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        String GET_NEWS_URL = String.format("http://192.168.1.90:10089/DWTong/Webservice/MobileService.asmx/GetUsers?iPageNo=%s","1");
                        System.out.println(GET_NEWS_URL);
                        HttpUtils.getNewsJSON(GET_NEWS_URL, getListHandler);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


 }



    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();
        }

    }

}
