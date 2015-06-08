package com.wy.roadtrip.activity.mystyle;

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
import com.froyo.commonjar.utils.SpUtil;
import com.froyo.commonjar.utils.Utils;
import com.froyo.commonjar.view.CustomListView;
import com.froyo.commonjar.view.CustomListView.OnLoadMoreListener;
import com.froyo.commonjar.view.CustomListView.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.MyFansAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.MyAttentionVo;

/**
 * 
 * @Des: 我的粉丝
 * @author Rhino
 * @version V1.0
 * @created 2015年5月19日 上午11:50:49
 */
public class MyFansActivity extends BaseActivity {

	@ViewInject(R.id.ct_list)
	private CustomListView listView;

	private MyFansAdapter adapter;

	private int pageNum = 1;

	@Override
	public void doBusiness() {

		TitleBar bar = new TitleBar(activity);
		bar.setTitle("粉丝");
		bar.showBack();

		adapter = new MyFansAdapter(new ArrayList<MyAttentionVo>(), activity,
				R.layout.item_my_fans);

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
			}
		});
		showDialog();
		pullToRefresh();
	}

	private void pullToRefresh(){
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("page", 1+"");
		SpUtil sp=new SpUtil(activity);
		params.put("tuid", sp.getStringValue(Const.UID));
		
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.MY_FANS),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<MyAttentionVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"fans"), MyAttentionVo.class);
							if (Utils.isEmpty(datas)) {
								toast("尚未有粉丝");
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
		PostRequest req = new PostRequest(activity, params, SimpleUtils.buildUrl(activity, Const.MY_FANS),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<MyAttentionVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"fans"), MyAttentionVo.class);
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
		return R.layout.activity_my_attention;
	}

}
