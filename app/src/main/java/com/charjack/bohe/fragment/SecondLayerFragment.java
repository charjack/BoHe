package com.charjack.bohe.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charjack.bohe.HomeListAdapter;
import com.charjack.bohe.MainActivity;
import com.charjack.bohe.R;
import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class SecondLayerFragment extends LazyFragment {
	public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
	public static final String INTENT_INT_POSITION = "intent_int_position";
	private String tabName;
	private int position;
	private TextView textView;
	private List<HomeListItemData> list = new ArrayList<>();
	private HomeListItemData listitem;
	private HomeListAdapter mHomeListAdapter;
	public ListView listView;
	public PtrClassicFrameLayout mPtrLayout;
	public MainActivity mainActivity;
	private TextView mPtrText;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.mainActivity = (MainActivity) context;
	}

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		tabName = getArguments().getString(INTENT_STRING_TABNAME);
		position = getArguments().getInt(INTENT_INT_POSITION);
		setContentView(R.layout.fragment_tabmain_item);
		listView = (ListView) findViewById(R.id.fm_first_all_listView);
		mPtrLayout = (PtrClassicFrameLayout) findViewById(R.id.fm_first_all_ptrLayout);

		initdata();
		mHomeListAdapter = new HomeListAdapter(mainActivity,list);
		listView.setAdapter(mHomeListAdapter);

		LayoutInflater inflater = LayoutInflater.from(mainActivity);
		initPtr(inflater);

	}

	private void initPtr(LayoutInflater inflater) {
		View header = inflater.inflate(R.layout.fm_home_ptrhead_layout, null);

		mPtrText = (TextView) header.findViewById(R.id.loading_text);

		mPtrLayout.setDurationToCloseHeader(1000);
		mPtrLayout.setDurationToClose(2000);
		mPtrLayout.setHeaderView(header);
		mPtrLayout.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				mPtrText.setText("释放刷新");
				System.out.println("------checkCanDoRefresh");
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
			}

			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				mPtrText.setText("加载中");
//                mLoadingLayout.setVisibility(View.GONE);
//                mPtrImg.setBackgroundResource(R.drawable.refresh_anim);
//                mPtrAnimation = (AnimationDrawable) mPtrImg.getBackground();
//                mNothingText.setText(getString(R.string.loading));
//                mPtrAnimation.start();
				System.out.println("------onRefreshBegin");
				mPtrLayout.postDelayed(new Runnable() {
					@Override
					public void run() {
						loadData();

					}
				}, 100);
			}
		});
	}

	private void initdata() {
		for(int i=0;i<10;i++){
			list.add(new HomeListItemData());
		}
	}

	private void loadData() {
		for(int i=0;i<10;i++){
			list.add(new HomeListItemData());
		}
		mHomeListAdapter.notifyDataSetChanged();
		mPtrLayout.refreshComplete();//表示刷新完成
	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();

	}

}
