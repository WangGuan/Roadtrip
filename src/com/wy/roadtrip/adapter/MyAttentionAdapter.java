package com.wy.roadtrip.adapter;

import java.util.List;

import android.view.View;
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
import com.wy.roadtrip.R;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.vo.MyAttentionVo;
/**
 * 
 * @Des: 我的关注列表
 * @author Rhino 
 * @version V1.0 
 * @created  2015年6月6日 下午4:40:02
 */
public class MyAttentionAdapter extends SimpleAdapter<MyAttentionVo> {

	private RequestQueue mQueue;

	private ImageLoader imageLoader;
	
	public MyAttentionAdapter(List<MyAttentionVo> data, BaseActivity activity, int layoutId) {
		super(data, activity, layoutId, ViewHolder.class, R.id.class);
		mQueue = Volley.newRequestQueue(activity);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}

	@Override
	public void doExtra(View convertView, MyAttentionVo item, int position) {
		ViewHolder h = (ViewHolder) holder;
		h.iv_header.setImageUrl(Const.IMAGE_DOMAIN+item.getUser().getAvatar128(), imageLoader);
		h.tv_title.setText(item.getUser().getNickname());
		h.tv_content.setText(item.getUser().getSignature());
		if("1".equals(item.getUser().getSex())){
			h.iv_sex.setImageResource(R.drawable.icon_female);
		}else{
			h.iv_sex.setImageResource(R.drawable.icon_male);
		}
	}

	public static class ViewHolder {
		RelativeLayout rl_content;
		
		TextView tv_title;

		TextView tv_content;
		
		ImageView iv_sex;
		
		NetworkImageView iv_header;
	}
}
