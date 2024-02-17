package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class TwitterTrendsService {

	private final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAOq1owEAAAAAdYDYBFX4H84g%2BeTH62m6fY79p9c%3Dgtyua3MW9H3nCePGa9DooZJXe6HwZrAjXxkhJNnXV6Asmfyprm";

	public String getTwitterTrends() {
		try {
			// Twitter APIからトレンドを取得
			String url = "https://api.twitter.com/1.1/trends/place.json?id=1"; // WOEID=1118370は東京を示す
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + BEARER_TOKEN);
			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

			// JSONレスポンスからトレンドを取り出し、必要な形に整形
			JsonArray jsonArray = JsonParser.parseString(response).getAsJsonArray();
			JsonArray trends = jsonArray.get(0).getAsJsonObject().get("trends").getAsJsonArray();
			StringBuilder sb = new StringBuilder();
			sb.append("トレンド\n");
			for (int i = 0; i < trends.size(); i++) {
				JsonObject trend = trends.get(i).getAsJsonObject();
				String name = trend.get("name").getAsString();
				sb.append("トレンド").append(i + 1).append("位：").append(name).append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "トレンドの取得に失敗しました。";
		}
	}
}
