package com.wy.roadtrip.fragment;

import android.view.View;

import com.froyo.commonjar.fragment.BaseFragment;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;

/**
 * 
 * @Des: 首页
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月6日 上午11:43:54
 */
public class FragmentHome extends BaseFragment {

	@Override
	protected void setListener() {

	}
	
	@OnClick(R.id.rl_line)
	void line(View view) {
		activity.toast("我的线路");
	}

	@OnClick(R.id.rl_collect)
	void collect(View view) {
		activity.toast("我的收藏");
	}

	@OnClick(R.id.rl_car)
	void car(View view) {
		activity.toast("我的车队");
	}

	@OnClick(R.id.rl_photo)
	void photo(View view) {
		activity.toast("我的相册");
	}

	@OnClick(R.id.rl_mystyle)
	void mystyle(View view) {
		activity.toast("我的定制");
	}

	@OnClick(R.id.rl_menu)
	void menu(View view) {
		activity.toast("菜单");
	}

	@OnClick(R.id.rl_map)
	void offlineMap(View view) {
		activity.toast("离线地图");
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.fragment_home;
	}

}
