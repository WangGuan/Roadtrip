package com.wy.roadtrip.activity.line;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.froyo.commonjar.activity.BaseActivity;
import com.froyo.commonjar.utils.Utils;
import com.froyo.commonjar.view.CustomListView;
import com.froyo.commonjar.view.CustomListView.OnLoadMoreListener;
import com.froyo.commonjar.view.CustomListView.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wy.roadtrip.R;
import com.wy.roadtrip.adapter.LineAdapter;
import com.wy.roadtrip.componet.TitleBar;
import com.wy.roadtrip.vo.LineInfoVo;

/**
 * 
 * @Des: 我的线路
 * @author Rhino
 * @version V1.0
 * @created 2015年5月6日 下午3:25:22
 */
public class LineActivity extends BaseActivity {

	@ViewInject(R.id.list_view)
	private CustomListView listView;

	private LineAdapter adapter;

	@Override
	public void doBusiness() {
		final TitleBar bar = new TitleBar(activity);
		bar.showBack();
		bar.setTitle("我的线路");
		bar.showRightText(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (bar.tv_right.getText().toString().equals("编辑")) {
					bar.tv_right.setText("删除");
					List<LineInfoVo> data = adapter.getDataSource();
					if (!Utils.isEmpty(data)) {
						for (LineInfoVo vo : data) {
							vo.setShown(true);
						}
						adapter.refresh();
					}
				} else {
					bar.tv_right.setText("编辑");
					List<LineInfoVo> data = adapter.getDataSource();
					List<LineInfoVo> tempData = new ArrayList<LineInfoVo>();
					if (!Utils.isEmpty(data)) {
						for (LineInfoVo vo : data) {
							vo.setShown(false);
							if (!vo.isSelected()) {
								tempData.add(vo);
							}
						}
						adapter.removeAll();
						adapter.addItems(tempData);
					}

				}
			}
		}, "编辑");

		adapter = new LineAdapter(new ArrayList<LineInfoVo>(), activity,
				R.layout.item_line);
		listView.setAdapter(adapter);
		adapter.addItems(getMockdata());
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {

			}
		});

		listView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// listView.setHasNoMoreData();（加载更多，没有更多数据时调用）
				// listView.setAutoLoadMore(false);(加载更多失败，变为手动加载调用)--（手动加载更多成功应该恢复到自动加载listView.setAutoLoadMore(true)）
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				skip(LinePreviewActivity.class);
			}
		});

	}

	private List<LineInfoVo> getMockdata() {
		List<LineInfoVo> data = new ArrayList<LineInfoVo>();
		for (int i = 0; i < 5; i++) {
			LineInfoVo vo = new LineInfoVo();
			vo.setTitle("风景最为秀丽的旅程" + i);
			data.add(vo);
		}
		return data;
	}

	@Override
	protected int setLayoutResID() {
		return R.layout.activity_line;
	}
}