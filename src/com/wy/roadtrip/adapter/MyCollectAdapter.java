package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.wy.roadtrip.R;
import com.wy.roadtrip.vo.CollectVo;

public class MyCollectAdapter extends SimpleAdapter<CollectVo> {

	public MyCollectAdapter(List<CollectVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
	}

	@Override
	public void doExtra(View convertView, CollectVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.rl_content.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				activity.toast("item");

			}
		});
	}

	public static class ViewHolder {
		RelativeLayout rl_content;
	}
}
