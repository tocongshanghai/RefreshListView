package com.example.refreshlistmodule.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionUtil {
	public static String sessionID;
	private static String CONNEXCEPTION = "connException";
	private static final String TAG = "HttpURLConnectionManage";
	private static Handler mHandler = new Handler(Looper.getMainLooper());
	private static Gson mGson = new Gson();

	/**
	 * 此方法描述的是： 同步Post方法
	 * 
	 * @param url
	 * @param params
	 * @return String
	 */
	public static String syncPost(String url, Map<String, Object> params) {
		return executePost(url, params);
	}

	/**
	 * 此方法描述的是： 异步Post方法
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 *            void
	 */
	@SuppressWarnings("rawtypes")
	public static void asynPost(final String url, final Map<String, Object> params, final ResultCallback callback) {
		new Thread() {
			public void run() {
				final String json = executePost(url, params);
				mHandler.post(new Runnable() {

					@SuppressWarnings("unchecked")
					@Override
					public void run() {
						if (CONNEXCEPTION.equals(json) || null == json || json.trim().length() == 0) {
							callback.onError(json);
						} else {
							try {
								if (callback.mType == String.class) {
									callback.onSuccess(json);
								} else {
									Object ob = mGson.fromJson(json, callback.mType);
									callback.onSuccess(ob);
								}
							} catch (com.google.gson.JsonParseException e) {
								callback.onError(e.toString());
							}

						}
					}
				});
			};
		}.start();
	}

	/**
	 * 此方法描述的是： HttpURLConnection post方法
	 * 
	 * @param url
	 *            void
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private static String executePost(String url, Map<String, Object> params) {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpURLConnection urlConnection = null;
		String returnData = null;
		String requestData = map2Params(params);
		byte[] entity = requestData.getBytes();
		try {
			urlConnection = getConnection(url);
			urlConnection.setRequestMethod("POST");
			urlConnection.setConnectTimeout(5000);
			if (sessionID != null) {
				urlConnection.setRequestProperty("cookie", sessionID);
			}
			urlConnection.setRequestProperty("Content-Length", entity.length + "");
			urlConnection.setRequestProperty("Content-Type", URLEncodedUtils.CONTENT_TYPE);
			urlConnection.setDoOutput(true);
			outputStream = urlConnection.getOutputStream();
			outputStream.write(entity);
			outputStream.flush();
			int responseCode = urlConnection.getResponseCode();
			Log.i(TAG, "responseCode:" + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				inputStream = urlConnection.getInputStream();
				returnData = readStream(inputStream);
				if ("".equals(returnData)) {
					Log.i(TAG, "returnData:" + (returnData.equals("") ? "\"\"" : returnData));
				}
			}
		} catch (IOException e) {
			returnData = CONNEXCEPTION;
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (urlConnection != null) {
				if (sessionID == null) {
					sessionID = getSessionID(urlConnection);
				}
				// 关闭连接
				urlConnection.disconnect();
				urlConnection = null;
			}
		}
		return returnData;
	}

	/**
	 * 此方法描述的是： 把map键对值转成对应参数格式
	 * 
	 * @param params
	 * @return String
	 */
	private static String map2Params(Map<String, Object> params) {
		StringBuilder requestData = new StringBuilder();
		if(!params.isEmpty()){
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (null != entry.getValue()) {
					requestData.append(entry.getKey()).append("=");
					requestData.append(entry.getValue().toString()).append("&");
				}
			}
			requestData.deleteCharAt(requestData.length() - 1);
		}
		return requestData.toString();
	}

	/**
	 * 此方法描述的是： 获得HttpURLConnection对象
	 * 
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 *             HttpURLConnection
	 */
	private static HttpURLConnection getConnection(String url) throws IOException {
		URL url1 = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
		return urlConnection;
	}

	/**
	 * 此方法描述的是： 获得SessionID
	 * 
	 * @param conn
	 *            void
	 */
	private static String getSessionID(HttpURLConnection conn) {
		StringBuilder sessionId = new StringBuilder();
		Map<String, List<String>> headerFields = conn.getHeaderFields();
		if (headerFields != null) {
			List<String> cookieval = headerFields.get("Set-Cookie");
			// String cookieval = conn.getHeaderField("Set-Cookie");
			if (cookieval != null) {
				for (String ck : cookieval) {
					if (ck.contains(";")) {
						sessionId.append(ck.substring(0, ck.indexOf(";"))).append(";");
					}
				}
				sessionId.deleteCharAt(sessionId.length() - 1);
				Log.i(TAG, "sessionId:" + sessionId.toString());
			}
		}
		return sessionId.toString().equals("") ? null : sessionId.toString();
	}

	/**
	 * 此方法描述的是： 将请求返回来的输入流转成String
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 *             String
	 */
	private static String readStream(InputStream inputStream) {
		byte buffer[] = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toString().trim();
	}

	public static abstract class ResultCallback<T> {
		Type mType;

		public ResultCallback() {
			mType = getSuperclassTypeParameter(getClass());
		}

		static Type getSuperclassTypeParameter(Class<?> subclass) {
			Type superclass = subclass.getGenericSuperclass();
			if (superclass instanceof Class) {
				throw new RuntimeException("Missing type parameter.");
			}
			ParameterizedType parameterized = (ParameterizedType) superclass;
			return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
		}

		public abstract void onError(String errorMsg);

		public abstract void onSuccess(T returnData_json);
	}
}
