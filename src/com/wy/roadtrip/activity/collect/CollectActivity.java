package com.wy.roadtrip.activity.collect;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.adapter.GridItemClickListener;
import com.froyo.commonjar.adapter.GridSimpleAdapter;
import com.froyo.commonjar.adapter.SimpleAdapter;
import com.froyo.commonjar.network.PostParams;
import com.froyo.commonjar.network.PostRequest;
import com.froyo.commonjar.network.RespListener;
import com.froyo.commonjar.utils.AppUtils;
import com.froyo.commonjar.utils.GsonTools;
import com.froyo.commonjar.utils.Utils;
import com.froyo.commonjar.view.CustomListView;
import com.froyo.commonjar.view.CustomListView.OnLoadMoreListener;
import com.froyo.commonjar.view.CustomListView.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.CollectPhotoAdapter;
import com.wy.roadtrip.adapter.FindPagerAdapter;
import com.wy.roadtrip.adapter.MyCollectRouteAdapter;
import com.wy.roadtrip.adapter.MyCollectViewAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.constant.Const;
import com.wy.roadtrip.utils.SimpleUtils;
import com.wy.roadtrip.vo.CollectPhotoVo;
import com.wy.roadtrip.vo.CollectRouteVo;
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
	private CustomListView page3ListView;
	private CustomListView page4ListView;
	private int pageNum1 = 1;
	private int pageNum2 = 1;
	private int pageNum3 = 1;
	private int pageNum4 = 1;

	private MyCollectViewAdapter adapter1;
	private MyCollectViewAdapter adapter2;
	private MyCollectRouteAdapter adapter3;
	private CollectPhotoAdapter adapter4;

	/** 保存页面来回切换时，标题栏的状态，以及标题文字和对应的处理事件 */
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
			
			if (i == 1) {
				View view = activity.makeView(R.layout.layout_collect_page);
				View temp=view.findViewById(R.id.view_divider);
				lists.add(view);
				temp.setVisibility(View.GONE);
				initPage1(view);
			} else if (i == 2) {
				View view = activity.makeView(R.layout.layout_collect_page);
				View temp=view.findViewById(R.id.view_divider);
				lists.add(view);
				temp.setVisibility(View.GONE);
				initPage2(view);
			} else if (i == 3) {
				View view = activity.makeView(R.layout.layout_collect_page);
				lists.add(view);
//				temp.setVisibility(View.GONE);
				initPage3(view);
			} else if (i == 4) {
				View view = activity.makeView(R.layout.layout_collect_photo);
				lists.add(view);
				initPage4(view);
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
			initPage(page1ListView, adapter1, position+1,
					SimpleUtils.buildUrl(activity, Const.COLLECT_VIEW));
		} else if (position == 1) {
			initPage(page2ListView, adapter2, position+1,
					SimpleUtils.buildUrl(activity, Const.COLLECT_SHARE));
		} else if (position == 2) {
			initPage(page3ListView, adapter3, position+1,
					SimpleUtils.buildUrl(activity, Const.COLLECT_ROUTE));
		} else if (position == 3) {
			initPage(page4ListView, adapter4);
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

	private void initPage(CustomListView listView, SimpleAdapter adapter,
			int pageOrder, String url) {
		if (adapter.isEmpty()) {
			pullRefresh(listView, adapter, pageOrder, url);
		}
	}

	private void initPage(CustomListView listView, GridSimpleAdapter adapter) {
		if (adapter.isEmpty()) {
			pullRefresh(listView, adapter);
		}
	}

	private void initPage1(View view) {
		page1ListView = (CustomListView) view.findViewById(R.id.lv_page_list);
		adapter1 = new MyCollectViewAdapter(new ArrayList<CollectVo>(), activity,
				R.layout.item_collect);
		page1ListView.setAdapter(adapter1);
		page1ListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pullRefresh(page1ListView, adapter1, 1,
						SimpleUtils.buildUrl(activity, Const.COLLECT_VIEW));
			}
		});

		page1ListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// listView.setHasNoMoreData();（加载更多，没有更多数据时调用）
				// listView.setAutoLoadMore(false);(加载更多失败，变为手动加载调用)--（手动加载更多成功应该恢复到自动加载listView.setAutoLoadMore(true)）
				loadMore(page1ListView, adapter1, 1,
						SimpleUtils.buildUrl(activity, Const.COLLECT_VIEW),
						pageNum1);
			}
		});
	}

	private void initPage2(View view) {
		page2ListView = (CustomListView) view.findViewById(R.id.lv_page_list);
		adapter2 = new MyCollectViewAdapter(new ArrayList<CollectVo>(), activity,
				R.layout.item_collect);
		page2ListView.setAdapter(adapter2);
		page2ListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pullRefresh(page2ListView, adapter2, 2,
						SimpleUtils.buildUrl(activity, Const.COLLECT_SHARE));
			}
		});

		page2ListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				loadMore(page2ListView, adapter2, 2,
						SimpleUtils.buildUrl(activity, Const.COLLECT_SHARE),
						pageNum2);
			}
		});
	}

	private void initPage3(View view) {
		page3ListView = (CustomListView) view.findViewById(R.id.lv_page_list);

		adapter3 = new MyCollectRouteAdapter(new ArrayList<CollectRouteVo>(), activity,
				R.layout.item_collect_route);
		page3ListView.setAdapter(adapter3);
		page3ListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pullRefresh(page3ListView, adapter3, 3,
						SimpleUtils.buildUrl(activity, Const.COLLECT_ROUTE));
			}
		});

		page3ListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				loadMore(page3ListView, adapter3, 3,
						SimpleUtils.buildUrl(activity, Const.COLLECT_ROUTE),
						pageNum3);
			}
		});

	}

	private void initPage4(View view) {
		page4ListView = (CustomListView) view.findViewById(R.id.lv_page_list);

		adapter4 = new CollectPhotoAdapter(new ArrayList<CollectPhotoVo>(),
				activity, R.layout.item_collect_photo);
		page4ListView.setAdapter(adapter4);

		adapter4.setOnGridClickListener(new GridItemClickListener() {

			@Override
			public void onGridItemClicked(View v, int position, long itemId) {
				toast(position + "");
			}
		});

		page4ListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				pullRefresh(page4ListView, adapter4);
			}
		});

		page4ListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				loadMore(page4ListView, adapter4, pageNum4);
			}
		});
	}

	private void pullRefresh(final CustomListView listView,
			final SimpleAdapter adapter, final int pageOrder, String url) {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("pageIndex", "1");
		PostRequest req = new PostRequest(activity, params, url,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						if (pageOrder == 1) {
							try {
								List<CollectVo> datas = GsonTools.getList(
										obj.getJSONObject("data").getJSONArray(
												"list"), CollectVo.class);
								int totalPage = obj.getJSONObject("data")
										.getInt("total_page");
								if (!Utils.isEmpty(datas)) {
									pageNum1 = 2;
									adapter.removeAll();
									adapter.addItems(datas);
									if (totalPage < 2) {
										listView.setHasNoMoreData();
									} else {
										listView.setAutoLoadMore(true);
										page1ListView.setCanLoadMore(true);
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						} else if (pageOrder == 2) {
							try {
								List<CollectVo> datas = GsonTools.getList(
										obj.getJSONObject("data").getJSONArray(
												"list"), CollectVo.class);
								int totalPage = obj.getJSONObject("data")
										.getInt("total_page");
								if (!Utils.isEmpty(datas)) {
									pageNum2 = 2;
									adapter.removeAll();
									adapter.addItems(datas);
									if (totalPage < 2) {
										listView.setHasNoMoreData();
									} else {
										listView.setAutoLoadMore(true);
										page2ListView.setCanLoadMore(true);
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						} else if (pageOrder == 3) {
							try {
								List<CollectRouteVo> datas = GsonTools.getList(
										obj.getJSONObject("data").getJSONArray(
												"list"), CollectRouteVo.class);
								int totalPage = obj.getJSONObject("data")
										.getInt("total_page");
								if (!Utils.isEmpty(datas)) {
									pageNum3 = 2;
									adapter.removeAll();
									adapter.addItems(datas);
									if (totalPage < 2) {
										listView.setHasNoMoreData();
									} else {
										listView.setAutoLoadMore(true);
										page3ListView.setCanLoadMore(true);
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						} 

						listView.onRefreshComplete();
					}

					@Override
					public void doFailed() {
						listView.onRefreshComplete();
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	private void pullRefresh(final CustomListView listView,
			final GridSimpleAdapter adapter) {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("pageIndex", "1");
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.COLLECT_PIC),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<CollectPhotoVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"list"), CollectPhotoVo.class);
							int totalPage = obj.getJSONObject("data").getInt(
									"total_page");
							if (!Utils.isEmpty(datas)) {
								pageNum4 = 2;
								adapter.removeAll();
								adapter.addItems(datas);
								if (totalPage < 2) {
									listView.setHasNoMoreData();
								} else {
									listView.setAutoLoadMore(true);
									page4ListView.setCanLoadMore(true);
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

						listView.onRefreshComplete();
					}

					@Override
					public void doFailed() {
						listView.onRefreshComplete();
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	private void loadMore(final CustomListView listView,
			final SimpleAdapter adapter, final int pageOrder, String url,
			int pageNum) {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("pageIndex", pageNum + "");
		PostRequest req = new PostRequest(activity, params, url,
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						if (pageOrder == 1) {
							try {
								List<CollectVo> datas = GsonTools.getList(
										obj.getJSONObject("data").getJSONArray(
												"list"), CollectVo.class);
								int totalPage = obj.getJSONObject("data")
										.getInt("total_page");
								if (!Utils.isEmpty(datas)) {
									pageNum1++;
									adapter.addItems(datas);
									if (totalPage < pageNum1) {
										listView.setHasNoMoreData();
									} else {
										listView.setAutoLoadMore(true);
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}

						} else if (pageOrder == 2) {
							try {
								List<CollectVo> datas = GsonTools.getList(
										obj.getJSONObject("data").getJSONArray(
												"list"), CollectVo.class);
								int totalPage = obj.getJSONObject("data")
										.getInt("total_page");
								if (!Utils.isEmpty(datas)) {
									pageNum2++;
									adapter.addItems(datas);
									if (totalPage < pageNum2) {
										listView.setHasNoMoreData();
									} else {
										listView.setAutoLoadMore(true);
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						} else if (pageOrder == 3) {
							try {
								List<CollectRouteVo> datas = GsonTools.getList(
										obj.getJSONObject("data").getJSONArray(
												"list"), CollectRouteVo.class);
								int totalPage = obj.getJSONObject("data")
										.getInt("total_page");
								if (!Utils.isEmpty(datas)) {
									pageNum3++;
									adapter.addItems(datas);
									if (totalPage < pageNum3) {
										listView.setHasNoMoreData();
									} else {
										listView.setAutoLoadMore(true);
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						} 
						listView.onLoadMoreComplete();
					}

					@Override
					public void doFailed() {
						listView.onLoadMoreComplete();
						listView.setAutoLoadMore(false);
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	private void loadMore(final CustomListView listView,
			final GridSimpleAdapter adapter, int pageNum) {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		PostParams params = new PostParams();
		params.put("pageIndex", pageNum + "");
		PostRequest req = new PostRequest(activity, params,
				SimpleUtils.buildUrl(activity, Const.COLLECT_PIC),
				new RespListener(activity) {

					@Override
					public void getResp(JSONObject obj) {
						try {
							List<CollectPhotoVo> datas = GsonTools.getList(
									obj.getJSONObject("data").getJSONArray(
											"list"), CollectPhotoVo.class);
							int totalPage = obj.getJSONObject("data").getInt(
									"total_page");
							if (!Utils.isEmpty(datas)) {
								pageNum4++;
								adapter.addItems(datas);
								if (totalPage < pageNum4) {
									listView.setHasNoMoreData();
								} else {
									listView.setAutoLoadMore(true);
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						listView.onLoadMoreComplete();
					}

					@Override
					public void doFailed() {
						listView.onLoadMoreComplete();
						listView.setAutoLoadMore(false);
					}
				});
		mQueue.add(req);
		mQueue.start();
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_collect;
	}
}
