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
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.vo.ResponseVo;
/**
 * 重新设置密码
 * @author wangyi
 *
 */
public class SetPassActivity extends BaseActivity {

	@ViewInject(R.id.et_pass)
	private EditText et_pass;
	
	@ViewInject(R.id.et_new_pass)
	private EditText et_new_pass;
	
	@ViewInject(R.id.et_repass)
	private EditText et_repass;
	
	private String phone;
	
	private String verify;
	
	@Override
	public void doBusiness() {
		TitleBar bar=new TitleBar(activity);
		bar.showBack();
		bar.setTitle("重置密码");
		phone=(String) getVo("0");
		verify=(String) getVo("1");
	}
	
	@OnClick(R.id.btn_submit)
	void submit(View view){
		if(Utils.isEmpty(et_pass.getText().toString().trim())){
			toast("请输入原密码");
			return;
		}
		if(Utils.isEmpty(et_new_pass.getText().toString().trim())||et_new_pass.getText().toString().trim().length()<6){
			toast("请输入合法格式的新密码");
			return;
		}
		if(Utils.isEmpty(et_repass.getText().toString().trim())){
			toast("请再次输入新密码");
			return;
		}
		if(!et_new_pass.getText().toString().trim().equals(et_repass.getText().toString().trim())){
			toast("两次输入的密码不同");
			et_repass.setText("");
			return;
		}
		
		showDialog();
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("password", et_repass.getText().toString());
		params.put("password2", et_repass.getText().toString());
		params.put("mobile", phone);
		params.put("verify_code", verify);
		PostRequest req = new PostRequest(activity, params, Const.RESET_PASS,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						ResponseVo vo=	GsonTools.getVo(obj.toString(), ResponseVo.class);
						toast(vo.getMsg());
					}
				});
		mQueue.add(req);
		mQueue.start();
	}
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_reset_pass;
	}

}
