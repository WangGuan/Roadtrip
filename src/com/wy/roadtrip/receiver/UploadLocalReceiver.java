package com.wy.roadtrip.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.froyo.commonjar.utils.SpUtil;
import com.froyo.commonjar.utils.Utils;
import com.wy.roadtrip.constant.Const;

public class UploadLocalReceiver extends BroadcastReceiver {

	private LocationManagerProxy mLocationManagerProxy;

	private void init(Context context) {
		// 初始化定位，只采用网络定位
		mLocationManagerProxy = LocationManagerProxy.getInstance(context);
		mLocationManagerProxy.setGpsEnable(true);
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用removeUpdates()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用destroy()方法
		// 其中如果间隔时间为-1，则定位只定一次,
		// 在单次定位情况下，定位无论成功与否，都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, -1, 30000,
				new AMapLocationListener() {

					@Override
					public void onStatusChanged(String arg0, int arg1,
							Bundle arg2) {

					}

					@Override
					public void onProviderEnabled(String arg0) {

					}

					@Override
					public void onProviderDisabled(String arg0) {

					}

					@Override
					public void onLocationChanged(Location arg0) {

					}

					@Override
					public void onLocationChanged(AMapLocation amapLocation) {
						if (amapLocation != null
								&& amapLocation.getAMapException()
										.getErrorCode() == 0) {
							System.out.println("当前地址："
									+ amapLocation.getAddress());
						} else {
							System.out.println("地位失败了");
						}
					}
				});

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		if (Const.ACTION_UPLOAD_CAR_LOCAL.equals(action)) {
			SpUtil sp=new SpUtil(context);
			if(!Utils.isEmpty(sp.getStringValue(Const.CAR_TEAM_ID))){
				init(context);
			}
		}
	}

}
