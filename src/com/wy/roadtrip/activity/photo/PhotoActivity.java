package com.wy.roadtrip.activity.photo;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 
 * @Des: 我的相册
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月6日 下午3:25:22
 */
public class PhotoActivity extends BaseActivity {

	
	@Override
	public void doBusiness() {
		TitleBar bar =new TitleBar(activity);
		bar.showBack();
		bar.setTitle("我的相册");
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_photo;
	}

}
