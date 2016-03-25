package com.charjack.bohe.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charjack.bohe.R;
import com.shizhefei.fragment.LazyFragment;


public class SecondLayerFragment extends LazyFragment {
	public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
	public static final String INTENT_INT_POSITION = "intent_int_position";
	private String tabName;
	private int position;
	private TextView textView;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		tabName = getArguments().getString(INTENT_STRING_TABNAME);
		position = getArguments().getInt(INTENT_INT_POSITION);
		setContentView(R.layout.fragment_tabmain_item);



	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();

	}

}
