package com.wy.roadtrip.activity.portal;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.vo.ResponseVo;

/**
 * 
 * @Des: 注册
 * @author Rhino
 * @version V1.0
 * @created 2015年5月7日 上午10:00:32
 */
public class RegisterActivity extends BaseActivity {

	@ViewInject(R.id.et_phone)
	private EditText et_phone;

	@ViewInject(R.id.et_verify)
	private EditText et_verify;

	@ViewInject(R.id.et_pass)
	private EditText et_pass;

	@ViewInject(R.id.et_repass)
	private EditText et_repass;

	@ViewInject(R.id.et_invite)
	private EditText et_invite;

	@ViewInject(R.id.cb_agree)
	private CheckBox cb_agree;

	@ViewInject(R.id.tv_code)
	private TextView tv_code;
	
	private TimeCount time;
	
	@Override
	public void doBusiness() {
		TitleBar bar = new TitleBar(activity);
		bar.showBack();
		bar.setTitle("注册");
		
		time = new TimeCount(60000, 1000);
	}

	@OnClick(R.id.btn_submit)
	void submit(View view) {
		if (!Utils.isMobileNum(et_phone.getText().toString().trim())) {
			toast("请输入正确的手机号");
			return;
		}
		if (Utils.isEmpty(et_verify.getText().toString().trim())) {
			toast("请输入验证码");
			return;
		}
		if (Utils.isEmpty(et_pass.getText().toString().trim())) {
			toast("请输入密码");
			return;
		}
		if (Utils.isEmpty(et_repass.getText().toString().trim())) {
			toast("请再次输入密码");
			return;
		}

		if (!et_repass.getText().toString().trim()
				.equals(et_pass.getText().toString().trim())) {
			toast("两次输入的密码不同");
			et_repass.setText("");
			return;
		}
		if (!cb_agree.isChecked()) {
			toast("请同意爱自驾注册协议");
			return;
		}

		showDialog("正在注册……");
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("mobile", et_phone.getText().toString());
		params.put("verify_code", et_verify.getText().toString());
		params.put("password", et_pass.getText().toString());
		params.put("password2", et_pass.getText().toString());
		if (!Utils.isEmpty(et_invite.getText().toString().trim())) {
			params.put("invite_code", et_invite.getText().toString());
		}
		PostRequest req = new PostRequest(activity, params, Const.REGISTER,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						ResponseVo vo=GsonTools.getVo(obj.toString(), ResponseVo.class);
						if(vo.isSucess()){
							try {
								String auth=obj.getJSONObject("data").getString("auth");
								SpUtil sp=new SpUtil(activity);
								sp.setValue(Const.AUTH, auth);
								skip(AddInfoActivity.class);
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

	@OnClick(R.id.tv_code)
	void getVerifyCode(View view) {
		if (Utils.isEmpty(et_phone.getText().toString())) {
			toast("请填写手机号");
			return;
		}
		
		time.start();
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("mobile", et_phone.getText().toString());
		PostRequest req = new PostRequest(activity, params, Const.VERIFY,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						ResponseVo vo=GsonTools.getVo(obj.toString(), ResponseVo.class);
						toast(vo.getMsg());
						System.out.println("ob1:"+obj);
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	@OnClick(R.id.tv_protocol)
	void showProtocol(View view) {
		skip(ProtocolActivity.class);
	}

	
	class TimeCount extends CountDownTimer {

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			tv_code.setTextColor(Color.parseColor("#999999"));
			tv_code.setClickable(false);
			tv_code.setText(millisUntilFinished / 1000 + "秒后重新发送");
		}

		@Override
		public void onFinish() {
			tv_code.setText("重新获取验证码");
			tv_code.setClickable(true);
			tv_code.setTextColor(Color.parseColor("#50BBDB"));
		}
	}
	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_register;
	}

}
