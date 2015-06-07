package com.wy.roadtrip.activity.photo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.froyo.commonjar.view.CustomListView;
import com.froyo.commonjar.view.CustomListView.OnLoadMoreListener;
import com.froyo.commonjar.view.CustomListView.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.MyAttentionAdapter;
import com.wy.roadtrip.adapter.MyPhotoAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.MyAttentionVo;
import com.wy.roadtrip.vo.MyPhotoVo;
/**
 * 
 * @Des: 我的相册
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月6日 下午3:25:22
 */
public class PhotoActivity extends BaseActivity {
	@ViewInject(R.id.ct_list)
	private CustomListView listView;
	
	private MyPhotoAdapter adapter;

	private int pageNum=1;
	
	@Override
	public void doBusiness() {
		TitleBar bar =new TitleBar(activity);
		bar.showBack();
		bar.setTitle("我的相册");
		
		adapter = new MyPhotoAdapter(new ArrayList<MyPhotoVo>(), activity,
				R.layout.item_my_photo);
		
		listView.setAdapter(adapter);
		
		listView.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				pullToRefresh();
			}
		});		
		
		listView.setOnLoadListener(new OnLoadMoreListener() {
			
			@Override
			public void onLoadMore() {
				loadMore();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				MyAttentionVo vo=(MyAttentionVo) arg0.getAdapter().getItem(arg2);
			}
		});
		showDialog();
		pullToRefresh();
	}
	
	
	private void pullToRefresh(){
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("page", 1+"");
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.MY_PHOTO_LIST),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						System.out.println("obj:"+obj);
						try {
							List<MyPhotoVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"list"), MyPhotoVo.class);
							if (Utils.isEmpty(datas)) {
								toast("尚未上传照片");
							} else {
								adapter.removeAll();
								adapter.addItems(datas);
								int total_page= obj.getJSONObject("data").getInt(
										"total_page");
								if(total_page<2){
									listView.setHasNoMoreData();	
								}else{
									pageNum=2;
									listView.setCanLoadMore(true);
								}
							}
						} catch (Exception e) {
						}
						listView.onRefreshComplete();
					}

					@Override
					public void doFailed() {
						toast("获取数据失败");
						listView.onRefreshComplete();
					}
				});
		mQueue.add(req);
		mQueue.start();
	}
	
	private void loadMore() {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("page", pageNum+"");
		PostRequest req = new PostRequest(activity, params, SimpleUtils.buildUrl(activity, Const.MY_PHOTO_LIST),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<MyPhotoVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"list"), MyPhotoVo.class);
							int total_page= obj.getJSONObject("data").getInt(
									"total_page");
							if (!Utils.isEmpty(datas)) {
								pageNum++;
								adapter.addItems(datas);
								if (total_page < pageNum) {
									listView.setHasNoMoreData();
								} else {
									listView.setAutoLoadMore(true);
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						listView.onLoadMoreComplete();
					}

					@Override
					public void doFailed() {
						listView.onLoadMoreComplete();
						listView.setAutoLoadMore(false);
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_photo;
	}

}
