package com.wy.roadtrip.constant;

public interface Const {
	
	public static final String DOMAIN="http://106.39.56.134/opencenter/";
	
	/**登录*/
	public static final String LOGIN=DOMAIN+"index.php?s=/ucenter/app/login";
	
	/**注册获取验证码*/
	public static final String VERIFY=DOMAIN+"index.php?s=/ucenter/app/get_verify";
	
	/**注册*/
	public static final String REGISTER=DOMAIN+"index.php?s=/ucenter/app/register";

	/**注册之后补充信息*/
	public static final String  SUPPLY_INFO=DOMAIN+"index.php?s=/ucenter/app/register";
	
	/**重置密码*/
	public static final String  RESET_PASS=DOMAIN+"index.php?s=/ucenter/app/reset_pwd";

	/**修改密码*/
	public static final String  ALTER_PASS=DOMAIN+"index.php?s=/ucenter/app/alterPwd";

	/**保存需要上传车队地理位置的车队ID*/
	public static final String  CAR_TEAM_ID="car_team_id";

	/**上传车队地理位置action*/
	public static final String  ACTION_UPLOAD_CAR_LOCAL="com.wy.roadtrip.upload_car_local";
	
	/**用户登录id*/
	public static final String UID="com.wy.roadtrip.uid";
	
	/**用户登录auth*/
	public static final String AUTH="com.wy.roadtrip.auth";
	
	public static final int REQUEST_CODE_IMAGE_CAPTURE = 0;
	
	public static final int REQUEST_CODE_IMAGE_SELECTE = 1;
	
	public static final int REQUEST_CODE_IMAGE_CROP = 2;
	
	
}
