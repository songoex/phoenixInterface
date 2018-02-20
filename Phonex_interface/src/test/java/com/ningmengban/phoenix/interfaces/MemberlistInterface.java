package com.ningmengban.phoenix.interfaces;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class MemberlistInterface {
	@Test
	public void list() throws ClientProtocolException, IOException {
		// 定义接口有地址
		String interfaceUrl = "http://119.23.241.154:8080/futureloan/mvc/api/member/list";

		// 决定提交方式
		HttpGet httpGet = new HttpGet(interfaceUrl);

		// 直接提交接口请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(httpGet);

		// 获取响应数据
		String jasonString = EntityUtils.toString(response.getEntity());
		System.out.println(jasonString);
	}

}
