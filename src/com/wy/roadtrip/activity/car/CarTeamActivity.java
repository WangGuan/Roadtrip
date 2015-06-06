package com.wy.roadtrip.activity.car;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.CarTeamAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.CarTeamVo;

/**
 * 
 * @Des: 我的车队
 * @author Rhino
 * @version V1.0
 * @created 2015年5月6日 下午3:25:22
 */
public class CarTeamActivity extends BaseActivity {

	@ViewInject(R.id.lv_list)
	private ListView listView;

	private CarTeamAdapter adapter;

	@Override
	public void doBusiness() {
		TitleBar bar = new TitleBar(activity);
		bar.showBack();
		bar.setTitle("我的车队");

		adapter = new CarTeamAdapter(new ArrayList<CarTeamVo>(), activity,
				R.layout.item_car_team);
		listView.setAdapter(adapter);

		showDialog();
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.MY_CAR_TEAM),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<CarTeamVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"list"), CarTeamVo.class);
							if (Utils.isEmpty(datas)) {
								toast("尚未加入任何车队");
							} else {
								adapter.addItems(datas);
							}
						} catch (Exception e) {
							e.printStackTrace();
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
		return R.layout.activity_car;
	}

}
