package com.wy.roadtrip.activity.line;

import android.view.View;
import android.view.View.OnClickListener;

import com.froyo.commonjar.activity.BaseActivity;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.BottomBar;
import com.wy.roadtrip.componet.TitleBar;

/**
 * 编辑线路
 * @author wangyi
 *
 */
public class LineEditActivity extends BaseActivity {
	
		@Override
		public void doBusiness() {
			TitleBar bar =new TitleBar(activity);
			bar.showBack();
			bar.setTitle("编辑线路");
			bar.showRightText(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					toast("编辑");
				}
			}, "保存");
			
			
			BottomBar bottombar=new BottomBar(activity);
			bottombar.showTv1("添加看点", R.drawable.icon_act_active, new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					toast("添加看点");
				}
			});
			
			bottombar.showTv2("添加照片", R.drawable.icon_act_active, new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							toast("添加照片");
						}
			});
			bottombar.showTv3("线路设置", R.drawable.icon_act_active, new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					toast("线路设置");
				}
			});
			
		}
		
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_line_edit;
	}

}
