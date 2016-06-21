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

		viewLeft = new ViewLeft(this);
		viewMiddle = new ViewMiddle(this);
		viewRight = new ViewRight(this);

		listView = (ListView) findViewById(R.id.listView3);

		String[] tvUserNameArray =  new String[]{
				"John","Mike","Nick","John","Mike","Nick","John","Mike","Nick",
  				"John","Mike","Nick","John","Mike","Nick","John","Mike","Nick"};

		String[] tvJobArray =  new String[]{
				"主治医师","副主任医师","医师","主治医师","副主任医师","医师","主治医师","副主任医师","医师",
				"主治医师","副主任医师","医师","主治医师","副主任医师","医师","主治医师","副主任医师","医师"};

		String[] tvDepartmentArray =  new String[]{
				"妇科","儿科","耳眼鼻喉科","精神科","外科","内科","肿瘤科","小儿麻痹科","神经外科",
				"神经内科","小二科","保健科","妇科","儿科","耳眼鼻喉科","妇科","儿科","耳眼鼻喉科"};

		String[] tvProfileArray =  new String[]{
				"南方医科大学眼科博士","妇产科经验丰富","多年研究小二科","毕业于华南理工大学,精通内科,外科,保健科,1969年出生","xxxxxxtttxrrrrrrrrr","yyyyyyyy","xxxxxx","xxxxxx","yyyyyyyy",
				"xxxxxx","xxxxxx","yyyyyyyy","xxxxxx","xxxxxx","yyyyyyyy","xxxxxx","xxxxxx","yyyyyyyy"};


		homeList = new ArrayList<HomeModel>();

		for(int i=0;i<tvUserNameArray.length;i++) {

			homeList.add(new HomeModel(tvUserNameArray[i],tvDepartmentArray[i],tvJobArray[i],tvProfileArray[i]));
		}

		adapter = new MainListAdapter(this,homeList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListenerImpl()); // 单击选项

	}

	private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			HomeModel model = homeList.get(position);

			if(model.getTvDepartment().equals("妇科")) {

				Toast.makeText(MainActivity.this, "选择妇科", Toast.LENGTH_SHORT).show();

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
				
				onRefresh(viewMiddle,showText);
				
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
		String str = str1+str2+str3;


		Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();

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
