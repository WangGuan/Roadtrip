package com.wy.roadtrip.activity.portal;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 
 * @Des: 注册
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月7日 上午10:00:32
 */
public class RegisterActivity extends BaseActivity {

	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("注册");
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_register;
	}

}
