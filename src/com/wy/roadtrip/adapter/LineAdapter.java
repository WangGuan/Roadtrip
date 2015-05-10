package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.wy.roadtrip.R;
import com.wy.roadtrip.vo.LineInfoVo;

public class LineAdapter extends SimpleAdapter<LineInfoVo> {

	public LineAdapter(List<LineInfoVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
	}

	@Override
	public void doExtra(View convertView, final LineInfoVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.tv_title.setText(item.getTitle());
		if (item.isSelected()) {
			h.cb_selected.setChecked(true);
		} else {
			h.cb_selected.setChecked(false);
		}
		if (item.isShown()) {
			h.cb_selected.setVisibility(View.VISIBLE);
		} else {
			h.cb_selected.setVisibility(View.GONE);
		}
		h.cb_selected.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					item.setSelected(true);
				} else {
					item.setSelected(false);
				}
			}
		});

	}

	public static class ViewHolder {
		CheckBox cb_selected;

		TextView tv_title;
	}
}
