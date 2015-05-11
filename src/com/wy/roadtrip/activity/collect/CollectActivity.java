package com.wy.roadtrip.activity.collect;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.utils.AppUtils;
import com.froyo.commonjar.view.CustomListView;
import com.froyo.commonjar.view.CustomListView.OnLoadMoreListener;
import com.froyo.commonjar.view.CustomListView.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.CollectPhotoAdapter;
import com.wy.roadtrip.adapter.FindPagerAdapter;
import com.wy.roadtrip.adapter.MyCollectAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.vo.CollectPhotoVo;
import com.wy.roadtrip.vo.CollectVo;

/**
 * 
 * @Des: 我的收藏
 * @author Rhino
 * @version V1.0
 * @created 2015年5月6日 下午3:25:22
 */
public class CollectActivity extends BaseActivity {

	@ViewInject(R.id.vp_travel)
	private ViewPager vp_travel;

	@ViewInject(R.id.ll_tab_container)
	private LinearLayout tab_container;

	@ViewInject(R.id.hs_tab)
	private HorizontalScrollView hs_tab;

	private int lastScrollX = 0;

	private int width = 0;

	public int currentIndex = 0;

	private CustomListView page1ListView;
	private CustomListView page2ListView;

	private MyCollectAdapter adapter1;

	private CollectPhotoAdapter adapter2;

	/**保存页面来回切换时，标题栏的状态，以及标题文字和对应的处理事件*/
	private SparseArray<OnClickListener> map = new SparseArray<OnClickListener>();
	
	private SparseArray<String> titleMap = new SparseArray<String>();

	TitleBar bar;

