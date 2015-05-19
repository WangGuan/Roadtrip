package com.wy.roadtrip.activity.mystyle;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.froyo.commonjar.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.MyMsgAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.vo.MyMsgVo;

/**
 * 
 * @Des: 我的消息列表
 * @author Rhino
 * @version V1.0
 * @created 2015年5月19日 上午11:50:49
 */
public class MyMsgActivity extends BaseActivity {

	@ViewInject(R.id.list_view)
	private ListView list_view;

	private MyMsgAdapter adapter;

	@Override
	public void doBusiness() {
		
		TitleBar bar = new TitleBar(activity);
		bar.setTitle("我的消息");
		bar.showBack();
		
		adapter = new MyMsgAdapter(new ArrayList<MyMsgVo>(), activity,
				R.layout.item_my_msg);
		
		list_view.setAdapter(adapter);
		list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				skip(MsgDetailActivity.class);
			}
		});
		for (int i = 0; i < 5; i++) {
			MyMsgVo vo = new MyMsgVo();
			adapter.addItem(vo, 0);
		}
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_my_msg;
	}

}
