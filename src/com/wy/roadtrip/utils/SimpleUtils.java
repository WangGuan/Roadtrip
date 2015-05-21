package com.wy.roadtrip.utils;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.utils.SpUtil;
import com.froyo.commonjar.utils.Utils;
import com.wy.roadtrip.constant.Const;

/**
 * 
 * @Des: 简单工具类
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月19日 上午11:29:00
 */
public class SimpleUtils {

	public static String buildUrl(BaseActivity activity,String url){
		SpUtil sp=new SpUtil(activity);
		if(!isLogin(activity)){
			return "";
		}
		return url+"&auth="+sp.getStringValue(Const.AUTH)+"&uid="+sp.getStringValue(Const.UID);
	}
	
	public static boolean isLogin(BaseActivity activity){
		SpUtil sp=new SpUtil(activity);
		if(Utils.isEmpty(sp.getStringValue(Const.AUTH))){
			return false;
		}
		return true;
	}
}
