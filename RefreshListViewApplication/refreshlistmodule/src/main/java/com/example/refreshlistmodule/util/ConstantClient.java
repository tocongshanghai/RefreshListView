package com.example.refreshlistmodule.util;

/**
 * 用到的常量
 * 
 * @author hc
 *
 */
public interface ConstantClient {

	// 请求服务端的地址
	/** 田总 */
	// String URL = "http://172.18.6.181:8080/jialebao/";
	/** 肖雷 */
//	 String URL = "http://192.168.1.68:8080/jialebao/";
	/** 李博凯 */
	// String URL = "http://172.18.6.182:8080/jialebao/";
	/** 俞文彬 */
//	 String URL = "http://192.168.1.67:8080/jialebao/";
	/** 正式库 */
//	 String URL = "http://www.jialebao.cc/";//121.40.205.167
	/** 测试库 */
	String URL = "http://121.40.155.207/";

	// 接口
	/** 登录接口 */
	String IF_login = "login";
	/** 站订单接口 */
	String IF_user_order = "user_order";
	/** 发送地理位置接口 */
	String IF_user_mgr = "user_mgr";
	/** 发送地理位置接口 */
	String IF_sms_mgr = "sms_mgr";
	/** 采购订单接口 */
	String IF_big_order = "big_order";
	/** 商品 */
	String IF_mgr_food = "mgr_food";
	/** 销售业绩 */
	String IF_statistic = "statistic";
	/** 客户营销数据*/
	String IF_stat = "stat";
	
	// 接口中的方法
	/** 送货员登录方法 */
	String MD_login_check_sender_user = "login_check_sender_user";
	/** 站点登录方法 */
	String MD_login_check_pc = "login_check_pc";
	/** 客户订单给送货员 */
	String MD_get_order_list_4_sender = "get_order_list_4_sender";
	/** 搜索订单 */
	String MD_get_list_order = "get_order_list";
	/** 搜索订单（强制出单） */
	String MD_get_detailstono_order = "get_detailstono_order";
	/** 客户订单详情 */
	String MD_get_orderdetails = "get_orderdetails";
	/** 客户订单已发货确认 */
	String MD_update_order_with_confirm = "update_order_with_confirm";
	/** 送货员地理位置 */
	String MD_insert_sender_position = "insert_sender_position";
	/** 送货员首页信息获取 */
	String MD_statistic_4_sender_a_day = "statistic_4_sender_a_day";
	/** 送货员群发信息 */
	String MD_tell_user_sender_comming = "tell_user_sender_comming";
	/** 送货员查历史订单 */
	String MD_get_record_by_send_user = "get_record_by_send_user";
	/** 获取送货员经纬度 */
	String MD_select_sender_user_position = "select_sender_user_position";
	/** 检测版本号 */
	String MD_get_latest_version_4_app = "get_latest_version_4_app";
	/** 站点采购大单 */
	String MD_get_purchase_or_supply_order_list = "get_purchase_or_supply_order_list";
	/** 站类别 */
	String MD_find_lines_site = "find_lines_site";
	/** 产品分类 */
	String MD_load_big_cls_and_first_sub_cls = "load_big_cls_and_first_sub_cls";
	/** 站点销量排行 */
	String MD_find_sales_time = "find_sales_time";
	/** 站点销售业绩 */
	String MD_get_all_ordernum = "get_all_ordernum";
	/** 获取站点销售业绩订单数量图表*/
	String MD_order_num = "order_num";
	/** 获取站点销售业绩销售额量图表*/
	String MD_order_sales = "order_sales";
	/** 获取站点销售业绩客单价量图表*/
	String MD_customer_price = "customer_price";
	/** 站点订单list */
	String MD_get_order_list = "get_order_list";
	/** 站点客户订单出货扫描确认 */
	String MD_order_banding = "order_banding";
	/** 站点客户订单强制出货 */
	String MD_upd_order_ahead_today = "upd_order_ahead_today";
	/** 客户营销数据 */
	String MD_shou_wechat_usesr_stat = "shou_wechat_usesr_stat";
	
	/** handler-what 访问网络异常 */
	int FAIL_NET = 400;
	/** handler-what 数据解析异常 */
	int FAIL_PARSER = 500;
	/** handler-what 数据获取成功 */
	int SUCCESS = 200;
	/** handler-what 服务器数据为空 */
	int FAIL_NULL = 300;
	/** 地理位置更新时间间隔 单位ms */
	int LOCATION_UPDATE_TIME = 10 * 1000;
	/** 地理位置上传服务器时间间隔 单位ms */
	int LOCATION_LOAD_TIME = 5 * 60 * 1000;
	/** 连接服务器失败时提示信息 */
	String EROORMSG = "网络状态欠佳，请稍后再试";
	/** 连接服务器状态码标示 */
	String STATUS = "status";
	/** 连接服务器状态消息标示 */
	String MSG = "msg";
	/** 连接服务器返回数据标示 */
	String RETURNDATA = "returnData";

}
