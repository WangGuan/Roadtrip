package com.wy.roadtrip.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.froyo.commonjar.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;

/**
 * 
 * @Des: 首页
 * @author Rhino
 * @version V1.0
 * @created 2015年5月5日 上午10:19:16
 */
public class MainActivity extends BaseActivity {

	@ViewInject(R.id.tv_home)
	private TextView tvHome;

	@ViewInject(R.id.tv_go)
	private TextView tvGo;

	@ViewInject(R.id.tv_map)
	private TextView tvMap;

	@ViewInject(R.id.tv_info)
	private TextView tvInfo;

	private List<TextView> tvList = new ArrayList<TextView>();
	TitleBar bar = null;

	@Override
	public void doBusiness() {
		bar = new TitleBar(activity);
		bar.setTitle("首页");
		tvList.add(tvHome);
		tvList.add(tvGo);
		tvList.add(tvMap);
		tvList.add(tvInfo);
		changeBtnBg(0);
	}

	@OnClick(R.id.tv_home)
	void home(View view) {
		changeBtnBg(0);
	}

	@OnClick(R.id.tv_go)
	void go(View view) {
		changeBtnBg(1);
	}

	@OnClick(R.id.tv_map)
	void map(View view) {
		changeBtnBg(2);
	}

	@OnClick(R.id.tv_info)
	void info(View view) {
		changeBtnBg(3);
	}

	@OnClick(R.id.rl_line)
	void line(View view) {
		toast("我的线路");
	}

	@OnClick(R.id.rl_collect)
	void collect(View view) {
		toast("我的收藏");
	}

	@OnClick(R.id.rl_car)
	void car(View view) {
		toast("我的车队");
	}

	@OnClick(R.id.rl_photo)
	void photo(View view) {
		toast("我的相册");
	}

	@OnClick(R.id.rl_mystyle)
	void mystyle(View view) {
		toast("我的定制");
	}

	@OnClick(R.id.rl_menu)
	void menu(View view) {
		toast("菜单");
	}

	@OnClick(R.id.rl_map)
	void offlineMap(View view) {
		toast("离线地图");
	}

	private void changeBtnBg(int position) {
		for (int i = 0; i < tvList.size(); i++) {
			if (position == i) {
				tvList.get(i).setTextColor(Color.rgb(0xcc, 0x00, 0x00));
			} else {
				tvList.get(i).setTextColor(Color.rgb(0x8A, 0x8A, 0x8A));
			}
		}
		switch (position) {
		case 0:
			bar.setTitle("首页");
			tvHome.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act), null, null);
			tvGo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvMap.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvInfo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);

			break;
		case 1:
			bar.setTitle("我去");
			tvHome.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvGo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act), null, null);
			tvMap.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvInfo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			break;
		case 2:
			bar.setTitle("地图");
			tvHome.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvGo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvMap.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act), null, null);
			tvInfo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			break;
		case 3:
			bar.setTitle("个人中心");
			tvHome.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvGo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvMap.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act_active), null,
					null);
			tvInfo.setCompoundDrawablesWithIntrinsicBounds(null,
					activity.getDrawableRes(R.drawable.icon_act), null, null);

			break;
		}
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_main;
	}
}
