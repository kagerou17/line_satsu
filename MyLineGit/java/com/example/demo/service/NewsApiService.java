//package com.example.demo.service;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Service;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//@Service
//public class NewsApiService {
//
//	public String getNews() {
//		String newsResponse = "";
//		OkHttpClient client = new OkHttpClient();
//		String api_key = "ccc0dc450c7d4a43b79a223fd8ad330a";
//
//		Request request = new Request.Builder()
//				.url("https://newsapi.org/v2/top-headlines?country=jp&apiKey=" + api_key)
//				.build();
//
//		try (Response response = client.newCall(request).execute()) {
//			String jsonData = response.body().string();
//			JSONObject jsonObject = new JSONObject(jsonData);
//			JSONArray articles = jsonObject.getJSONArray("articles");
//			LocalDateTime date = null;
//
//			ZoneId jst = ZoneId.of("Asia/Tokyo");
//
//			for (int i = 0; i < 3; i++) {
//				JSONObject article = articles.getJSONObject(i);
//				String title = article.getString("title");
//				String author = "";
//				String url = article.getString("url");
//
//				String publishedAt = article.getString("publishedAt");
//				date = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME);
//				date = date.atZone(ZoneOffset.UTC).withZoneSameInstant(jst).toLocalDateTime();
//
//				if (i == 0) {
//					newsResponse += "＜注目のニュース＞\n";
//				}
//
//				if (!article.isNull("author") && article.get("author") instanceof String) {
//					author = article.getString("author");
//				} else {
//					author = "someone";
//				}
//				newsResponse += "【" + author + "】\n"
//						+ title + "\n"
//						+ url + "\n\n";
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return newsResponse;
//	}
//}
