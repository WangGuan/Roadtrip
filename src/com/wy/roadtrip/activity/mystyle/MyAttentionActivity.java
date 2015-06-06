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
import com.froyo.commonjar.utils.Utils;
import com.froyo.commonjar.view.CustomListView;
import com.froyo.commonjar.view.CustomListView.OnLoadMoreListener;
import com.froyo.commonjar.view.CustomListView.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.MyAttentionAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.MyAttentionVo;
import com.wy.roadtrip.vo.ResponseVo;

/**
 * 
 * @Des: 我的关注
 * @author Rhino
 * @version V1.0
 * @created 2015年5月19日 上午11:50:49
 */
public class MyAttentionActivity extends BaseActivity {

	@ViewInject(R.id.ct_list)
	private CustomListView listView;

	private MyAttentionAdapter adapter;

	private int pageNum=1;
	
	@Override
	public void doBusiness() {
		
		TitleBar bar = new TitleBar(activity);
		bar.setTitle("关注");
		bar.showBack();
		
		adapter = new MyAttentionAdapter(new ArrayList<MyAttentionVo>(), activity,
				R.layout.item_my_attention);
		
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
				cancelFollow(vo.getUser().getUid(), vo.getUser().getNickname());
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
				SimpleUtils.buildUrl(activity, Const.MY_ATTENTION),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<MyAttentionVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"following"), MyAttentionVo.class);
							if (Utils.isEmpty(datas)) {
								toast("尚未关注任何人");
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
		PostRequest req = new PostRequest(activity, params, SimpleUtils.buildUrl(activity, Const.MY_ATTENTION),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<MyAttentionVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"following"), MyAttentionVo.class);
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

	private void cancelFollow(String id, final String name) {
		activity.showDialog();
		RequestQueue mQueue = Volley.newRequestQueue(activity);
		PostParams params = new PostParams();
		params.put("unfollow_uid", id);
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.CANCEL_ATTENTION),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						ResponseVo vo = GsonTools.getVo(obj.toString(),
								ResponseVo.class);
						if (vo.isSucess()) {
							activity.toast("成功取消关注" + name);
						} else {
							activity.toast("取消失败");
						}
					}

					@Override
					public void doFailed() {
						activity.toast("取消失败");
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
