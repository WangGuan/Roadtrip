package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
import android.widget.CheckBox;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.GridSimpleAdapter;
import com.froyo.commonjar.network.BitmapCache;
import com.wy.roadtrip.R;
import com.wy.roadtrip.vo.MyPhotoVo.Pic;

/**
 * 收藏--照片
 * 
 * @author wangyi
 * 
 */
public class MyPhotoDetailAdapter extends GridSimpleAdapter<Pic> {

	private RequestQueue mQueue;

	private ImageLoader imageLoader;

	public MyPhotoDetailAdapter(List<Pic> data,
			BaseActivity activity, int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
		mQueue = Volley.newRequestQueue(activity);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void doExtra(View convertView, Pic item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.iv_header.setImageUrl(item.getPic_path(), imageLoader);
		h.cb_selected.setVisibility(View.GONE);
	}

	@Override
	public int setColumns() {
		return 3;
	}

	public static class ViewHolder {
		NetworkImageView iv_header;
		CheckBox cb_selected;
	}
}
