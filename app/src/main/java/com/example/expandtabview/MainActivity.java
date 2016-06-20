package com.example.expandtabview;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.MainListAdapter;
import com.example.view.ExpandTabView;
import com.example.view.ViewMiddle;
import com.example.view.ViewLeft;
import com.example.view.ViewRight;

import model.HomeModel;

public class MainActivity extends Activity {

	private ExpandTabView expandTabView;
	private ArrayList<View> mViewArray = new ArrayList<View>();
	private ViewMiddle viewMiddle;
	private ViewLeft viewLeft;
	private ViewRight viewRight;

	private ListView listView;
	private ArrayList<HomeModel> homeList;
	private  MainListAdapter adapter;
	
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
		viewMiddle = new ViewMiddle(this);
		viewLeft = new ViewLeft(this);
		viewRight = new ViewRight(this);

		listView = (ListView) findViewById(R.id.listView3);

		String[] array =  new String[] {"考勤打卡", "考勤查询", "历史轨迹","通讯录","定时上传记录"};

		homeList = new ArrayList<HomeModel>();

		for(int i=0;i<array.length;i++) {

			homeList.add(new HomeModel(array[i]));

		}

		adapter = new MainListAdapter(this,homeList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListenerImpl()); // 单击选项

	}

	private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			HomeModel model = homeList.get(position);

			if(model.getTitle().equals("考勤打卡")) {

				Toast.makeText(MainActivity.this, "考勤打卡", Toast.LENGTH_SHORT).show();

			}
		}
	}

	private void initVaule() {

		mViewArray.add(viewMiddle);
		mViewArray.add(viewLeft);
		mViewArray.add(viewRight);

		ArrayList<String> mTextArray = new ArrayList<String>();
		mTextArray.add("距离");
		mTextArray.add("区域");
		mTextArray.add("距离");
		expandTabView.setValue(mTextArray, mViewArray);

		expandTabView.setTitle(viewMiddle.getShowText(), 0);
		expandTabView.setTitle(viewLeft.getShowText(), 1);
		expandTabView.setTitle(viewRight.getShowText(), 2);
		
	}

	private void initListener() {
		
		viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewMiddle, showText);
			}
		});
		
		viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {
			
			@Override
			public void getValue(String showText) {
				
				onRefresh(viewLeft,showText);
				
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
		Toast.makeText(MainActivity.this, showText, Toast.LENGTH_SHORT).show();

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
