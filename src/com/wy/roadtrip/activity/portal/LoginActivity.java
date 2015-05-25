package com.wy.roadtrip.activity.portal;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.SpUtil;
import com.froyo.commonjar.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.activity.MainActivity;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.vo.ResponseVo;

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
		PostRequest req = new PostRequest(activity, params, Const.LOGIN,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {

						ResponseVo vo=GsonTools.getVo(obj.toString(), ResponseVo.class);
						if(vo.isSucess()){
							try {
								String auth=obj.getJSONObject("data").getString("auth");
								String uid=obj.getJSONObject("data").getJSONObject("userinfo").getString("uid");
								SpUtil sp=new SpUtil(activity);
								sp.setValue(Const.AUTH, auth);
								sp.setValue(Const.UID, uid);
								skip(MainActivity.class);
								finish();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}else{
							toast(vo.getMsg());
						}
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

	@OnClick(R.id.iv_weixin)
	void weixinLogin(View view){
		skip(MainActivity.class);
		finish();
	}
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_login;
	}

}
