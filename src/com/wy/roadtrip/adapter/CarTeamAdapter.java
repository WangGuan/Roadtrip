package com.wy.roadtrip.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.froyo.commonjar.utils.SpUtil;
import com.wy.roadtrip.R;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.vo.CarTeamVo;

/**
 * 
 * @Des: 我的车队
 * @author Rhino
 * @version V1.0
 * @created 2015年6月6日 下午3:13:11
 */
public class CarTeamAdapter extends SimpleAdapter<CarTeamVo> {

	public CarTeamAdapter(List<CarTeamVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
	}

	@Override
	public void doExtra(View convertView, final CarTeamVo item, final int position) {
		final ViewHolder h = (ViewHolder) holder;
		h.tv_title.setText(item.getTeam_title());
		
		final SpUtil sp=new SpUtil(activity);
		if ("1".equals(item.getSwitch_on())) {
			h.tg_btn.setChecked(true);
			sp.setValue(Const.CAR_TEAM_ID, item.getId());
		} else {
			h.tg_btn.setChecked(false);
		}
		h.tg_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				List<CarTeamVo> tempData=getDataSource();
				List<CarTeamVo> newData=new ArrayList<CarTeamVo>();
				for (int i = 0; i < tempData.size(); i++) {
					CarTeamVo CarTeamVo=tempData.get(i);
					CarTeamVo.setSwitch_on("0");
					newData.add(CarTeamVo);
				}
				reload(newData);
				if(h.tg_btn.isChecked()){
					//保存信息，同时关闭其他按钮
					item.setSwitch_on("1");
					replace(item, position);
					sp.setValue(Const.CAR_TEAM_ID, item.getId());
				}else{
					//删除信息
					item.setSwitch_on("0");
					replace(item, position);
					sp.remove(Const.CAR_TEAM_ID);
				}
				
			}
		});
	}

	public static class ViewHolder {
		TextView tv_title;
		ToggleButton tg_btn;
	}
}
