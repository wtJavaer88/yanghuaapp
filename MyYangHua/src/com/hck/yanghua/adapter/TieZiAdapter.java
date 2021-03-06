package com.hck.yanghua.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hck.yanghua.R;
import com.hck.yanghua.bean.TieZiBean;
import com.hck.yanghua.data.Constant;
import com.hck.yanghua.util.ExpressionUtil;
import com.hck.yanghua.util.GetImageUtil;
import com.hck.yanghua.util.LogUtil;
import com.hck.yanghua.util.MyTools;
import com.hck.yanghua.util.TimeUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TieZiAdapter extends BaseAdapter {
	public static final int JING_HUA = 1;
	public static final int HUO = 20;
	public static final int TUIJIAN = 1;
	public List<TieZiBean> tieZiBeans;
	private Context context;
	private OnTouXiangCliceListener oCliceListener;

	public interface OnTouXiangCliceListener {
		void getUserId(Long uid);
	}

	public TieZiAdapter(Context context, List<TieZiBean> tieZiBeans,
			OnTouXiangCliceListener onTouXiangCliceListener) {
		this.tieZiBeans = tieZiBeans;
		if (this.tieZiBeans == null) {
			this.tieZiBeans = new ArrayList<TieZiBean>();
		}
		this.context = context;
		this.oCliceListener = onTouXiangCliceListener;
	}

	@Override
	public int getCount() {
		return tieZiBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return tieZiBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_tiezi_item, null);
			viewHolder = new ViewHolder();
			viewHolder.touxiangImageView = (ImageView) convertView
					.findViewById(R.id.tiezi_touxiang);
			viewHolder.tupianImageView = (ImageView) convertView
					.findViewById(R.id.tiezi_image1);
			viewHolder.tupian2ImageView = (ImageView) convertView
					.findViewById(R.id.tiezi_image2);
			viewHolder.userNameTextView = (TextView) convertView
					.findViewById(R.id.tiezi_userName);
			viewHolder.contentTextView = (TextView) convertView
					.findViewById(R.id.tiezi_content);
			viewHolder.layout = (LinearLayout) convertView
					.findViewById(R.id.tiezi_tupian_lay);
			viewHolder.dingTextView = (TextView) convertView
					.findViewById(R.id.tiezi_ding);
			viewHolder.pinglunTextView = (TextView) convertView
					.findViewById(R.id.tiezi_pinglun);
			viewHolder.xingbieImageView = (ImageView) convertView
					.findViewById(R.id.tiezi_xingbie);
			viewHolder.fensiTextView = (TextView) convertView
					.findViewById(R.id.tie_fensi);
			viewHolder.huoTextView = (TextView) convertView
					.findViewById(R.id.tiezi_huo);
			viewHolder.jingTextView = (TextView) convertView
					.findViewById(R.id.tiezi_jing);
			viewHolder.tuijianTextView = (TextView) convertView
					.findViewById(R.id.tiezi_tuijian);
			viewHolder.addressLayout = (RelativeLayout) convertView
					.findViewById(R.id.tiezi_address_lay);
			viewHolder.addressTextView = (TextView) convertView
					.findViewById(R.id.tiezi_address);
			viewHolder.timeTextView = (TextView) convertView
					.findViewById(R.id.tiez_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		TieZiBean tieZiBean = tieZiBeans.get(position);
		viewHolder.userNameTextView.setText(tieZiBean.getName());
		SpannableString spannableString = ExpressionUtil.getExpressionString(
				context, tieZiBean.getContent(), Constant.zhengze);
		
		viewHolder.contentTextView.setText(spannableString);
		viewHolder.dingTextView.setText(tieZiBean.getDingsize() + "");
		viewHolder.pinglunTextView.setText(tieZiBean.getPinglunsize() + "");
		viewHolder.fensiTextView.setText("粉丝:" + tieZiBean.getFensi());
		if (!TextUtils.isEmpty(tieZiBean.getAddress())) {
			viewHolder.addressTextView.setText(tieZiBean.getAddress());
		} else {
			viewHolder.addressTextView.setText("");
		}
		if (tieZiBean.isNan()) {
			viewHolder.xingbieImageView.setImageResource(R.drawable.nan);
		} else {
			viewHolder.xingbieImageView.setImageResource(R.drawable.nv);
		}
		if (tieZiBean.getIsjinghua() == JING_HUA) {
			viewHolder.jingTextView.setVisibility(View.VISIBLE);
		} else {
			viewHolder.jingTextView.setVisibility(View.GONE);
		}
		if (tieZiBean.getIstuijian() == TUIJIAN) {
			viewHolder.tuijianTextView.setVisibility(View.VISIBLE);
		} else {
			viewHolder.tuijianTextView.setVisibility(View.GONE);
		}
		if (tieZiBean.getPinglunsize() > 20) {
			viewHolder.huoTextView.setVisibility(View.VISIBLE);
		} else {
			viewHolder.huoTextView.setVisibility(View.GONE);
		}
		viewHolder.timeTextView.setText("丨"+TimeUtil.forTime(tieZiBean
				.getHuifuTiem()));
		String tupian6 = tieZiBean.getTupian6();
		String tupian7 = tieZiBean.getTupian7();
		if (!TextUtils.isEmpty(tupian6)) {
			viewHolder.tupianImageView.setVisibility(View.VISIBLE);
			viewHolder.layout.setVisibility(View.VISIBLE);
			GetImageUtil.showImageXiaoTu(tupian6, viewHolder.tupianImageView);
		} else {
			viewHolder.layout.setVisibility(View.GONE);
		}
		if (!TextUtils.isEmpty(tupian7)) {
			viewHolder.layout.setVisibility(View.VISIBLE);
			viewHolder.tupian2ImageView.setVisibility(View.VISIBLE);
			GetImageUtil.showImageXiaoTu(tupian7, viewHolder.tupian2ImageView);
		} else {
			viewHolder.tupian2ImageView.setVisibility(View.INVISIBLE);
		}
		ImageLoader.getInstance().displayImage(tieZiBean.getTouxiang(),
				viewHolder.touxiangImageView, MyTools.getoptions());
		viewHolder.touxiangImageView.setTag(tieZiBean.getUid());
		viewHolder.userNameTextView.setTag(tieZiBean.getUid());
		viewHolder.fensiTextView.setTag(tieZiBean.getUid());
		showUser(viewHolder.touxiangImageView);
		showUser(viewHolder.userNameTextView);
		showUser(viewHolder.fensiTextView);
		return convertView;
	}

	private void showUser(View View) {
		View.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Long uid = (Long) v.getTag();
				oCliceListener.getUserId(uid);
			}
		});
	}

	static class ViewHolder {
		LinearLayout layout;
		RelativeLayout addressLayout;
		ImageView touxiangImageView, tupianImageView, tupian2ImageView,
				xingbieImageView;
		TextView userNameTextView, contentTextView, dingTextView,
				pinglunTextView, timeTextView, fensiTextView;
		TextView huoTextView, jingTextView, tuijianTextView, addressTextView;
	}

	public void addData(List<TieZiBean> tieZiBeans) {
		this.tieZiBeans.addAll(tieZiBeans);
		this.notifyDataSetChanged();
	}

	public void addZan(int pos) {
		if (pos >= 1) {
			pos--;
		}
		TieZiBean tieZiBean = tieZiBeans.get(pos);
		tieZiBeans.get(pos).setDingsize(tieZiBean.getDingsize() + 1);
		this.notifyDataSetChanged();

	}

	public void addPl(int pos) {
		if (pos >= 1) {
			pos--;
		}
		TieZiBean tieZiBean = tieZiBeans.get(pos);

		tieZiBeans.get(pos).setPinglunsize(tieZiBean.getPinglunsize() + 1);
		this.notifyDataSetChanged();

	}

}
