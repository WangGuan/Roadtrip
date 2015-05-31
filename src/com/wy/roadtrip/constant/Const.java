package com.wy.roadtrip.constant;

public interface Const {
	
	public static final String DOMAIN="http://121.40.224.43/opencenter/";
	
	/**登录*/
	public static final String LOGIN=DOMAIN+"index.php?s=/ucenter/app/login";
	
	/**注册获取验证码*/
	public static final String VERIFY=DOMAIN+"index.php?s=/ucenter/app/get_verify";
	
	/**注册*/
	public static final String REGISTER=DOMAIN+"index.php?s=/ucenter/app/register";

	/**注册之后补充信息*/
	public static final String  SUPPLY_INFO=DOMAIN+"index.php?s=/ucenter/app/save_info";
	
	/**重置密码*/
	public static final String  RESET_PASS=DOMAIN+"index.php?s=/ucenter/app/reset_pwd";

	/**修改密码*/
	public static final String  ALTER_PASS=DOMAIN+"index.php?s=/ucenter/app/alterPwd";

	/**保存需要上传车队地理位置的车队ID*/
	public static final String  CAR_TEAM_ID="car_team_id";

	/**上传车队地理位置action*/
	public static final String  ACTION_UPLOAD_CAR_LOCAL="com.wy.roadtrip.upload_car_local";
	
	/**我的收藏-看点列表*/
	public static final String COLLECT_VIEW=DOMAIN+"index.php?s=/favorite/app/fav_focus_list";
	
	/**我的收藏-私享列表*/
	public static final String COLLECT_SHARE=DOMAIN+"index.php?s=/favorite/app/fav_own_share_list";
	
	/**我的收藏-照片列表*/
	public static final String COLLECT_PIC=DOMAIN+"index.php?s=/favorite/app/fav_pic_list";
	
	/**我的收藏-线路列表*/
	public static final String COLLECT_ROUTE=DOMAIN+"index.php?s=/favorite/app/fav_route_list";
	
	/**我的订单列表*/
	public static final String ORDER_LIST=DOMAIN+"index.php?s=/order/app/order_list";

	/**上传，修改头像图片*/
	public static final String ALTER_AVATAR=DOMAIN+"index.php?s=/ucenter/app/alter_avatar";

	/**用户登录id*/
	public static final String UID="com.wy.roadtrip.uid";
	
	/**用户登录auth*/
	public static final String AUTH="com.wy.roadtrip.auth";

	public static final String USERNAME="com.wy.roadtrip.username";
	
	public static final int REQUEST_CODE_IMAGE_CAPTURE = 0;
	
	public static final int REQUEST_CODE_IMAGE_SELECTE = 1;
	
	public static final int REQUEST_CODE_IMAGE_CROP = 2;
}
