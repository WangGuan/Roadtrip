package com.wy.roadtrip.activity.portal;

import java.io.File;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.InitUtil;
import com.froyo.commonjar.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;

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
	
	@ViewInject(R.id.iv_avator)
	private ImageView iv_avator;

	private File mCurrentPhotoFile;

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
		showChoiceDialog();
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

		PostRequest req = new PostRequest(activity, params, SimpleUtils.buildUrl(activity, Const.SUPPLY_INFO),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						System.out.println("xx:"+obj);
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	private void showChoiceDialog() {
		new AlertDialog.Builder(activity)
				.setTitle("图片选项")
				.setItems(new String[] { "手机拍照", "手机相册" },
						new OnMyOnClickListener()).show();
	}

	protected class OnMyOnClickListener implements
			DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case 0:
				// 从摄像头拍照取头像
				mCurrentPhotoFile = new File(
						InitUtil.getImageCachePath(activity), "temp.png");
				Intent it_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				it_camera.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(mCurrentPhotoFile));
				startActivityForResult(it_camera,
						Const.REQUEST_CODE_IMAGE_CAPTURE);
				break;
			case 1:
				Intent it_photo = new Intent(Intent.ACTION_PICK, null);
				it_photo.setDataAndType(
						MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
				// 跳转至系统功能
				startActivityForResult(it_photo,
						Const.REQUEST_CODE_IMAGE_SELECTE);
				break;
			default:
				break;
			}
		}
	}

	/** 获取调用摄像头以及相册返回数据 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			super.onActivityResult(requestCode, resultCode, data);
			// ==========摄像头===========
			if (requestCode == Const.REQUEST_CODE_IMAGE_CAPTURE&&resultCode==Activity.RESULT_OK) {
				if (mCurrentPhotoFile != null) {
					// 方法1：读取缓存图片
					startPhotoZoom(Uri.fromFile(mCurrentPhotoFile));
				}
				// ==========相册============
			} else if (requestCode == Const.REQUEST_CODE_IMAGE_SELECTE) {
				startPhotoZoom(data.getData());
			} else if (requestCode == Const.REQUEST_CODE_IMAGE_CROP) {
				if (data != null) {
					Bundle extras = data.getExtras();
					if (extras == null) {
						return;
					}
					Bitmap photo = extras.getParcelable("data");
					Bitmap bitmap = Utils.getRoundedCornerBitmap(photo);
					iv_avator.setImageBitmap(bitmap);
					doUpLoadAvator();
				}

			}
		} catch (Exception e) {
		}
	}

	private void doUpLoadAvator() {
		
	}

	/** 缩放拍摄图片 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 300);
		intent.putExtra("outputY", 300);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, Const.REQUEST_CODE_IMAGE_CROP);
	}

	
	@Override
	protected int setLayoutResID() {
		return R.layout.activity_add_info;
	}

}
