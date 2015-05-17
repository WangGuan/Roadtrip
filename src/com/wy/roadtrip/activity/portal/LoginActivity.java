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
 * 
 * @Des: 登录
 * @author Rhino
 * @version V1.0
 * @created 2015年5月7日 上午10:00:32
 */
public class LoginActivity extends BaseActivity {

	@ViewInject(R.id.et_name)
	private EditText et_name;

	@ViewInject(R.id.et_pass)
	private EditText et_pass;

	@Override
	public void doBusiness() {
		TitleBar bar = new TitleBar(activity);
		bar.setTitle("登录");
	}

	@OnClick(R.id.btn_login)
	void login(View view) {
		if(Utils.isEmpty(et_name.getText().toString())){
			toast("请填写手机号/邮箱");
			return;
		}
		if(Utils.isEmpty(et_pass.getText().toString())){
			toast("请填写密码");
			return;
		}
		
		showDialog("努力登录中……");
		
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("username", et_name.getText().toString());
		params.put("password", et_pass.getText().toString());
		PostRequest req = new PostRequest(activity, params, Const.DOMAIN,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	@OnClick(R.id.tv_register)
	void register(View view) {
		skip(RegisterActivity.class);
	}

	@OnClick(R.id.tv_forget_pass)
	void retrievePass(View view) {
		skip(RetrievePassActivity.class);
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_login;
	}

}
