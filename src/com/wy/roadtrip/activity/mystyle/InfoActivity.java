package com.wy.roadtrip.activity.mystyle;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.GetRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.InfoVo;
import com.wy.roadtrip.vo.ResponseVo;

/**
 * 个人中心
 * 
 * @author wangyi
 * 
 */
public class InfoActivity extends BaseActivity {

	@ViewInject(R.id.btn_msg)
	private Button btn_msg;

	@ViewInject(R.id.btn_followed)
	private Button btn_followed;

	@ViewInject(R.id.tv_name)
	private TextView tv_name;

	@ViewInject(R.id.tv_follow)
	private TextView tv_follow;

	@ViewInject(R.id.tv_fans)
	private TextView tv_fans;

	@ViewInject(R.id.iv_avatar)
	private ImageView iv_avatar;

	RequestQueue mQueue;

	@Override
	public void doBusiness() {
		TitleBar bar = new TitleBar(activity);
		bar.setTitle("个人中心");
		bar.showBack();
		bar.showRightText(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		}, "举报");
		mQueue = Volley.newRequestQueue(this);

		String tuid = (String) getVo("0");
		queryInfo(tuid);
	}

	private void queryInfo(String tuid) {
		showDialog();
		GetRequest req = new GetRequest(activity, SimpleUtils.buildUrl(
				activity, Const.MY_INFO) + "&tuid=" + tuid, new RespListener(
				activity) {

			@Override
			public void getResp(JSONObject obj) {
				ResponseVo vo = GsonTools.getVo(obj.toString(),
						ResponseVo.class);
				if (vo.isSucess()) {
					try {
						InfoVo info = GsonTools.getVo(obj.getJSONObject("data")
								.getJSONObject("user_info").toString(),
								InfoVo.class);
						if (info.isFollowed()) {
							btn_followed.setText("已关注");
						} else {
							btn_followed.setText("关注");
						}
						tv_name.setText(info.getNickname());
						tv_follow.setText("关注  \n " + info.getFollowing());
						tv_fans.setText("粉丝  \n " + info.getFans());
						showAvatar(info.getAvatar128());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					toast(vo.getMsg());
				}
			}

			@Override
			public void doFailed() {
			}
		});

		mQueue.add(req);
		mQueue.start();
	}

	private void showAvatar(String url) {
		ImageRequest req = new ImageRequest(url,
				new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap arg0) {
						if(arg0!=null){
							iv_avatar.setImageBitmap(Utils.getRoundedCornerBitmap(arg0));
						}
					}
				}, 300, 300, Config.ARGB_8888, null);
		mQueue.add(req);
		mQueue.start();
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_info;
	}

}
