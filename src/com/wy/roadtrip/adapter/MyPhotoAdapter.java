package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
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
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.vo.MyPhotoVo;
import com.wy.roadtrip.vo.MyPhotoVo.Pic;

/**
 * 
 * @Des: 我的关注列表
 * @author Rhino
 * @version V1.0
 * @created 2015年6月6日 下午4:40:02
 */
public class MyPhotoAdapter extends SimpleAdapter<MyPhotoVo> {

	private RequestQueue mQueue;

	private ImageLoader imageLoader;

	public MyPhotoAdapter(List<MyPhotoVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
		mQueue = Volley.newRequestQueue(activity);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void doExtra(View convertView, final MyPhotoVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.tv_day.setText(Utils.formatTime(
				Long.parseLong(item.getCreate_time() + "000"), "dd"));
		h.tv_month.setText(convert(Utils.formatTime(
				Long.parseLong(item.getCreate_time() + "000"), "MM")));
		h.iv_1.setImageUrl(item.getCover(), imageLoader);
		if (item.getPics().size() > 1) {
			h.tv_title.setVisibility(View.GONE);
			for (int i = 0; i < item.getPics().size(); i++) {
				Pic pic = item.getPics().get(i);
				if (i == 0) {
					h.iv_2.setVisibility(View.VISIBLE);
					h.iv_2.setImageUrl(pic.getPic_path(), imageLoader);
				} else if (i == 1) {
					h.iv_3.setImageUrl(pic.getPic_path(), imageLoader);
					h.iv_3.setVisibility(View.VISIBLE);
				} else if (i == 2) {
					h.iv_4.setVisibility(View.VISIBLE);
					h.iv_4.setImageUrl(pic.getPic_path(), imageLoader);
				}
			}
		} else {
			h.tv_title.setVisibility(View.VISIBLE);
			h.tv_title.setText(item.getTitle());
			h.iv_2.setVisibility(View.GONE);
			h.iv_3.setVisibility(View.GONE);
			h.iv_4.setVisibility(View.GONE);
		}
	}

	public static class ViewHolder {

		TextView tv_title;
		TextView tv_day;
		TextView tv_month;

		NetworkImageView iv_1;
		NetworkImageView iv_2;
		NetworkImageView iv_3;
		NetworkImageView iv_4;
	}

	private String convert(String str) {
		String result = "";
		if ("01".equals(str)) {
			result = "一月";
		} else if ("02".equals(str)) {
			result = "二月";
		} else if ("03".equals(str)) {
			result = "三月";
		} else if ("04".equals(str)) {
			result = "四月";
		} else if ("05".equals(str)) {
			result = "五月";
		}

		else if ("06".equals(str)) {
			result = "六月";
		} else if ("07".equals(str)) {
			result = "七月";
		} else if ("08".equals(str)) {
			result = "八月";
		} else if ("09".equals(str)) {
			result = "九月";
		}

		else if ("10".equals(str)) {
			result = "十月";
		} else if ("11".equals(str)) {
			result = "十一月";
		}

		else if ("12".equals(str)) {
			result = "十二月";
		}
		return result;
	}
}
