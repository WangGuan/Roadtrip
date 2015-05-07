package com.wy.roadtrip.activity.portal;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;

/**
 * 注册第二步，设置个人信息
 * @author wangyi
 *
 */
public class AddInfoActivity extends BaseActivity {

	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("填写信息");
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_add_info;
	}

}
