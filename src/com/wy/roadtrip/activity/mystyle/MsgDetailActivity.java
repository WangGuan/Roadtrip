package com.wy.roadtrip.activity.mystyle;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.froyo.commonjar.activity.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.MsgDetailAdapter;
import com.wy.roadtrip.adapter.MyMsgAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.vo.MsgDetailVo;
import com.wy.roadtrip.vo.MyMsgVo;

/**
 * 
 * @Des: 我的消息详情
 * @author Rhino
 * @version V1.0
 * @created 2015年5月19日 上午11:50:49
 */
public class MsgDetailActivity extends BaseActivity {

	@ViewInject(R.id.list_view)
	private ListView list_view;

	private MsgDetailAdapter adapter;

	@Override
	public void doBusiness() {
		
		TitleBar bar = new TitleBar(activity);
		bar.setTitle("消息详情");
		bar.showBack();
		
		adapter = new MsgDetailAdapter(new ArrayList<MsgDetailVo>(), activity,
				R.layout.item_msg_detail);
		
		list_view.setAdapter(adapter);
		list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
		});
		for (int i = 0; i < 5; i++) {
			MsgDetailVo vo = new MsgDetailVo();
			if(i<3){
				vo.setContent("好呵呵呵你好呵呵呵你好呵呵呵 "+i);
				vo.setCreateTime(System.currentTimeMillis()+i*100000);
				vo.setName("王大爷");
				vo.setSend(true);
			}else{
				vo.setCreateTime(System.currentTimeMillis());
				vo.setName("王小爷");
				vo.setContent("好呵呵呵你好呵呵呵你好呵呵呵 好呵呵呵你好呵呵呵你好呵呵呵 好呵呵呵你好呵呵呵你好呵呵呵 好呵呵呵你好呵呵呵你好呵呵呵 好呵呵呵你好呵呵呵你好呵呵呵 "+i);
				vo.setSend(false);
			}
			adapter.addItem(vo, 0);
		}
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_msg_detail;
	}

}
