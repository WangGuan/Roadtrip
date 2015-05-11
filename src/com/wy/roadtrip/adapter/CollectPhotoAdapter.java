package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.GridSimpleAdapter;
import com.wy.roadtrip.R;
import com.wy.roadtrip.vo.CollectPhotoVo;
/**
 * 收藏--照片
 * @author wangyi
 *
 */
public class CollectPhotoAdapter extends GridSimpleAdapter<CollectPhotoVo> {

	public CollectPhotoAdapter(List<CollectPhotoVo> data,
			BaseActivity activity, int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
	}

	@Override
	public void doExtra(View convertView, CollectPhotoVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
	}

	@Override
	public int setColumns() {
		return 3;
	}

	public static class ViewHolder {

	}
}
