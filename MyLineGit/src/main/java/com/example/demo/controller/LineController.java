package com.example.demo.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.LineData;
import com.example.demo.service.CreateRichMenuService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LineController {

	@Autowired
	CreateRichMenuService createRichMenuService;
	
	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "Vw8uJqKu4zdg6eJdZ0TlfH5vRbfCCEuKdKFyG+i6KpySmWKpyG5Hx/7nObmZSg21xOkxm+USjJ9Ej5jWbYxFSF87UvUhQ0jD6vhTiBCbXv9Rkh4l4q/iRZc7T7/f73eqtoLrStsoflsYVrZWErXocAdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/Riko")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData) throws IOException {
		for (Event event : webhookData.getEvents()) {

			//メッセージを送ってきたアカウント情報を変数「replyToken」に格納する。
			String replyToken = event.getReplyToken();

			///////////////☆☆重要☆☆///////////////////
			/////////////変数「replyText」に送られてきたメッセージが格納されている
			String replyText = event.getMessage().getText();

			if (replyText.equals("ノートが選択されました")) {
				
				chooseArea(replyToken,"test");
				replyMessage(replyToken, "月曜日の日課だよ！！\n 1限目　データベース応用 \n 2限目　React演習 \n 3限目　デジタル法制度");

			} else if (replyText.equals("イベントが選択されました")) {
				replyMessage(replyToken, "火曜日の日課だよ！！\n 1限目　なし \n 2限目　JAVAフレームワーク \n 3限目　卒業制作");
			} else if (replyText.equals("時間割が選択されました。")) {
				replyMessage(replyToken, "水曜日の日課だよ！！\n 1限目　卒業制作 \n 2限目　テスト技法 \n 3限目　なし");
			} else if (replyText.equals("その他が選択されました。")) {
				replyMessage(replyToken, "木曜日の日課だよ！！\n 1限目　AI演習 \n 2限目　C#演習 \n 3限目　卒業制作");
			} else if (replyText.equals("金曜日の日課")) {
				replyMessage(replyToken, "金曜日の日課だよ！！\n 1限目　卒業制作 \n 2限目　C言語検定 \n 3限目　JAVAフレームワーク");
			}

			//////////////ここまでを見てね。///////////////////////
		}
	}

	/*******************************************************************:
	 * ここから↓は今は気にしないでOK!
	 *******************************************************************/
	private void replyMessage(String replyToken, String replyText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", replyText);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

	////↑ここに貼る////
	public void chooseArea(String replyToken, String todohu) throws IOException {
		final String LINE_MESSAGE_API_ENDPOINT = "https://api.line.me/v2/bot/message/reply";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(channelAccessToken);

		// 上記のFlex Message JSONを作成する部分を追加
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode loadedJson = objectMapper.readTree(createRichMenuService.loadFlexMessageJson(todohu));
		JsonNode innerMessages = loadedJson.get("messages");

		String requestBody = String.format("{"
				+ "\"replyToken\": \"%s\","
				+ "\"messages\": %s"
				+ "}", replyToken, innerMessages.toString());

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(LINE_MESSAGE_API_ENDPOINT, request, String.class);
	}

}
