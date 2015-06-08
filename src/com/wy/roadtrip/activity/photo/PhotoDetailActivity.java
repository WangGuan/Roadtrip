package com.wy.roadtrip.activity.photo;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.GetRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.froyo.commonjar.view.CustomListView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.CollectPhotoAdapter;
import com.wy.roadtrip.adapter.MyPhotoDetailAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.CollectPhotoVo;
import com.wy.roadtrip.vo.MyPhotoVo;
import com.wy.roadtrip.vo.MyPhotoVo.Pic;
import com.wy.roadtrip.vo.ResponseVo;

/**
 * 我的相册详情
 * 
 * @author wangyi
 * 
 */
public class PhotoDetailActivity extends BaseActivity {

	private MyPhotoDetailAdapter adapter;

	@ViewInject(R.id.lv_page_list)
	private CustomListView listView;

	@Override
	public void doBusiness() {
		TitleBar bar =new TitleBar(activity);
		bar.showBack();
		bar.setTitle("内容详情");
		bar.showRightText(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		}, "管理");
		
		adapter = new MyPhotoDetailAdapter(new ArrayList<Pic>(),
				activity, R.layout.item_collect_photo);
		listView.setAdapter(adapter);
		
		String id=(String) getVo("0");
		showDialog();
		RequestQueue mQueue = Volley.newRequestQueue(this);
		GetRequest req=new GetRequest(activity, SimpleUtils.buildUrl(activity, Const.MY_PHOTO_DETAIL)+"&id="+id, new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						ResponseVo vo=GsonTools.getVo(obj.toString(), ResponseVo.class);
						if(vo.isSucess()){
							try {
								MyPhotoVo photo=GsonTools.getVo(obj.getJSONObject("data").getJSONObject("detail").toString(), MyPhotoVo.class);
								if(!Utils.isEmpty(photo.getPics())){
									adapter.addItems(photo.getPics());
								}
										
							} catch (JSONException e) {
							}
						}else{
							toast(vo.getMsg());
						}
					}

					@Override
					public void doFailed() {
						toast("获取数据失败");
					}
				});
		
		mQueue.add(req);
		mQueue.start();
		
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_photo_detail;
	}

}
