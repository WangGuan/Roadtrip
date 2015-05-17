package com.wy.roadtrip.constant;

public interface Const {
	
	public static final String DOMAIN="http://opencenter.mmm.com/";
	
	/**登录*/
	public static final String LOGIN=DOMAIN+"index.php?s=/ucenter/app/login";
	
	/**注册获取验证码*/
	public static final String VERIFY=DOMAIN+"index.php?s=/ucenter/app/get_verify";
	
	/**注册*/
	public static final String REGISTER=DOMAIN+"index.php?s=/ucenter/app/register";

	/**注册之后补充信息*/
	public static final String  SUPPLY_INFO=DOMAIN+"index.php?s=/ucenter/app/register";
	
	/**找回密码*/
	public static final String  RETRIEVE_PASS=DOMAIN+"index.php?s=/ucenter/app/get_verify";

	/**重置密码*/
	public static final String  RESET_PASS=DOMAIN+"index.php?s=/ucenter/app/reset_pwd";
}
