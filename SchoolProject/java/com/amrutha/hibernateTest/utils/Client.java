package com.amrutha.hibernateTest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

public class Client {
	public static void main(String[] args) throws IOException {

		//Client data and url
		Client client = new Client();
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","TEXT/PLAIN");
		headers.put("Accept","APPLICATION/JSON");
		String data = "id,name,gender;121,Harish,Male;122,Amrutha,Female;123,Aryan,Male;";
		client.postCall("http://localhost:8080/HibernateTest-0.0.1-SNAPSHOT/student/add", headers, data);

	}

	public void postCall(String url, Map<String, String> headers, String data) throws IOException {
		URL myurl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");

		for (Entry<String, String> header : headers.entrySet()) {
			con.setRequestProperty(header.getKey(), header.getValue());
		}

		con.connect();
		con.getOutputStream().write(data.getBytes());

		int responseCode = con.getResponseCode();
		String responseData = "null";

		if (responseCode >= 200 && responseCode < 300) {
			InputStream inStream = con.getInputStream();
			responseData = IOUtils.toString(inStream, Charset.defaultCharset());
			
		}
		System.out.println("Response code " + responseCode + " Response message " + responseData);
		con.disconnect();

	}
}