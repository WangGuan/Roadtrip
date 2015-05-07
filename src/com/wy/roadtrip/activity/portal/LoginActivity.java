package com.wy.roadtrip.activity.portal;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 
 * @Des: 登录
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月7日 上午10:00:32
 */
public class LoginActivity extends BaseActivity {

	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.setTitle("登录");
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_login;
	}

}
