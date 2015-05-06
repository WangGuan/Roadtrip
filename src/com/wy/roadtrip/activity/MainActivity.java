package com.wy.roadtrip.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.fragment.BaseFragment;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.fragment.FragmentGo;
import com.wy.roadtrip.fragment.FragmentHome;
import com.wy.roadtrip.fragment.FragmentInfo;
import com.wy.roadtrip.fragment.FragmentMap;

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
	
	private SparseArray<Fragment> fragContainer = new SparseArray<Fragment>();

	private static BaseFragment lastFragment;

	@Override
	public void doBusiness() {
		bar = new TitleBar(activity);
		bar.setTitle("首页");
		
		BaseFragment fragment = new FragmentHome();
		lastFragment = fragment;
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.fl_content, fragment);
		fragContainer.put(0, fragment);
		fragmentTransaction.commit();
		
		
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
		switchFragment(position);
	}
	
	public void switchFragment(int position) {
		Fragment fragment = null;
		if (position == 0) {
			fragment = fragContainer.get(0);
			if (fragment == null) {
				fragment = new FragmentHome();
				fragContainer.put(0, fragment);
			}
		} else if (position == 1) {
			fragment = fragContainer.get(1);
			if (fragment == null) {
				fragment = new FragmentGo();
				fragContainer.put(1, fragment);
			}

		} else if (position == 2) {
			fragment = fragContainer.get(2);
			if (fragment == null) {
				fragment = new FragmentMap();
				fragContainer.put(2, fragment);
			}
		} else if (position == 3) {
			if (fragment == null) {
				fragment = new FragmentInfo();
				fragContainer.put(3, fragment);
			}
		}

		changeFragment(lastFragment, (BaseFragment) fragment);
	}

	private void changeFragment(BaseFragment pre, BaseFragment next) {
		if (pre == next) {
			return;
		}
		lastFragment = next;

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		if (!next.isAdded()) {
			transaction.hide(pre).add(R.id.fl_content, next).commit();
		} else {
			transaction.hide(pre).show(next).commit();
		}
	}


	@Override
	protected int setLayoutResID() {
		return R.layout.activity_main;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {

	}
}
