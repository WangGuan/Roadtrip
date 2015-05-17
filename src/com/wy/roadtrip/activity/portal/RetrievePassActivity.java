package com.wy.roadtrip.activity.portal;

import org.json.JSONObject;

import android.view.View;
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
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
/**
 * 找回密码
 * @author wangyi
 *
 */
public class RetrievePassActivity extends BaseActivity {

	
	@ViewInject(R.id.et_verify)
	private EditText et_verify;

	@ViewInject(R.id.et_phone)
	private EditText et_phone;
	
	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("找回密码");
	}
	
	
	@OnClick(R.id.btn_submit)
	void next(View view){
		if (Utils.isEmpty(et_phone.getText().toString())) {
			toast("请填写手机号");
			return;
		}
		if (Utils.isEmpty(et_verify.getText().toString())) {
			toast("请填写验证码");
			return;
		}
		showDialog("正在找回密码 ……");
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("mobile", et_phone.getText().toString());
		params.put("verify_code", et_verify.getText().toString());
		params.put("is_find_pwd", "1");
		PostRequest req = new PostRequest(activity, params, Const.RETRIEVE_PASS,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {

					}
				});
		mQueue.add(req);
		mQueue.start();
	}
	
	@OnClick(R.id.tv_code)
	void getVerifyCode(View view){
		if (Utils.isEmpty(et_phone.getText().toString())) {
			toast("请填写手机号");
			return;
		}

		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("mobile", et_phone.getText().toString());
		PostRequest req = new PostRequest(activity, params, Const.VERIFY,
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
		return R.layout.activity_retrieve_pass;
	}

}
