package edu.pnu.util;

import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomMyUtil {
	// OAuth2User 정보를 이용해서 임의의 사용자 아이디를 생성하는 메서드
	// 인증 서버를 추가하려면 이 메서드 수정
	@SuppressWarnings("unchecked")
	public static String getUsernameFromOAuth2User(OAuth2User user) {
		// 로그인 성공 후 인증서버에서 보내준 속성 정보 추출
		Map<String, Object> attmap = user.getAttributes();
		String userString = (String)user.toString();
		String ret = "";
		
		//Google
		if (userString.contains("http://www.googleapis.com")) {
			ret = "Google_" + attmap.get("name") + "_" + attmap.get("sub");
		}else if(userString.contains("response=")) {
			Map<String, Object> resmap = (Map<String, Object>)attmap.get("response");
			ret = "Naver_" + resmap.get("name" )+ "_" + resmap.get("id");
		}else if(userString.contains("http://k.kakaocdn.net")) {
			Map<String, Object> propmap = (Map<String, Object>)attmap.get("properties");
			ret = "Kakao" + propmap.get("nickname") + "_" + attmap.get("id");
		}else if(userString.contains("http://api.github.com")) {
			ret = "Github_" + attmap.get("name") + "_" + attmap.get("id");
		}else {
			ret = "Facebook_" + attmap.get("name") + "_" + attmap.get("id");
		}
			
		// 만들어진 아이디에 ",", " " 제거
		ret = ret.replaceAll(",", "_").replaceAll(" ", "_");
		return ret;
	}

}
