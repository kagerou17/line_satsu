package com.example.demo.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.LineData;
import com.example.demo.service.CreateRichMenuService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class LineController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	CreateRichMenuService createRichMenuService;

	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "Vw8uJqKu4zdg6eJdZ0TlfH5vRbfCCEuKdKFyG+i6KpySmWKpyG5Hx/7nObmZSg21xOkxm+USjJ9Ej5jWbYxFSF87UvUhQ0jD6vhTiBCbXv9Rkh4l4q/iRZc7T7/f73eqtoLrStsoflsYVrZWErXocAdB04t89/1O/w1cDnyilFU=";

	public HttpSession replyToken1;

	@PostMapping("/Riko")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData, Model model, HttpSession session) throws IOException {

		for (Event event : webhookData.getEvents()) {

			String replyText = event.getMessage().getText();
			//メッセージを送ってきたアカウント情報を変数「replyToken」に格納する。
			String replyToken = event.getReplyToken();
			session.setAttribute("replyToken", replyToken);

			String lineId = event.getSource().getUserId();
			session.setAttribute("line_id", lineId);
			if (replyText.equals("初期設定")) {

				replyMessage(replyToken, "ユーザ登録をしてください：\r\n https://2e45-116-82-246-181.ngrok-free.app/sinkitouroku/"
						+ event.getSource().getUserId() + "\n" + "登録が終了したら「登録完了」と返信をしてください");
			}

			List<Map<String, Object>> resultlist3 = jdbcTemplate
					.queryForList("select gakkamei,class,gakunen from acount where line_id = ? ", lineId);

			session.setAttribute("class", (String) resultlist3.get(0).get("class"));
			session.setAttribute("gakkamei", (String) resultlist3.get(0).get("gakkamei"));
			session.setAttribute("gakunen", (String) resultlist3.get(0).get("gakunen"));

			///////////////☆☆重要☆☆///////////////////
			/////////////変数「replyText」に送られてきたメッセージが格納されている

			//ricgmenuメイン
			if (replyText.equals("ノートが選択されました")) {

				chooseArea(replyToken, "test");

			} else if (replyText.equals("イベントが選択されました")) {
				chooseArea1(replyToken, "test");

			} else if (replyText.equals("時間割が選択されました。")) {
				chooseArea2(replyToken, "test");

			} else if (replyText.equals("その他が選択されました。")) {
				chooseArea3(replyToken, "test");
			}

			//時間割リプライ
			else if (replyText.equals("月曜日課")) {
				List<Map<String, Object>> resultlist;
				resultlist = jdbcTemplate.queryForList(
						"select getu1,getu2,getu3,getu4 from nikka where class =  ? and gakkamei = ? and gakunen = ? ",
						session.getAttribute("class"), session.getAttribute("gakkamei"),
						session.getAttribute("gakunen"));
				String ichi = (String) resultlist.get(0).get("getu1");
				String ni = (String) resultlist.get(0).get("getu2");
				String san = (String) resultlist.get(0).get("getu3");
				String yon = (String) resultlist.get(0).get("getu4");
				replyMessage(replyToken,
						"月曜日の日課だよ！！\n 1限目　" + ichi + " \n 2限目　" + ni + "\n 3限目　" + san + "\n 4限目　" + yon);
			} else if (replyText.equals("火曜日課")) {

				List<Map<String, Object>> resultlist;
				resultlist = jdbcTemplate.queryForList(
						"select ka1,ka2,ka3,ka4 from nikka where class = ? and gakkamei = ? and gakunen = ? ",
						session.getAttribute("class"), session.getAttribute("gakkamei"),
						session.getAttribute("gakunen"));
				String ichi = (String) resultlist.get(0).get("ka1");
				String ni = (String) resultlist.get(0).get("ka2");
				String san = (String) resultlist.get(0).get("ka3");
				String yon = (String) resultlist.get(0).get("ka4");
				replyMessage(replyToken,
						"火曜日の日課だよ！！\n 1限目　" + ichi + " \n 2限目　" + ni + "\n 3限目　" + san + "\n 4限目　" + yon);
			} else if (replyText.equals("水曜日課")) {
				List<Map<String, Object>> resultlist;
				resultlist = jdbcTemplate.queryForList(
						"select sui1,sui2,sui3,sui4 from nikka where class = ? and gakkamei = ? and gakunen = ? ",
						session.getAttribute("class"), session.getAttribute("gakkamei"),
						session.getAttribute("gakunen"));
				String ichi = (String) resultlist.get(0).get("sui1");
				String ni = (String) resultlist.get(0).get("sui2");
				String san = (String) resultlist.get(0).get("sui3");
				String yon = (String) resultlist.get(0).get("sui4");
				replyMessage(replyToken,
						"水曜日の日課だよ！！\n 1限目　" + ichi + " \n 2限目　" + ni + "\n 3限目　" + san + "\n 4限目　" + yon);
			} else if (replyText.equals("木曜日課")) {
				List<Map<String, Object>> resultlist;
				resultlist = jdbcTemplate.queryForList(
						"select moku1,moku2,moku3,moku4 from nikka where class = ? and gakkamei = ? and gakunen = ? ",
						session.getAttribute("class"), session.getAttribute("gakkamei"),
						session.getAttribute("gakunen"));
				String ichi = (String) resultlist.get(0).get("moku1");
				String ni = (String) resultlist.get(0).get("moku2");
				String san = (String) resultlist.get(0).get("moku3");
				String yon = (String) resultlist.get(0).get("moku4");
				replyMessage(replyToken,
						"木曜日の日課だよ！！\n 1限目　" + ichi + " \n 2限目　" + ni + "\n 3限目　" + san + "\n 4限目　" + yon);
			} else if (replyText.equals("金曜日課")) {
				List<Map<String, Object>> resultlist;
				resultlist = jdbcTemplate.queryForList(
						"select kin1,kin2,kin3,kin4 from nikka where class = ? and gakkamei = ? and gakunen = ? ",
						session.getAttribute("class"), session.getAttribute("gakkamei"),
						session.getAttribute("gakunen"));
				String ichi = (String) resultlist.get(0).get("kin1");
				String ni = (String) resultlist.get(0).get("kin2");
				String san = (String) resultlist.get(0).get("kin3");
				String yon = (String) resultlist.get(0).get("kin4");
				replyMessage(replyToken,
						"金曜日の日課だよ！！\n 1限目　" + ichi + " \n 2限目　" + ni + "\n 3限目　" + san + "\n 4限目　" + yon);
			}
			//予定確認
			else if (replyText.equals("予定確認")) {
				chooseArea4(replyToken, "test");
			} 
			else if (replyText.equals("1月の予定を表示します")) {

				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("2月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("3月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("4月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("5月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("6月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("7月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("8月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			}

			else if (replyText.equals("9月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));
			} else if (replyText.equals("10月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));

			} else if (replyText.equals("11月の予定を表示します")) {
				jdbcTemplate.queryForList(
						"select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",
						session.getAttribute("line_id"));

			} else if (replyText.equals("12月の予定を表示します")) {
				System.out.println("12月の予定を表示します");
				List<Map<String, Object>> resultlist;
				resultlist = jdbcTemplate.queryForList("select timeschedule,content from event where MID(timeschedule,6,2) = 12 and line_id = ? ",lineId);
				
				String ichi =  (String) resultlist.get(0).get("timeschedule");
				String ni = (String) resultlist.get(0).get("content");
				
				replyMessage(replyToken, "12月の予定だよ！！\n 日程　" + ichi + " \n 用件　" + ni);
			}
			
			
		
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

	public void chooseArea1(String replyToken, String todohu) throws IOException {
		final String LINE_MESSAGE_API_ENDPOINT = "https://api.line.me/v2/bot/message/reply";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(channelAccessToken);

		// 上記のFlex Message JSONを作成する部分を追加
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode loadedJson = objectMapper.readTree(createRichMenuService.loadFlexMessageJson1(todohu));
		JsonNode innerMessages = loadedJson.get("messages");

		String requestBody = String.format("{"
				+ "\"replyToken\": \"%s\","
				+ "\"messages\": %s"
				+ "}", replyToken, innerMessages.toString());

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(LINE_MESSAGE_API_ENDPOINT, request, String.class);
	}

	public void chooseArea2(String replyToken, String todohu) throws IOException {
		final String LINE_MESSAGE_API_ENDPOINT = "https://api.line.me/v2/bot/message/reply";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(channelAccessToken);

		// 上記のFlex Message JSONを作成する部分を追加
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode loadedJson = objectMapper.readTree(createRichMenuService.loadFlexMessageJson2(todohu));
		JsonNode innerMessages = loadedJson.get("messages");

		String requestBody = String.format("{"
				+ "\"replyToken\": \"%s\","
				+ "\"messages\": %s"
				+ "}", replyToken, innerMessages.toString());

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(LINE_MESSAGE_API_ENDPOINT, request, String.class);
	}

	public void chooseArea3(String replyToken, String todohu) throws IOException {
		final String LINE_MESSAGE_API_ENDPOINT = "https://api.line.me/v2/bot/message/reply";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(channelAccessToken);

		// 上記のFlex Message JSONを作成する部分を追加
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode loadedJson = objectMapper.readTree(createRichMenuService.loadFlexMessageJson3(todohu));
		JsonNode innerMessages = loadedJson.get("messages");

		String requestBody = String.format("{"
				+ "\"replyToken\": \"%s\","
				+ "\"messages\": %s"
				+ "}", replyToken, innerMessages.toString());

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(LINE_MESSAGE_API_ENDPOINT, request, String.class);
	}

	public void chooseArea4(String replyToken, String todohu) throws IOException {
		final String LINE_MESSAGE_API_ENDPOINT = "https://api.line.me/v2/bot/message/reply";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(channelAccessToken);

		// 上記のFlex Message JSONを作成する部分を追加
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode loadedJson = objectMapper.readTree(createRichMenuService.loadFlexMessageJson4(todohu));
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
