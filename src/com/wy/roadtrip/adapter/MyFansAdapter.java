package com.wy.roadtrip.adapter;

import java.util.List;

import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.froyo.commonjar.network.BitmapCache;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.wy.roadtrip.R;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.MyAttentionVo;
import com.wy.roadtrip.vo.ResponseVo;

/**
 * 
 * @Des: 我的关注列表
 * @author Rhino
 * @version V1.0
 * @created 2015年6月6日 下午4:40:02
 */
public class MyFansAdapter extends SimpleAdapter<MyAttentionVo> {

	private RequestQueue mQueue;

	private ImageLoader imageLoader;

	public MyFansAdapter(List<MyAttentionVo> data, BaseActivity activity,
			int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
		mQueue = Volley.newRequestQueue(activity);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void doExtra(View convertView, final MyAttentionVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.iv_header.setImageUrl(Const.IMAGE_DOMAIN
				+ item.getUser().getAvatar128(), imageLoader);
		h.tv_title.setText(item.getUser().getNickname());
		if ("1".equals(item.getUser().getSex())) {
			h.iv_sex.setImageResource(R.drawable.icon_female);
		} else {
			h.iv_sex.setImageResource(R.drawable.icon_male);
		}
		h.tv_attention.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addFollow(item.getUser().getUid(), item.getUser().getNickname());
			}
		});
	}

	public static class ViewHolder {
		RelativeLayout rl_content;

		TextView tv_title;

		TextView tv_attention;

		ImageView iv_sex;

		NetworkImageView iv_header;
	}

	private void addFollow(String id, final String name) {
		activity.showDialog();
		PostParams params = new PostParams();
		params.put("follow_uid", id);
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.ADD_ATTENTION),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						ResponseVo vo = GsonTools.getVo(obj.toString(),
								ResponseVo.class);
						if (vo.isSucess()) {
							activity.toast("成功关注" + name);
						} else {
							activity.toast("关注失败");
						}
					}

					@Override
					public void doFailed() {
						activity.toast("关注失败");
					}
				});
		mQueue.add(req);
		mQueue.start();
	}
}
