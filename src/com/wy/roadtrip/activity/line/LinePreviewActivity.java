package com.wy.roadtrip.activity.line;

import android.view.View;
import android.view.View.OnClickListener;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.BottomBar;
import com.wy.roadtrip.componet.TitleBar;
/**
 * 线路预览
 * @author wangyi
 *
 */
public class LinePreviewActivity extends BaseActivity {

	
	@Override
	public void doBusiness() {
		TitleBar bar =new TitleBar(activity);
		bar.showBack();
		bar.setTitle("线路预览");
		bar.showRightText(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				skip(LineEditActivity.class);
			}
		}, "线路编辑");
		
		BottomBar bottombar=new BottomBar(activity);
		bottombar.showTv1("快速导航", R.drawable.icon_act_active, new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toast("快速导航");
			}
		});
		
		bottombar.showTv2("发布线路", R.drawable.icon_act_active, new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						toast("发布线路");
					}
		});
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_line_preview;
	}

}
