package com.songo.phoenix.interfaces;

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
		// pre the ip
		String interfaceUrl = "http://119.23.241.154:8080/futureloan/mvc/api/member/list";

		// pre the send tyoe
		HttpGet httpGet = new HttpGet(interfaceUrl);

		// pre the api
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(httpGet);

		// get the repayment data
		String jasonString = EntityUtils.toString(response.getEntity());
		System.out.println(jasonString);
	}

}
