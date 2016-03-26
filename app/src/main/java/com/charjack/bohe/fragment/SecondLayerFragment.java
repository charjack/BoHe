package com.charjack.bohe.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charjack.bohe.HomeListAdapter;
import com.charjack.bohe.MainActivity;
import com.charjack.bohe.R;
import com.charjack.bohe.vo.PagerPassageInfo;
import com.charjack.bohe.vo.PassageInfo;
import com.charjack.bohe.vo.UrlContents;
import com.charjack.bohe.vo.WebPassageInfo;
import com.shizhefei.fragment.LazyFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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

	private ArrayList<PagerPassageInfo> passageInfos= new ArrayList<PagerPassageInfo>();
	private PagerPassageInfo pagerPassageInfo;
	private WebPassageInfo webPassageInfo;
	private final OkHttpClient client = new OkHttpClient();



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
		getInfofromNetwork();
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


	public Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what){
				case 1:
					mHomeListAdapter.notifyDataSetChanged();
					break;
			}
		}
	};

	//okhttp
	private ExecutorService mThreadPool;
	public SecondLayerFragment(){mThreadPool= Executors.newSingleThreadExecutor();}

	public void getInfofromNetwork() {
		final String urlhome = UrlContents.SITEURL+UrlContents.HOMECONTENTURL;
		System.out.println(urlhome);
		mThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				Request request = new Request.Builder().url(urlhome).build();
				//android.os.NetworkOnMainThreadException 如果在主线程中执行的话会报这个异常
				//所以要想办法放到子线程执行
				try {
					Response response = client.newCall(request).execute();
					if (response.isSuccessful()) {
						System.out.println("网络下载成功");
//                        System.out.println(response.body().string());
						//response.body().string()这个是存在缓存中数据，只能使用一次，如果之前打印使用一次，后面就会出错。
						WebPassageInfo webpassageInfo = JSONObject.parseObject(response.body().string(), WebPassageInfo.class);
//                      System.out.println(webpassageInfo.toString());
						webPassageInfo.getData().getItems().addAll(webpassageInfo.getData().getItems());
						//discoveryAdapter.notifyDataSetChanged();
						Message msg = handler.obtainMessage(1, "success");
						msg.sendToTarget();
					}
				} catch (IOException e) {
					System.out.println("网络下载失败");
					e.printStackTrace();
				}
			}
		});
	}




	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();

	}

}
