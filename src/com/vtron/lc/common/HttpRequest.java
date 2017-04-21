package com.vtron.lc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ObjectUtils.Null;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.fusesource.hawtbuf.BufferInputStream;

public class HttpRequest {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
//		PrintWriter out = null;
		OutputStreamWriter out=null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setRequestProperty("Charset", "utf-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
//			out = new PrintWriter(conn.getOutputStream());
			out=new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
//			out.print(param);
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(new
			// InputStreamReader(conn.getInputStream()));
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost2(String path,String param){
		URL url=null;
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			url=new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			out = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
			out.write(param);
			out.flush();
		int ok=	HttpURLConnection.HTTP_OK;
			System.out.println(httpURLConnection.getResponseCode());
			 in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws DocumentException, UnsupportedEncodingException {
		String aString=(String)null;
		// 发送 GET 请求
		// String s =
		// HttpRequest.sendGet("http://localhost:6144/Home/RequestString",
		// "key=123&v=456");
		// System.out.println(s);
		// String trustKeyStore = "D:/tomcat.keystore";
		// System.setProperty("javax.net.ssl.trustStore", trustKeyStore);
		// System.setProperty("javax.net.ssl.trustStorePassword", "vtroncorp");
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/postdata.xml"));
		String messageText = document.asXML();
		// System.out.println(messageText);
		// messageText = URLEncoder.encode(messageText, "UTF-8");
		// 发送 POST 请求
		String sr = HttpRequest.sendPost2("https://v2006738a.vtroncorp.com:8443/InternationalTrade/declare",
				"clientId=CO0000000033&key=12345678&messageText=" + messageText);
		System.out.println(sr);
		// JSONObject result = JSONObject.fromObject(sr);
		// System.out.println(result.getString("description"));
		// document = DocumentHelper.parseText(result.getString("description"));
		// String description = document.asXML();
		// System.out.println(description);
	}
}