	@Override
	public void doBusiness() {
		bar = new TitleBar(activity);
		bar.showBack();
		bar.setTitle("我的收藏");

		map.put(1, new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bar.tv_right.getText().toString().equals("编辑")) {
					bar.tv_right.setText("删除");
					toast("执行删除");
					titleMap.put(1, "删除");
				} else {
					bar.tv_right.setText("编辑");
					titleMap.put(1, "编辑");
					toast("回到编辑");
				}
			}
		});
		titleMap.put(1, "编辑");
		titleMap.put(2, "编辑");
		titleMap.put(3, "编辑");
		titleMap.put(4, "编辑");
		map.put(2, new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bar.tv_right.getText().toString().equals("编辑")) {
					bar.tv_right.setText("删除");
					toast("执行删除");
					titleMap.put(2, "删除");
				} else {
					bar.tv_right.setText("编辑");
					titleMap.put(2, "编辑");
					toast("回到编辑");
				}
			}
		});
		map.put(3, new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bar.tv_right.getText().toString().equals("编辑")) {
					bar.tv_right.setText("删除");
					toast("执行删除");
					titleMap.put(3, "删除");
				} else {
					bar.tv_right.setText("编辑");
					titleMap.put(3, "编辑");
					toast("回到编辑");
				}
			}
		});
		map.put(4, new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bar.tv_right.getText().toString().equals("编辑")) {
					bar.tv_right.setText("删除");
					toast("执行删除");
					titleMap.put(4, "删除");
				} else {
					bar.tv_right.setText("编辑");
					titleMap.put(4, "编辑");
					toast("回到编辑");
				}
			}
		});

		List<View> lists = new ArrayList<View>();
		for (int i = 1; i < 5; i++) {
			View view = activity.makeView(R.layout.layout_collect_page);
			lists.add(view);
			if (i == 1) {
				initPage1(view);
			} else if (i == 4) {
				initPage2(view);
			}
		}

		FindPagerAdapter adadapter = new FindPagerAdapter(activity, lists);
		vp_travel.setAdapter(adadapter);

		vp_travel.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				changeBtnBg(position);
				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

		List<String> names = new ArrayList<String>();
		names.add("看点");
		names.add("私享");
		names.add("线路");
		names.add("照片");
		addTab(names);

	}

	public void selectItem(int position, List<String> datas, View view) {
		vp_travel.setCurrentItem(position);
	}

	private void changeBtnBg(int position) {

		bar.showRightText(map.get(position + 1), titleMap.get(position + 1));

		if (position == 0) {
			initPage(position);
		} else if (position == 1) {
			initPage(position);
		} else if (position == 2) {
			initPage(position);
		} else if (position == 3) {
			initPage(position);
		}

		scrollToChild(position, (int) (0.3 * tab_container.getChildAt(position)
				.getWidth()));
		for (int i = 0; i < tab_container.getChildCount(); i++) {
			LinearLayout v = (LinearLayout) tab_container.getChildAt(i);
			if (i == position) {
				v.getChildAt(1).setVisibility(View.VISIBLE);
				((TextView) v.getChildAt(0)).setTextColor(getResources()
						.getColor(R.color.base_color));
			} else {
				v.getChildAt(1).setVisibility(View.INVISIBLE);
				((TextView) v.getChildAt(0)).setTextColor(Color
						.parseColor("#333333"));
			}
		}
	}

	private void scrollToChild(int position, int offset) {

		int newScrollX = tab_container.getChildAt(position).getLeft() + offset;

		if (position > 0 || offset > 0) {
			newScrollX -= width;
		}
		if (position != 0) {
			if (newScrollX != lastScrollX) {
				lastScrollX = newScrollX;
				hs_tab.scrollTo(newScrollX, 0);
			}
		} else {
			lastScrollX = 0;
			hs_tab.scrollTo(0, 0);
		}

	}

	public void addTab(final List<String> names) {

		if (names.size() < 5) {
			width = AppUtils.getWidth(activity) / names.size();
		} else {
			width = AppUtils.getWidth(activity) / 4;
		}
		for (int i = 0; i < names.size(); i++) {
			final String vo = names.get(i);
			View view = activity.makeView(R.layout.view_collect_tab);
			TextView text = (TextView) view.findViewById(R.id.tv_name);
			text.setText(vo);

			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,
					LayoutParams.WRAP_CONTENT);
			lp.gravity = Gravity.CENTER_VERTICAL;
			view.setLayoutParams(lp);

			tab_container.addView(view);
			final int tempP = i;
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					selectItem(tempP, names, arg0);
				}
			});
		}
		changeBtnBg(0);
	}

	private void initPage(int p) {
		activity.toast("执行业务" + p);
	}

	private void initPage1(View view) {
		page1ListView = (CustomListView) view.findViewById(R.id.lv_page_list);
		List<CollectVo> data = new ArrayList<CollectVo>();
		data.add(new CollectVo());
		data.add(new CollectVo());
		data.add(new CollectVo());
		data.add(new CollectVo());
		adapter1 = new MyCollectAdapter(data, activity, R.layout.item_collect);
		page1ListView.setAdapter(adapter1);

		page1ListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {

			}
		});

		page1ListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// listView.setHasNoMoreData();（加载更多，没有更多数据时调用）
				// listView.setAutoLoadMore(false);(加载更多失败，变为手动加载调用)--（手动加载更多成功应该恢复到自动加载listView.setAutoLoadMore(true)）
			}
		});
	}

	private void initPage2(View view) {
		page2ListView = (CustomListView) view.findViewById(R.id.lv_page_list);
		List<CollectPhotoVo> data = new ArrayList<CollectPhotoVo>();
		data.add(new CollectPhotoVo());
		data.add(new CollectPhotoVo());
		data.add(new CollectPhotoVo());
		data.add(new CollectPhotoVo());
		adapter2 = new CollectPhotoAdapter(data, activity,
				R.layout.item_collect_photo);
		page2ListView.setAdapter(adapter2);

		page2ListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {

			}
		});

		page2ListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// listView.setHasNoMoreData();（加载更多，没有更多数据时调用）
				// listView.setAutoLoadMore(false);(加载更多失败，变为手动加载调用)--（手动加载更多成功应该恢复到自动加载listView.setAutoLoadMore(true)）
			}
		});
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_collect;
	}

}
