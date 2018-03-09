package com.xm2013.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * http 请求工具类
 * @author xm2013.com
 *
 */
public class HttpTools {
	/**
	 * 发送get请求
	 */
	public static String sendGet(String url, String cookie, Map<String, String> resultCookies){

		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			if(cookie != null)
				connection.setRequestProperty("Cookie", cookie);

			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			getCookie(map, resultCookies);

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
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
	 * 获取文件
	 */
	public static byte[] sendGetMime(String url, String cookie){

		byte[] result = null;
		BufferedReader in = null;
		InputStream ins = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			if(cookie != null)
				connection.setRequestProperty("Cookie", cookie);

			// 建立实际的连接
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			ins = connection.getInputStream();
			result = new byte[ins.available()];
			ins.read(result);
//			System.out.println(Arrays.toString(result));
//			System.out.println(result.length);
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) in.close();
				if(ins != null) ins.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;

	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url 发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param, String cookies) {
		PrintWriter out = null;
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

			conn.setRequestProperty("Cookie",cookies);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}    
	
	/**
	 * 获取cookie
	 * @param headers
	 * @param resultCookies
	 */
	public static void getCookie(Map<String, List<String>> headers,  Map<String, String> resultCookies){
		// 遍历所有的响应头字段
		//Set-Cookie--->[JSESSIONID1=kk21WLso5Ax3xH9mWWUbi0yythS9ye-k62zxuN8xRZsJIvoFORao!-1593154385; path=/; HttpOnly]
		if(resultCookies!=null){
			for (String key : headers.keySet()) {
				System.out.println(key + "--->" + headers.get(key));
			}

			List<String> cookies = headers.get("Set-Cookie");
			if(cookies != null && cookies.size()>0){
				for(int i = 0; i<cookies.size(); i++){
					String value = cookies.get(i);
					String[] temps = value.split(";");
					if(temps.length > 0){
						String cookieStr = temps[0];
						String[] cookieArr = cookieStr.split("=");
						if(cookieArr.length>1){
							resultCookies.put(cookieArr[0], cookieArr[1]);
						}
					}
				}
			}

		}

	}
	
	
	
}
