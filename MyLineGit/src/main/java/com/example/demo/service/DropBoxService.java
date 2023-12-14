//package com.example.demo.service;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.dropbox.core.DbxAppInfo;
//import com.dropbox.core.DbxException;
//import com.dropbox.core.DbxRequestConfig;
//import com.dropbox.core.DbxSessionStore;
//import com.dropbox.core.DbxStandardSessionStore;
//import com.dropbox.core.DbxWebAuth;
//import com.dropbox.core.v2.DbxClientV2;
//
//@Service
//public class DropBoxService {
//	@Value("${dropbox.appKey}")
//	private String appKey;
//
//	@Value("${dropbox.appSecret}")
//	private String appSecret;
//
//	public DbxClientV2 getClient(HttpSession session, String accessToken) throws DbxException {
//		DbxRequestConfig config = new DbxRequestConfig("backup logger");
//		DbxAppInfo appInfo = new DbxAppInfo(appKey, appSecret);
//		DbxWebAuth webAuth = new DbxWebAuth(config, appInfo);
//
//		// セッションストアの初期化
//		DbxSessionStore sessionStore = new DbxStandardSessionStore(session, "your-session-key");
//
//		return new DbxClientV2(config, accessToken);
//	}
//
//}
