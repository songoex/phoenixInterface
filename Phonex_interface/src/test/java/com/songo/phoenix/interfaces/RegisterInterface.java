package com.songo.phoenix.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.songo.phoenix.config.RestConfig;
import com.songo.phoenix.util.ExcelUtil;
import com.songo.phoenix.util.HttpUtil;

public class RegisterInterface {
	@Test(dataProvider = "datas")
	public void test1(String caseId, String apiId, String requestData) throws Exception {

		// 准备URL
		String Url = RestConfig.getRestUrlByApiId(apiId);
		// 接口请求方式
		String type = RestConfig.getRestTypeByApiId(apiId);
		// 准备参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Map<String, String> map = (Map<String, String>) JSONObject.parse(requestData);
		Set<String> keys = map.keySet();
		for (String key : keys) {
			params.add(new BasicNameValuePair(key, map.get(key)));
		}
		String resultString = HttpUtil.process(type, Url, params);
		ExcelUtil.write("/rest_info.xlsx", 0, caseId, 6, resultString, "src/test/resources/rest_info.xlsx");

		// System.out.println("************" + resultString);

	}

	@DataProvider
	public Object[][] datas() {
		return ExcelUtil.read("/rest_info.xlsx", 1, 2, 7, 1, 3);
	}

	// public static void main(String[] args) {
	// String requestData =
	// "{\"mobilephone\":\"13517315121\",\"pwd\":\"123456\",\"regname\":\"柠檬班\"}";
	// Map<String, String> map = (Map<String, String>)
	// JSONObject.parse(requestData);
	// Set<String> keys = map.keySet();
	// for (String key : keys) {
	// System.out.println("key:" + key + ",value:" + map.get(key));
	// //11sp fenzhongs
	// }
	// }
}
