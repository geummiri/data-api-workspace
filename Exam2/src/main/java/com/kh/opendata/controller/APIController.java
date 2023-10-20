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
public class APIController {
	public static final String serviceKey = "UNmnLkcNtzgMKivBzvyb3TdrsqmthwquJWOHYpkKXK6aXtSdhG1gbTQ6EOqATL5t3ApCJ2hySkej4pCTXuaAZg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="api.do", produces="application/json; charset=utf-8")
	public String apiInfo(String stnId)  throws IOException{
		// URL (key값 제외한 주소)
		String url = "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst";
		
		url += "?serviceKey=" + serviceKey; 
		url += "&tmFc=202310200600";
		url += "&dataType=JSON"; // 기본값 xml이고 json으로 변경하고 싶으면 type 지정해주기
		url += "&stnId=" + stnId; 
		
//		ex)파라미터 값이 한글이라면?  임시적으로 하는것임
//		String stnName = "서울";
//		url += "&stnName=" + URLEncoder.encode(stnName, "UTF-8");
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET"); // 요청 방식

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		//setRequestMethod 응답 받은 것을 String 으로 받기
		//응답받을 변수 지정
		String responseText = "";
				
		while((line = br.readLine())!=null) {
			responseText += line;
		} 
		
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		
		return responseText;
	}
}