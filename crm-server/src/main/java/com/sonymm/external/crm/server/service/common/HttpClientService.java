package com.sonymm.external.crm.server.service.common;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class HttpClientService {
	// 启动spring boot服务的IP地址
	protected static final String IP = "http://127.0.0.1:8080/";
	protected CloseableHttpClient httpclient;
	/**
	 * get请求HttpClient返回实体或error 
	 * @param address 请求地址
	 * @return
	 */
	protected String getHttpClient(String address) {
		String result = "";
		try {
			HttpGet httpGet = new HttpGet(address);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (response.getStatusLine().getStatusCode() == 200) {
						// 获取响应结果json
						result = EntityUtils.toString(entity);
						EntityUtils.consume(entity);
					} else {
						result = "error";
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * post请求HttpClient返回执行结果
	 * @param address 请求地址
	 * @param formparams 请求参数
	 * @return
	 */
	protected String postHttpClient(String address, List<NameValuePair> formparams) {
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(address);
			// 创建请求参数队列
			UrlEncodedFormEntity uefEntity;
			if (formparams != null && formparams.size() > 0) {
				uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httpPost.setEntity(uefEntity);
			}
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if (response.getStatusLine().getStatusCode() == 200) {
						result = EntityUtils.toString(entity);
						EntityUtils.consume(entity);
					} else {
						result = "error";
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
