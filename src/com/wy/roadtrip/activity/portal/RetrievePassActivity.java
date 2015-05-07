package com.wy.roadtrip.activity.portal;

import android.view.View;

import com.froyo.commonjar.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 找回密码
 * @author wangyi
 *
 */
public class RetrievePassActivity extends BaseActivity {

	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("找回密码");
	}
	
	
	@OnClick(R.id.btn_next)
	void next(View view){
		skip(ResetPassActivity.class);
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_retrieve_pass;
	}

}
