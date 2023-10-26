package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController1 {
	public static final String serviceKey = "MxfBeW51refx7%2FVJUJ%2B%2FOduUBx5GA5yOjblAdHX49EFvmAnN32mR8QCdLIsIFfECSbtTPjWtwQFzdAlb%2B6E%2BKg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="air.do", produces="application/json; charset=utf-8")
	public String apiInfo(String location)  throws IOException{
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
			
		url += "?serviceKey=" + serviceKey;
		url += "&returnType=json";
		String sidoName = location;
		url += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");


		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); // 요청 방식

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		

		String responseText = "";
				
		while((line = br.readLine())!=null) {
			responseText += line;
		} 
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}
}