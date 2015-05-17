package com.wy.roadtrip.activity.portal;

import org.json.JSONObject;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;

/**
 * 注册第二步，设置个人信息
 * 
 * @author wangyi
 * 
 */
public class AddInfoActivity extends BaseActivity {

	@ViewInject(R.id.et_nickname)
	private EditText et_nickname;

	@ViewInject(R.id.cb_male)
	private CheckBox cb_male;

	@ViewInject(R.id.cb_female)
	private CheckBox cb_female;

	@Override
	public void doBusiness() {
		TitleBar bar = new TitleBar(activity);
		bar.showBack();
		bar.setTitle("填写信息");
		cb_male.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					cb_female.setChecked(false);
				} else {
					cb_female.setChecked(true);
				}
			}
		});
		cb_female.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					cb_male.setChecked(false);
				} else {
					cb_male.setChecked(true);
				}
			}
		});
	}

	@OnClick(R.id.iv_avator)
	void selectPhoto(View view) {
		toast("选择照片");
	}

	@OnClick(R.id.btn_submit)
	void submit(View view) {
		if (Utils.isEmpty(et_nickname.getText().toString())) {
			toast("请填写昵称");
			return;
		}

		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("nickname", et_nickname.getText().toString());

		if (cb_female.isChecked()) {
			params.put("sex", "2");
		} else {
			params.put("sex", "1");
		}

		PostRequest req = new PostRequest(activity, params, Const.SUPPLY_INFO,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {

					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_add_info;
	}

}
