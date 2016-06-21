package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expandtabview.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import model.HomeModel;

/**
 * Created by wuyongmin on 16/6/20.
 */
public class MainListAdapter extends BaseAdapter {

    private Context context;
    private List<HomeModel> list;
    ViewHolder viewHolder = new ViewHolder();

    private static class ViewHolder {

        ImageView ivUserImg;
        TextView tvUserName;
        TextView tvDepartment;
        TextView tvJob;
        TextView tvProfile;

    }

    public MainListAdapter(Context context,List<HomeModel> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.mainlist_item, null);

            viewHolder.ivUserImg = (ImageView) convertView.findViewById(R.id.ivUserImg);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            viewHolder.tvDepartment = (TextView)convertView.findViewById(R.id.tvDepartment);
            viewHolder.tvJob = (TextView)convertView.findViewById(R.id.tvJob);
            viewHolder.tvProfile = (TextView)convertView.findViewById(R.id.tvProfile);

            convertView.setTag(viewHolder);
//        }

//        else
//        {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
        HomeModel home = list.get(position);

        viewHolder.tvUserName.setText(home.getTvUserName());
        viewHolder.tvDepartment.setText(home.getTvDepartment());
        viewHolder.tvJob.setText(home.getTvJob());
        viewHolder.tvProfile.setText(home.getTvProfile());

        return convertView;
    }

}
