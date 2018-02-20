package com.ningmengban.phoenix.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.ningmengban.phoenix.util.ExcelUtil;

public class RegisterInterface {
	@Test
	public void test1(String caseId, String apiId, String requestData) throws Exception {
		// 准备URL
		String url = "http://119.23.241.154:8080/futureloan/mvc/api/member/register";
		// 参数提交方式
		// HttpPost post = new HttpPost(url);
		// 准备参数
		Map<String, String> map = (Map<String, String>) JSONObject.parse(requestData);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			params.add(new BasicNameValuePair(key, map.get(key)));
		}

		// params.add(new BasicNameValuePair("mobilephone", "18520796150"));
		// params.add(new BasicNameValuePair("password",
		// "1ef6171fc1adc6c6385c9d62136d19be"));
		// 将参数设置到post请求当中
		// post.setEntity(new UrlEncodedFormEntity(params));
		// 发送请求
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		// CloseableHttpResponse response = httpClient.execute(post);
		// 获取接口返回数据
		// String jsonStr = EntityUtils.toString(response.getEntity());
		// System.out.println(jsonStr);
		// String resultString = HttpUtil.getResultStringByPost(url, params);
		// System.out.println("************" + resultString);
	}

	public Object[][] datas() {
		return ExcelUtil.read("/rest_info.xlsx", 1, 2, 7, 1, 3);
	}

	public static void main(String[] args) {
		String requestData = "{\"mobilephone\":\"13517315121\",\"pwd\":\"123456\",\"regname\":\"柠檬班\"}";
		Map<String, String> map = (Map<String, String>) JSONObject.parse(requestData);
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println("key:" + key + ",value:" + map.get(key));
			//
		}
	}
}
