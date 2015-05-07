package com.wy.roadtrip.activity.portal;

import android.view.View;

import com.froyo.commonjar.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;
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
	
	@OnClick(R.id.tv_register)
	void register(View view){
		skip(RegisterActivity.class);
	}
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_login;
	}

}
