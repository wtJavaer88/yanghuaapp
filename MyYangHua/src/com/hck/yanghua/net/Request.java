package com.hck.yanghua.net;

import android.R.bool;

import com.google.android.gms.internal.el;
import com.hck.httpserver.HCKHttpClient;
import com.hck.httpserver.HCKHttpResponseHandler;
import com.hck.httpserver.RequestParams;
import com.hck.yanghua.bean.UserBean;
import com.hck.yanghua.data.Constant;
import com.hck.yanghua.data.MyData;

public class Request {

	private static final int TIME_OUT = 15 * 1000;
	public static final int REQUEST_SUCCESS = 0;
	private static HCKHttpClient client = new HCKHttpClient();
	static {
		client.setTimeout(TIME_OUT);
	}

	private static void requestPost(String method, Boolean isNeedUserId,
			RequestParams params, HCKHttpResponseHandler handler) {
		if (params == null) {
			client.post(Constant.MAINHOST + method, handler);
		} else {
			UserBean userBean = MyData.getData().getUserBean();
			if (isNeedUserId) {
				params.put("uid", userBean.getUid() + "");
			}
			client.post(Constant.MAINHOST + method, params, handler);
		}
	}

	private static void requestGet(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		if (params == null) {
			client.get(Constant.MAINHOST + method, handler);
		} else {
			client.get(Constant.MAINHOST + method, params, handler);
		}

	}

	private static void requestGet(String method, RequestParams params,
			HCKHttpResponseHandler handler, boolean isNeedUserId) {
		if (params == null) {
			client.get(Constant.MAINHOST + method, handler);
		} else {
			UserBean userBean = MyData.getData().getUserBean();
			if (isNeedUserId) {
				params.put("uid", userBean.getUid() + "");
			}
			client.post(Constant.MAINHOST + method, params, handler);
		}

	}

	// 获取版本信息
	public static void getBanBenInfo(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(method, params, handler);

	}

	// 增加用户
	public static void addUser(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(method, false, params, handler);
	}

	// 修改用pushid
	public static void addUserPusshId(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(method, true, params, handler);
	}

	// 获取用户信息
	public static void getUserData(String method, Boolean isMy,
			RequestParams params, HCKHttpResponseHandler handler) {
		if (isMy) {
			requestPost(method, true, params, handler);
		} else {
			requestPost(method, false, params, handler);
		}

	}

	// 添加帖子信息
	public static void addTieZi(String method, RequestParams params,
			HCKHttpResponseHandler handler) {

		requestPost(method, true, params, handler);
	}

	// 获取帖子信息
	public static void getTieZi(String method, RequestParams params,
			HCKHttpResponseHandler handler) {

		requestPost(method, false, params, handler);
	}

	// 增加回复
	public static void addHuiTie(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(method, true, params, handler);
	}

	// 增加赞
	public static void addZan(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(method, true, params, handler);
	}

	// 获取赞
	public static void getZan(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(method, params, handler);
	}

	// 获取回帖
	public static void getHuiTie(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(method, params, handler);
	}

	// 获取热帖
	public static void getHotTieZi(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(method, params, handler);
	}

	// 获取回复信息
	public static void getHuiFuMsg(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(method, params, handler, true);
	}

	// 获取用户信息
	public static void getUserInfoByStId(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		client.get(Constant.MAINHOST + method, params, handler);

	}

	// 增加好友
	public static void addFriend(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(Constant.METHOD_ADDFRIEND, true, params, handler);
	}

	// 获取好友
	public static void getFriends(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GETFRIEND, params, handler, true);
	}

	// 删除回复msg
	public static void deleteHuiFuMsg(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(Constant.METHOD_DELETE_HUIFU_MSG, false, params, handler);
	}

	// 获取附近的人
	public static void getNearUser(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_NEAR_USER, params, handler);
	}

	// 获取娱乐活动信息
	public static void getHuoDongs(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_HUODONG, params, handler);
	}

	// 获取活动url
	public static void getHuoDongUrl(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_HUO_DONG_URL, params, handler, true);
	}

	// 获取兑换url
	public static void getDuiHuanUrl(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_DUI_HUAN, params, handler);
	}

	// 增加关注
	public static void addGuanZhu(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(Constant.METHOD_ADD_GUANZHU, true, params, handler);
	}

	// 获取关注ids
	public static void getGuanZhuIds(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_GUANZHU_IDS, params, handler, true);
	}

	// 删除关注
	public static void deleteGuanZhu(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestPost(Constant.METHOD_DELETE_GUANZHU, false, params, handler);
	}

	// 获取回复信息size
	public static void getMsgSize(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_MSG_SIZE, params, handler, true);
	}

	// 获取关注我的信息
	public static void getGuanZhuMsg(String method,RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(method, params, handler, true);
	}

	// 获取我的帖子数量
	public static void getMyTieZiSize(RequestParams params,
			HCKHttpResponseHandler handler) {
		requestGet(Constant.METHOD_GET_MY_TIEZI_SIZE, params, handler, true);

	}

}
