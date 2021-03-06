package com.wy.roadtrip.fragment;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.froyo.commonjar.componet.BadgeView;
import com.froyo.commonjar.fragment.BaseFragment;
import com.froyo.commonjar.utils.SpUtil;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wy.roadtrip.R;
import com.wy.roadtrip.activity.car.CarTeamActivity;
import com.wy.roadtrip.activity.collect.CollectActivity;
import com.wy.roadtrip.activity.line.LineActivity;
import com.wy.roadtrip.activity.map.OfflineMapActivity;
import com.wy.roadtrip.activity.menu.MenuActivity;
import com.wy.roadtrip.activity.mystyle.MyAttentionActivity;
import com.wy.roadtrip.activity.mystyle.MyFansActivity;
import com.wy.roadtrip.activity.mystyle.MyMsgActivity;
import com.wy.roadtrip.activity.mystyle.MystyleActivity;
import com.wy.roadtrip.activity.photo.MyTestActivity;
import com.wy.roadtrip.activity.photo.PhotoActivity;
import com.wy.roadtrip.constant.Const;

/**
 * 
 * @Des: 首页
 * @author Rhino 
 * @version V1.0 
 * @created  2015年5月6日 上午11:43:54
 */
public class FragmentHome extends BaseFragment {
	
	@ViewInject(R.id.tv_msg)
	private TextView tv_msg;
	
	@ViewInject(R.id.tv_name)
	private TextView tv_name;
	
	@Override
	protected void setListener() {
		   BadgeView tips = new BadgeView(activity, tv_msg);
           tips.setText("2");
           tips.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
           tips.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
           tips.toggle();
           
           SpUtil sp=new SpUtil(activity);
           tv_name.setText(sp.getStringValue(Const.USERNAME));
	}
	
	@OnClick(R.id.tv_msg)
	void showMsg(View view){
		activity.skip(MyMsgActivity.class);
	}
	
	@OnClick(R.id.rl_line)
	void line(View view) {
		activity.skip(LineActivity.class);
	}

	@OnClick(R.id.rl_collect)
	void collect(View view) {
		activity.skip(CollectActivity.class);
	}

	@OnClick(R.id.rl_car)
	void car(View view) {
		activity.skip(CarTeamActivity.class);
	}

	@OnClick(R.id.rl_photo)
	void photo(View view) {
		activity.skip(PhotoActivity.class);
	}

	@OnClick(R.id.rl_mystyle)
	void mystyle(View view) {
		activity.skip(MystyleActivity.class);
	}

	@OnClick(R.id.rl_menu)
	void menu(View view) {
		activity.skip(MenuActivity.class);
	}

	@OnClick(R.id.rl_map)
	void offlineMap(View view) {
		activity.skip(MyTestActivity.class);
//		activity.skip(OfflineMapActivity.class);
	}

	@OnClick(R.id.tv_follow)
	void myAttention(View view){
		activity.skip(MyAttentionActivity.class);
	}
	 
	@OnClick(R.id.tv_fans)
	void myFans(View view){
		activity.skip(MyFansActivity.class);
	}
	@Override
	protected int setLayoutResID() {
		return R.layout.fragment_home;
	}

}
