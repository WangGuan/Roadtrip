package com.wy.roadtrip.adapter;

import java.util.List;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.froyo.commonjar.utils.Utils;
import com.wy.roadtrip.R;
import com.wy.roadtrip.vo.MsgDetailVo;

public class MsgDetailAdapter extends SimpleAdapter<MsgDetailVo> {

	public MsgDetailAdapter(List<MsgDetailVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
	}

	@Override
	public void doExtra(View convertView, MsgDetailVo item, int position) {
		ViewHolder h = (ViewHolder) holder;

		if (!item.isSend()) {
			
			RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
			lp1.addRule(RelativeLayout.BELOW,R.id.tv_time);
			h.iv_avator.setLayoutParams(lp1);
			
			RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp3.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
			lp3.addRule(RelativeLayout.RIGHT_OF,R.id.iv_avator);
			h.tv_time.setLayoutParams(lp3);
			h.tv_time.setPadding(28, 0, 0, 0);
			
			
			RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp2.addRule(RelativeLayout.RIGHT_OF, R.id.iv_avator);
			lp2.addRule(RelativeLayout.BELOW, R.id.tv_time);

			lp2.leftMargin=8;
			lp2.topMargin=12;
			h.tv_left.setLayoutParams(lp2);
			// 用户的反馈、回复
			h.tv_left.setGravity(Gravity.CENTER);
			h.tv_left.setTextColor(Color.parseColor("#333333"));
			h.tv_left.setBackgroundResource(R.drawable.icon_faq_left);
		} else {
			// 开发者的回复
			
			RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
			lp1.addRule(RelativeLayout.BELOW,R.id.tv_time);
			h.iv_avator.setLayoutParams(lp1);
			
			RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp3.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
			lp3.addRule(RelativeLayout.LEFT_OF,R.id.iv_avator);
			h.tv_time.setLayoutParams(lp3);
			h.tv_time.setPadding(0, 0, 28, 0);
			
			
			RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp2.addRule(RelativeLayout.LEFT_OF, R.id.iv_avator);
			lp2.addRule(RelativeLayout.BELOW, R.id.tv_time);
			lp2.rightMargin=8;
			lp2.topMargin=12;
			h.tv_left.setLayoutParams(lp2);
			
			h.tv_left.setGravity(Gravity.CENTER);
			h.tv_left.setTextColor(Color.parseColor("#ffffff"));
			h.tv_left.setBackgroundResource(R.drawable.icon_faq_right);
		}
		h.tv_time.setText(Utils.formatTime(item.getCreateTime()+""));
		h.tv_left.setText(item.getContent());
	}

	public static class ViewHolder {
		TextView tv_left;
		TextView tv_time;
		ImageView iv_avator;
	}
}
