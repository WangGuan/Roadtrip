package com.wy.roadtrip.activity.portal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.froyo.commonjar.activity.BaseActivity;
import com.google.zxing.WriterException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.zxing.CaptureActivity;
import com.wy.roadtrip.zxing.EncodingHandler;

/**
 * 设置
 * 
 * @author wangyi
 *
 */
public class SettingsActivity extends BaseActivity {

	@ViewInject(R.id.et_test)
	private ImageView et_test;
	
	@Override
	public void doBusiness() {
		TitleBar bar = new TitleBar(activity);
		bar.showBack();
		bar.setTitle("设置");
	}

	@OnClick(R.id.tv_change_pass)
	void changePass(View view) {
		skip(ResetPassActivity.class);
	}

	@OnClick(R.id.tv_zxing)
	void zxing(View view) {
		Intent openCameraIntent = new Intent(activity, CaptureActivity.class);
		startActivityForResult(openCameraIntent, 0);
	}
	
	@OnClick(R.id.btn_exit)
	void createBit(View view){
		Bitmap qrCodeBitmap;
		try {
			qrCodeBitmap = EncodingHandler.createQRCode("wwww.baidu.com", 350);
			et_test.setImageBitmap(qrCodeBitmap);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_settings;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			toast(scanResult);
		}
	}
}
