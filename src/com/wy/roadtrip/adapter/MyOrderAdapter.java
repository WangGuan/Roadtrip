package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
import android.widget.CheckBox;
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
import com.wy.roadtrip.vo.CollectRouteVo;

/**
 * 我的订单
 * @author wangyi
 *
 */
public class MyOrderAdapter extends SimpleAdapter<CollectRouteVo> {

	private RequestQueue mQueue;

	private ImageLoader imageLoader;
	
	public MyOrderAdapter(List<CollectRouteVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
		mQueue = Volley.newRequestQueue(activity);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void doExtra(View convertView, CollectRouteVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.tv_title.setText(item.getTitle());
		h.tv_content.setText(item.getLine());
		if(Utils.isEmpty(item.getImage())){
			h.iv_picture.setVisibility(View.GONE);
		}else{
			h.iv_picture.setVisibility(View.VISIBLE);
			h.iv_picture.setImageUrl(item.getImage(), imageLoader);
		}
//		if (item.isSelected()) {
//			h.cb_selected.setChecked(true);
//		} else {
//			h.cb_selected.setChecked(false);
//		}
//		if (item.isShown()) {
//			h.cb_selected.setVisibility(View.VISIBLE);
//		} else {
//			h.cb_selected.setVisibility(View.GONE);
//		}
//		h.cb_selected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
//				if (arg1) {
//					item.setSelected(true);
//				} else {
//					item.setSelected(false);
//				}
//			}
//		});

	}

	public static class ViewHolder {
		CheckBox cb_selected;

		TextView tv_title;
		
		TextView tv_content;
		
		NetworkImageView iv_picture;
	}
}
