package com.hck.yanghua.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hck.yanghua.R;
import com.hck.yanghua.ui.MainActivity;
import com.hck.yanghua.util.MyTools;

/**
 * 公用title.
 */
public class TitleBar extends LinearLayout {
	private LinearLayout mLeftBackBtn; // 左边返回按钮
	private TextView mCenterTextV; // 中间文本.
	private TextView mLeftTextV; // 中间文本.
	private TextView mRightTextV; // 中间文本.
	private Context mContext;
	private ImageView tongzhiImageView, guanzhuImageView;
	private FrameLayout frameLayout;

	public TitleBar(Context context) {
		super(context);
		mContext = context;
		init(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init(context);
	}

	/**
	 * 初始化view.
	 * 
	 * @param context
	 */
	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.title_bar, this);
		mLeftBackBtn = (LinearLayout) findViewById(R.id.left_btn);
		mCenterTextV = (TextView) findViewById(R.id.home_title_center);
		mRightTextV = (TextView) findViewById(R.id.home_title_right);
		mLeftTextV = (TextView) findViewById(R.id.home_title_left);
		tongzhiImageView = (ImageView) findViewById(R.id.title_tongzhi_img);
		guanzhuImageView = (ImageView) findViewById(R.id.title_guanzhu_img);
		frameLayout = (FrameLayout) findViewById(R.id.title_right_fr);
		setListener();
	}

	/**
	 * 点击返回按钮退出当前activity.
	 */
	private void setListener() {
		mLeftBackBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (MainActivity.mSlidingMenu != null) {
					MainActivity.mSlidingMenu.toggle();
				}

			}
		});
	}

	public void setTitleLeftContent(String content) {
		mCenterTextV.setText(content);
	}

	public TextView getLeftTextView() {
		return mLeftTextV;
	}

	public TextView getCenterTextView() {
		return mCenterTextV;
	}

	public TextView getRightTextView() {
		return mRightTextV;
	}

	public void setLeftText(String textString) {
		mLeftTextV.setText(textString);
	}

	public void setCenterText(String textString) {
		mCenterTextV.setText(textString);
	}

	public void setRightText(String textString) {
		mRightTextV.setText(textString);
	}

	public void setLeftTextColor(int color) {
		mLeftTextV.setTextColor(color);
	}

	public void setCentrTextColor(int color) {
		mCenterTextV.setTextColor(color);
	}

	public void setRightTextColor(int color) {
		mRightTextV.setTextColor(color);
	}


	public void hidenTongZhiImg() {
		tongzhiImageView.setVisibility(View.GONE);
		tongzhiImageView.clearAnimation();
	}

	public void hidenGuanZhuImg() {
		guanzhuImageView.setVisibility(View.GONE);
		guanzhuImageView.clearAnimation();
	}

	public void showTongzhiImg() {
		tongzhiImageView.setVisibility(View.VISIBLE);
		MyTools.startAna(tongzhiImageView);
	}

	public void showGuanZhuImg() {
		guanzhuImageView.setVisibility(View.VISIBLE);
		MyTools.startAna(guanzhuImageView);
	}

	public void hiddenFra() {
		frameLayout.setVisibility(View.GONE);
	}

}
