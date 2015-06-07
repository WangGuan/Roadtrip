package com.wy.roadtrip.constant;

public interface Const {
	
	public static final String DOMAIN="http://121.40.224.43/opencenter/";

	public static final String IMAGE_DOMAIN="http://121.40.224.43/";
	
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

	/**我的车队*/
	public static final String MY_CAR_TEAM=DOMAIN+"index.php?s=/fleet/app/all_team";

	/**上传个人位置*/
	public static final String UPLOAD_LOCAL=DOMAIN+"index.php?s=/fleet/app/upload_loc";
	
	/**我的收藏-线路列表*/
	public static final String COLLECT_ROUTE=DOMAIN+"index.php?s=/favorite/app/fav_route_list";
	
	/**我的订单列表*/
	public static final String ORDER_LIST=DOMAIN+"index.php?s=/order/app/order_list";

	/**上传，修改头像图片*/
	public static final String ALTER_AVATAR=DOMAIN+"index.php?s=/ucenter/app/alter_avatar";

	/**我的关注*/
	public static final String MY_ATTENTION =DOMAIN+"index.php?s=/ucenter/app/following";

	/**我的粉丝*/
	public static final String MY_FANS =DOMAIN+"index.php?s=/ucenter/app/fans";

	/**添加关注*/
	public static final String ADD_ATTENTION =DOMAIN+"index.php?s=/ucenter/app/follow";

	/**取消关注*/
	public static final String CANCEL_ATTENTION =DOMAIN+"index.php?s=/ucenter/app/unfollow";

	/**我的照片列表*/
	public static final String MY_PHOTO_LIST =DOMAIN+"index.php?s=/interaction/appAdmin/pic_list";

	/**用户登录id*/
	public static final String UID="com.wy.roadtrip.uid";
	
	/**用户登录auth*/
	public static final String AUTH="com.wy.roadtrip.auth";

	public static final String USERNAME="com.wy.roadtrip.username";
	
	public static final int REQUEST_CODE_IMAGE_CAPTURE = 0;
	
	public static final int REQUEST_CODE_IMAGE_SELECTE = 1;
	
	public static final int REQUEST_CODE_IMAGE_CROP = 2;
}
