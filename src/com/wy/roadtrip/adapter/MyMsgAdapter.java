package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
import android.widget.RelativeLayout;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.wy.roadtrip.R;
import com.wy.roadtrip.vo.MyMsgVo;

public class MyMsgAdapter extends SimpleAdapter<MyMsgVo> {

	public MyMsgAdapter(List<MyMsgVo> data, BaseActivity activity, int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
	}

	@Override
	public void doExtra(View convertView, MyMsgVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
	}

	public static class ViewHolder {
		RelativeLayout rl_content;
	}
}
