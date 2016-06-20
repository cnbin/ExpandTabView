package com.example.view;

import java.util.ArrayList;
import java.util.LinkedList;


import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.adapter.TextAdapter;
import com.example.expandtabview.R;

public class ViewMiddle extends LinearLayout implements ViewBaseAction {

	private ListView regionListView;
	private ListView plateListView;
	private ArrayList<String> groups = new ArrayList<String>();
	private LinkedList<String> childrenItem = new LinkedList<String>();
	private SparseArray<LinkedList<String>> children = new SparseArray<LinkedList<String>>();
	private TextAdapter plateListViewAdapter;
	private TextAdapter earaListViewAdapter;
	private OnSelectListener mOnSelectListener;
	private int tEaraPosition = 0;
	private int tBlockPosition = 0;
	private String showString = "全部科室";

	public ViewMiddle(Context context) {
		super(context);
		init(context);
	}

	public  ViewMiddle(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

//	public void updateShowText(String showArea, String showBlock) {
//		if (showArea == null || showBlock == null) {
//			return;
//		}
//		for (int i = 0; i < groups.size(); i++) {
//			if (groups.get(i).equals(showArea)) {
//				earaListViewAdapter.setSelectedPosition(i);
//				childrenItem.clear();
//				if (i < children.size()) {
//					childrenItem.addAll(children.get(i));
//				}
//				tEaraPosition = i;
//				break;
//			}
//		}
//		for (int j = 0; j < childrenItem.size(); j++) {
//			if (childrenItem.get(j).replace("全部科室", "").equals(showBlock.trim())) {
//				plateListViewAdapter.setSelectedPosition(j);
//				tBlockPosition = j;
//				break;
//			}
//		}
//		setDefaultSelect();
//	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_middle, this, true);
		regionListView = (ListView) findViewById(R.id.listView);
		plateListView = (ListView) findViewById(R.id.listView2);
		setBackgroundDrawable(getResources().getDrawable(
				R.drawable.choosearea_bg_mid));


//		for(int i=0;i<10;i++){
//			groups.add(i+"行");
//			LinkedList<String> tItem = new LinkedList<String>();
//			for(int j=0;j<15;j++){
//
//				tItem.add(i+"行"+j+"列");
//
//			}
//			children.put(i, tItem);
//		}
		groups.add("全部科室");

		groups.add("儿科");
		groups.add("妇科");
		groups.add("骨科");

		groups.add("病理科");
		groups.add("皮肤科");
		groups.add("外科");

		groups.add("肿瘤科");
		groups.add("女科");
		groups.add("放射科");

		groups.add("中医科");
		groups.add("口腔科");
		groups.add("眼科");

		groups.add("耳眼鼻喉科");
		groups.add("男科");
		groups.add("医疗美容科");

		LinkedList<String> tItem0 = new LinkedList<String>();
		tItem0.add("全部科室");
		children.put(0, tItem0);

		LinkedList<String> tItem1= new LinkedList<String>();
		tItem1.add("全部儿科");
		tItem1.add("儿科1");
		tItem1.add("儿科2");
		tItem1.add("儿科3");
		tItem1.add("儿科4");

		children.put(1, tItem1);

		LinkedList<String> tItem2 = new LinkedList<String>();
		tItem2.add("全部妇科");
		tItem2.add("妇科1");
		tItem2.add("妇科2");
		tItem2.add("妇科3");
		tItem2.add("妇科4");

		children.put(2, tItem2);

		LinkedList<String> tItem3 = new LinkedList<String>();
		tItem3.add("骨科1");
		tItem3.add("骨科2");
		tItem3.add("骨科3");
		tItem3.add("骨科4");
		tItem3.add("骨科5");
		tItem3.add("骨科6");
		tItem3.add("骨科7");
		tItem3.add("骨科8");

		children.put(3, tItem3);


		LinkedList<String> tItem4 = new LinkedList<String>();
		tItem4.add("病理科1");
		children.put(4, tItem4);


		LinkedList<String> tItem5 = new LinkedList<String>();
		tItem5.add("皮肤科1");
		children.put(5, tItem5);


		LinkedList<String> tItem6 = new LinkedList<String>();
		tItem6.add("外科1");
		children.put(6, tItem6);


		LinkedList<String> tItem7 = new LinkedList<String>();
		tItem7.add("肿瘤科1");
		children.put(7, tItem7);


		LinkedList<String> tItem8 = new LinkedList<String>();
		tItem8.add("女科1");
		children.put(8, tItem8);


		LinkedList<String> tItem9 = new LinkedList<String>();
		tItem9.add("放射科1");
		children.put(9, tItem9);


		LinkedList<String> tItem10 = new LinkedList<String>();
		tItem10.add("中医科1");
		children.put(10, tItem10);


		LinkedList<String> tItem11 = new LinkedList<String>();
		tItem11.add("口腔科1");
		children.put(11, tItem11);


		LinkedList<String> tItem12 = new LinkedList<String>();
		tItem12.add("眼科1");
		children.put(12, tItem12);


		LinkedList<String> tItem13 = new LinkedList<String>();
		tItem13.add("耳眼鼻喉科1");
		children.put(13, tItem13);


		LinkedList<String> tItem14 = new LinkedList<String>();
		tItem14.add("男科1");
		children.put(14, tItem14);


		LinkedList<String> tItem15 = new LinkedList<String>();
		tItem15.add("全部医疗美容科");
		tItem15.add("医疗美容科1");
		children.put(15, tItem15);


		earaListViewAdapter = new TextAdapter(context, groups,
				R.drawable.choose_item_selected,
				R.drawable.choose_eara_item_selector);
		earaListViewAdapter.setTextSize(17);
		earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
		regionListView.setAdapter(earaListViewAdapter);
		earaListViewAdapter
				.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, int position) {
						if (position < children.size()) {
							childrenItem.clear();
							childrenItem.addAll(children.get(position));
							plateListViewAdapter.notifyDataSetChanged();
						}
					}
				});
		if (tEaraPosition < children.size())
			childrenItem.addAll(children.get(tEaraPosition));
		plateListViewAdapter = new TextAdapter(context, childrenItem,
				R.drawable.choose_item_right,
				R.drawable.choose_plate_item_selector);
		plateListViewAdapter.setTextSize(15);
		plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
		plateListView.setAdapter(plateListViewAdapter);
		plateListViewAdapter
				.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, final int position) {

						showString = childrenItem.get(position);
						if (mOnSelectListener != null) {

							mOnSelectListener.getValue(showString);
						}

					}
				});
		if (tBlockPosition < childrenItem.size())
			showString = childrenItem.get(tBlockPosition);
		if (showString.contains("全部科室")) {
//			showString = showString.replace("全部科室", "");
		}
		setDefaultSelect();

	}

	public void setDefaultSelect() {
		regionListView.setSelection(tEaraPosition);
		plateListView.setSelection(tBlockPosition);
	}

	public String getShowText() {
		return showString;
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String showText);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
