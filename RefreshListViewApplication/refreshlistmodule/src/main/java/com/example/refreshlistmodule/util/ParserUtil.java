package com.example.refreshlistmodule.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cn.jialebao.model.AdminIstrativeArea;
import cn.jialebao.model.AppendUser;
import cn.jialebao.model.ConsumeRecord;
import cn.jialebao.model.DailyMarketUserData4Channel;
import cn.jialebao.model.DailyMarketUserData4Sales;
import cn.jialebao.model.DeliveryCell;
import cn.jialebao.model.EnumConfig;
import cn.jialebao.model.FoodInfo;
import cn.jialebao.model.FoodType;
import cn.jialebao.model.MarketingPurchaseDetail;
import cn.jialebao.model.OrderDetail;
import cn.jialebao.model.OrderRecord;
import cn.jialebao.model.SenderUserPosition;
import cn.jialebao.model.StatData4Chart;
import cn.jialebao.model.UserAddrMgr;
import cn.jialebao.model.UserInfo;
import cn.jialebao.model.VersionManagerment;

/**
 * 深度解析类， 专门用来解析映射到数据表段的工具, 提供的都是静态方法
 * 
 * @author hc
 *
 */
public class ParserUtil {

	public static HashMap<String, Object> parserInfo(String json) {
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		int status = -1;
		String msg = ConstantClient.EROORMSG;
		JSONObject returnData = null;
		try {
			if (null != json) {
				JSONObject jsonObject = new JSONObject(json);
				status = jsonObject.getInt(ConstantClient.STATUS);
				msg = jsonObject.getString(ConstantClient.MSG);
				returnData = jsonObject.getJSONObject(ConstantClient.RETURNDATA);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		dataMap.put(ConstantClient.STATUS, status);
		dataMap.put(ConstantClient.MSG, msg == null ? "" : msg);
		dataMap.put(ConstantClient.RETURNDATA, returnData);
		return dataMap;
	}

	public static UserAddrMgr getUserAddrMgrInfo(String returnData) {
		Gson gson = new Gson();
		UserAddrMgr uInfo = gson.fromJson(returnData, new TypeToken<UserAddrMgr>() {
		}.getType());
		return uInfo;
	}

	public static UserInfo getUserInfo(String returnData) {
		Gson gson = new Gson();
		UserInfo uInfo = gson.fromJson(returnData, new TypeToken<UserInfo>() {
		}.getType());
		return uInfo;
	}

	public static AppendUser getAppendUserInfo(String returnData) {
		Gson gson = new Gson();
		AppendUser apUser = gson.fromJson(returnData, new TypeToken<AppendUser>() {
		}.getType());
		return apUser;
	}

	public static List<AppendUser> listAppendUserInfo(String returnData) {
		Gson gson = new Gson();
		List<AppendUser> listAppUser = gson.fromJson(returnData, new TypeToken<List<AppendUser>>() {
		}.getType());
		return listAppUser;
	}

	public static VersionManagerment getVersionInfo(String returnData) {
		Gson gson = new Gson();
		VersionManagerment vInfo = gson.fromJson(returnData, new TypeToken<VersionManagerment>() {
		}.getType());
		return vInfo;
	}

	public static List<FoodInfo> listFood(String returnData) {
		Gson gson = new Gson();
		List<FoodInfo> listFood = gson.fromJson(returnData, new TypeToken<List<FoodInfo>>() {
		}.getType());
		return listFood;
	}

	public static List<OrderDetail> listOrderDetail(String returnData) {
		Gson gson = new Gson();
		List<OrderDetail> listOd = gson.fromJson(returnData, new TypeToken<List<OrderDetail>>() {
		}.getType());
		return listOd;
	}

	public static OrderRecord getOrderRecord(String returnData) {
		Gson gson = new Gson();
		OrderRecord orderRecord = gson.fromJson(returnData, new TypeToken<OrderRecord>() {
		}.getType());
		return orderRecord;
	}

	public static List<ConsumeRecord> listConsumeRecord(String returnData) {
		Gson gson = new Gson();
		List<ConsumeRecord> listConsume = gson.fromJson(returnData, new TypeToken<List<ConsumeRecord>>() {
		}.getType());
		return listConsume;
	}

	public static List<OrderRecord> listOrderRecord(String returnData) {
		Gson gson = new Gson();
		List<OrderRecord> listOrder = gson.fromJson(returnData, new TypeToken<List<OrderRecord>>() {
		}.getType());
		return listOrder;
	}

	public static List<DailyMarketUserData4Sales> listDailyMarketUserData4Sales(String returnData) {
		Gson gson = new Gson();
		List<DailyMarketUserData4Sales> listOrder = gson.fromJson(returnData, new TypeToken<List<DailyMarketUserData4Sales>>() {
		}.getType());
		return listOrder;
	}

//	public static List<SmallBoxGoodsRecord> listSmallBoxGoodsRecord(String returnData) {
//		Gson gson = new Gson();
//		List<SmallBoxGoodsRecord> smallBoxGoodsRecord = gson.fromJson(returnData, new TypeToken<List<SmallBoxGoodsRecord>>() {
//		}.getType());
//		return smallBoxGoodsRecord;
//	}
//
//	public static List<BoxInfo> listBoxlist(String returnData) {
//		Gson gson = new Gson();
//		List<BoxInfo> boxInfos = gson.fromJson(returnData, new TypeToken<List<BoxInfo>>() {
//		}.getType());
//		return boxInfos;
//	}
//
//	public static List<SmallBoxGoodsRecord> listSmallGood(String returnData) {
//		Gson gson = new Gson();
//		List<SmallBoxGoodsRecord> goodList = gson.fromJson(returnData, new TypeToken<List<SmallBoxGoodsRecord>>() {
//		}.getType());
//		return goodList;
//	}

	public static List<MarketingPurchaseDetail> getmarketDetail(String returnData) {
		Gson gson = new Gson();
		List<MarketingPurchaseDetail> marketDetails = gson.fromJson(returnData, new TypeToken<List<MarketingPurchaseDetail>>() {
		}.getType());
		return marketDetails;
	}

	public static List<FoodType> listFoodType(String returnData) {
		Gson gson = new Gson();
		List<FoodType> listFoodType = gson.fromJson(returnData, new TypeToken<List<FoodType>>() {
		}.getType());
		return listFoodType;
	}

	public static LinkedList<SaveUserPwdUtil.UserInfo> getSpSaveUserInfo(String json) {
		Gson gson = new Gson();
		LinkedList<SaveUserPwdUtil.UserInfo> userInfos = gson.fromJson(json, new TypeToken<LinkedList<SaveUserPwdUtil.UserInfo>>() {
		}.getType());
		return userInfos;
	}

	public static DeliveryCell getSenderUser(String returnData) {
		Gson gson = new Gson();
		DeliveryCell su = gson.fromJson(returnData, new TypeToken<DeliveryCell>() {
		}.getType());
		return su;
	}

	public static List<SenderUserPosition> listSenderPosition(String returnData) {
		Gson gson = new Gson();
		List<SenderUserPosition> listPosition = gson.fromJson(returnData, new TypeToken<List<SenderUserPosition>>() {
		}.getType());
		return listPosition;
	}

	public static List<AdminIstrativeArea> listSiteType(String returnData) {
		Gson gson = new Gson();
		List<AdminIstrativeArea> siteType = gson.fromJson(returnData, new TypeToken<List<AdminIstrativeArea>>() {
		}.getType());
		return siteType;
	}

	public static List<StatData4Chart> listSalePerformance(String returnData) {
		Gson gson = new Gson();
		List<StatData4Chart> listPosition = gson.fromJson(returnData, new TypeToken<List<StatData4Chart>>() {
		}.getType());
		return listPosition;
	}

	public static List<DailyMarketUserData4Channel> listCustomerData(String returnData) {
		Gson gson = new Gson();
		List<DailyMarketUserData4Channel> list = gson.fromJson(returnData, new TypeToken<List<DailyMarketUserData4Channel>>() {
		}.getType());
		return list;
	}

	public static List<EnumConfig> listEnumConfig(String returnData) {
		Gson gson = new Gson();
		List<EnumConfig> list = gson.fromJson(returnData, new TypeToken<List<EnumConfig>>() {
		}.getType());
		return list;
	}
}
