package com.wy.roadtrip.activity.menu;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 
 * @Des: 我的菜单
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月6日 下午3:25:22
 */
public class MenuActivity extends BaseActivity {

	
	@Override
	public void doBusiness() {
		TitleBar bar =new TitleBar(activity);
		bar.showBack();
		bar.setTitle("我的菜单");
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_menu;
	}

}
