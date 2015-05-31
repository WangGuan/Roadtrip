package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.froyo.commonjar.network.BitmapCache;
import com.froyo.commonjar.utils.Utils;
import com.wy.roadtrip.R;
import com.wy.roadtrip.activity.collect.CollectViewActivity;
import com.wy.roadtrip.vo.CollectVo;

/**
 * 我的收藏--看点，私享
 * 
 * @author wangyi
 *
 */
public class MyCollectViewAdapter extends SimpleAdapter<CollectVo> {

	private RequestQueue mQueue;

	private ImageLoader imageLoader;

	public MyCollectViewAdapter(List<CollectVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
		mQueue = Volley.newRequestQueue(activity);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void doExtra(View convertView, CollectVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		if(Utils.isEmpty(item.getImage())){
			h.iv_header.setVisibility(View.GONE);
		}else{
			h.iv_header.setVisibility(View.VISIBLE);
			h.iv_header.setImageUrl(item.getImage(), imageLoader);
		}
		h.tv_title.setText(item.getName());
		h.tv_content.setText(item.getDesc());
		h.rl_content.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				activity.skip(CollectViewActivity.class);
			}
		});
		h.cb_selected.setVisibility(View.GONE);
	}

	public static class ViewHolder {
		RelativeLayout rl_content;
		NetworkImageView iv_header;
		TextView tv_title;
		TextView tv_content;
		CheckBox cb_selected;
	}
}
