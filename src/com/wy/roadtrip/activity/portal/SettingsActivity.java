package com.wy.roadtrip.activity.portal;

import android.view.View;

import com.froyo.commonjar.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;

/**
 * 设置
 * @author wangyi
 *
 */
public class SettingsActivity extends BaseActivity {

	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("设置");
	}
	
	@OnClick(R.id.tv_change_pass)
	void changePass(View view){
		skip(ResetPassActivity.class);
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_settings;
	}

}
