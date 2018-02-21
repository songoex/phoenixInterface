package com.songo.phoenix.util;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	// post请求通用处理函数
	/**
	 * 
	 * @param uri：接口地址
	 * @param params：提交参数
	 * @return：响应数据
	 */
	public static String getResultStringByPost(String uri, List<NameValuePair> params) {
		if (StringUtils.isEmpty(uri)) {
			return "";
		}
		// 创建HTTPpost对象
		HttpPost httpPost = new HttpPost(uri);
		String resultString = "";

		// 设置参数到httppost对象中
		try {
			// 设置参数到httppost对象中
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			// 准备客户端对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 发送请求
			CloseableHttpResponse response = httpClient.execute(httpPost);
			// 解析数据
			resultString = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultString;
	}

	public static String getResultStringByGet(String uri, List<NameValuePair> params) {
		if (StringUtils.isEmpty(uri)) {
			return "";
		}
		if (params == null) {
			return "";
		}
		for (int i = 0; i < params.size(); i++) {
			NameValuePair nameValuePair = params.get(i);
			if (i == 0) {

				uri += ("?" + nameValuePair.getName() + "=" + nameValuePair.getValue());
			} else {
				uri += ("&" + nameValuePair.getName() + "=" + nameValuePair.getValue());
			}

		}
		// 创建HTTPpost对象
		HttpGet httpGet = new HttpGet(uri);
		String resultString = "";

		// 设置参数到httpGet对象中
		try {
			// 设置参数到httpget对象中

			// 准备客户端对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 发送请求
			CloseableHttpResponse response = httpClient.execute(httpGet);
			// 解析数据
			resultString = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultString;
	}

}
