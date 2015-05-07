package com.wy.roadtrip.activity.portal;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 重新设置密码
 * @author wangyi
 *
 */
public class ResetPassActivity extends BaseActivity {

	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("修改密码");
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_reset_pass;
	}

}
