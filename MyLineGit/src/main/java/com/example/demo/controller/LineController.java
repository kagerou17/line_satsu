package com.example.demo.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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

	@PostMapping("/Riko")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData,Model model,HttpSession session) throws IOException {
		
		
		
		
		for (Event event : webhookData.getEvents()) {

			
			//メッセージを送ってきたアカウント情報を変数「replyToken」に格納する。
			String replyToken = event.getReplyToken();
			session.setAttribute("replyToken", replyToken);
			
			
			///////////////☆☆重要☆☆///////////////////
			/////////////変数「replyText」に送られてきたメッセージが格納されている
			String replyText = event.getMessage().getText();
			if (replyText.equals("初期設定")) {
				session.setAttribute("line_id",event.getSource().getUserId() );
				replyMessage(replyToken, "ユーザ登録をしてください：\r\n https://2e45-116-82-246-181.ngrok-free.app/sinkitouroku/" + event.getSource().getUserId()+"\n"+"登録が終了したら「登録完了」と返信をしてください");
			}
			
			//ricgmenuメイン
			if (replyText.equals("ノートが選択されました")) {
				
				chooseArea(replyToken,"test");
				

			} else if (replyText.equals("イベントが選択されました")) {
				chooseArea1(replyToken,"test");
				
			} else if (replyText.equals("時間割が選択されました。")) {
				chooseArea2(replyToken,"test");
				
			} else if (replyText.equals("その他が選択されました。")) {
				chooseArea3(replyToken,"test");
			} 
		 
			//時間割リプライ
			else if (replyText.equals("月曜日課")) {
				List<Map<String, Object>> resultlist;
		    	resultlist = jdbcTemplate.queryForList("select getu1,getu2,getu3,getu4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
				String ichi =  (String)resultlist.get(0).get("getu1");
				String ni =  (String)resultlist.get(0).get("getu2");
				String san =  (String)resultlist.get(0).get("getu3");
				String yon =  (String)resultlist.get(0).get("getu4");
				replyMessage(replyToken, "月曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
			}else if (replyText.equals("火曜日課")) {
				
				List<Map<String, Object>> resultlist;
		    	resultlist = jdbcTemplate.queryForList("select ka1,ka2,ka3,ka4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
				String ichi =  (String)resultlist.get(0).get("ka1");
				String ni =  (String)resultlist.get(0).get("ka2");
				String san =  (String)resultlist.get(0).get("ka3");
				String yon =  (String)resultlist.get(0).get("ka4");
				replyMessage(replyToken, "火曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
				}else if (replyText.equals("水曜日課")) {
					List<Map<String, Object>> resultlist;
			    	resultlist = jdbcTemplate.queryForList("select sui1,sui2,sui3,sui4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
					String ichi =  (String)resultlist.get(0).get("sui1");
					String ni =  (String)resultlist.get(0).get("sui2");
					String san =  (String)resultlist.get(0).get("sui3");
					String yon =  (String)resultlist.get(0).get("sui4");
					replyMessage(replyToken, "水曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
				}else if (replyText.equals("木曜日課")) {
					List<Map<String, Object>> resultlist;
			    	resultlist = jdbcTemplate.queryForList("select moku1,moku2,moku3,moku4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
					String ichi =  (String)resultlist.get(0).get("moku1");
					String ni =  (String)resultlist.get(0).get("moku2");
					String san =  (String)resultlist.get(0).get("moku3");
					String yon =  (String)resultlist.get(0).get("moku4");
					replyMessage(replyToken, "木曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
				}else if (replyText.equals("金曜日課")) {
					List<Map<String, Object>> resultlist;
			    	resultlist = jdbcTemplate.queryForList("select kin1,kin2,kin3,kin4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
					String ichi =  (String)resultlist.get(0).get("kin1");
					String ni =  (String)resultlist.get(0).get("kin2");
					String san =  (String)resultlist.get(0).get("kin3");
					String yon =  (String)resultlist.get(0).get("kin4");
					replyMessage(replyToken, "金曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
				}
			//予定確認
				else if (replyText.equals("予定確認")) {
					chooseArea4(replyToken,"test");
				} 
				else if (replyText.equals("１月の予定を表示します")) {

					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("２月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("３月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("４月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("５月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("６月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("７月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("８月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
			
				else if (replyText.equals("９月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
				else if (replyText.equals("１０月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
					
				}else if (replyText.equals("１１月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
					
				}else if (replyText.equals("１２月の予定を表示します")) {
					jdbcTemplate.queryForList("select * from event where timeschedule = MID(timeschedule,5,2) and line_id = ? ",session.getAttribute("line_id"));
				}
			//sessionへ突っ込む
			    else if (replyText.equals("登録完了")) {
			    	List<Map<String, Object>> resultlist;
			    	resultlist = jdbcTemplate.queryForList("select class,kamokumei,gakunen from acount where lineid = ?  ",session.getAttribute("line_id") );
			    	session.setAttribute("class",(String) resultlist.get(0).get("class") );
			    	session.setAttribute("kamokumei",(String) resultlist.get(0).get("kamokumei") );
			    	session.setAttribute("gakunen",(String) resultlist.get(0).get("gakunen") );
			    
                    replyMessage(replyToken, "登録完了しました！");
                }
			

	        
			Timer timer = new Timer();
			List<Map<String, Object>> resultlist;
	    	resultlist = jdbcTemplate.queryForList("select nikka_hour,nikka_minute from acount where lineid = ?  ",session.getAttribute("line_id") );
	    	session.setAttribute("nikka_hour", (int)resultlist.get(0).get("nikka_hour") );
	    	session.setAttribute("nikka_minute",(int) resultlist.get(0).get("nikka_minute") );
	    	
	        // タスクを設定する時間
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.HOUR_OF_DAY, (int)session.getAttribute("nikka_hour")); // 時を設定
	        calendar.set(Calendar.MINUTE, (int)session.getAttribute("nikka_minute")); // 分を設定
	        calendar.set(Calendar.SECOND, 0); // 0秒に設定

	        Date time = calendar.getTime();

	        timer.schedule(new SendScheduleTask(), time);
	    }
	}

	class SendScheduleTask extends TimerTask {
	    public void run(HttpSession session) {
	        // LINEに送信するメッセージを作成
	    	Calendar calendar = Calendar.getInstance();
	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
if (dayOfWeek == Calendar.MONDAY) {
	List<Map<String, Object>> resultlist;
	resultlist = jdbcTemplate.queryForList("select getu1,getu2,getu3,getu4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
	String ichi =  (String)resultlist.get(0).get("getu1");
	String ni =  (String)resultlist.get(0).get("getu2");
	String san =  (String)resultlist.get(0).get("getu3");
	String yon =  (String)resultlist.get(0).get("getu4");
	replyMessage((String)session.getAttribute("replyToken"), "月曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);

}else if (dayOfWeek == Calendar.TUESDAY) {

	List<Map<String, Object>> resultlist;
	resultlist = jdbcTemplate.queryForList("select ka1,ka2,ka3,ka4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
	String ichi =  (String)resultlist.get(0).get("ka1");
	String ni =  (String)resultlist.get(0).get("ka2");
	String san =  (String)resultlist.get(0).get("ka3");
	String yon =  (String)resultlist.get(0).get("ka4");
	replyMessage((String)session.getAttribute("replyToken"), "火曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
	
}else if (dayOfWeek == Calendar.WEDNESDAY) {
	List<Map<String, Object>> resultlist;
	resultlist = jdbcTemplate.queryForList("select sui1,sui2,sui3,sui4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
	String ichi =  (String)resultlist.get(0).get("sui1");
	String ni =  (String)resultlist.get(0).get("sui2");
	String san =  (String)resultlist.get(0).get("sui3");
	String yon =  (String)resultlist.get(0).get("sui4");
	replyMessage((String)session.getAttribute("replyToken"), "水曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
	
}else if (dayOfWeek == Calendar.THURSDAY) {
	List<Map<String, Object>> resultlist;
	resultlist = jdbcTemplate.queryForList("select moku1,moku2,moku3,moku4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
	String ichi =  (String)resultlist.get(0).get("moku1");
	String ni =  (String)resultlist.get(0).get("moku2");
	String san =  (String)resultlist.get(0).get("moku3");
	String yon =  (String)resultlist.get(0).get("moku4");
	replyMessage((String)session.getAttribute("replyToken"), "木曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
}else if (dayOfWeek == Calendar.FRIDAY) {
	List<Map<String, Object>> resultlist;
	resultlist = jdbcTemplate.queryForList("select kin1,kin2,kin3,kin4 from nikka where class = '?' and kamokumei = ? and gakunen = ? ",session.getAttribute("class"),session.getAttribute("kamokumei"),session.getAttribute("gakunen"));
	String ichi =  (String)resultlist.get(0).get("kin1");
	String ni =  (String)resultlist.get(0).get("kin2");
	String san =  (String)resultlist.get(0).get("kin3");
	String yon =  (String)resultlist.get(0).get("kin4");
	replyMessage((String)session.getAttribute("replyToken"), "金曜日の日課だよ！！\n 1限目　"+ichi+" \n 2限目　"+ni+ "\n 3限目　"+san+ "\n 4限目　"+yon);
	
	}else if (dayOfWeek == Calendar.SATURDAY) {
		replyMessage((String)session.getAttribute("replyToken"), "土曜日はお休みだよ！！ゆっくり休んでね。");
		}else if (dayOfWeek == Calendar.SUNDAY) {
			replyMessage((String)session.getAttribute("replyToken"), "日曜日はお休みだよ！！明日は学校だよ忘れ物の無いようにね");
	}


	   
	    }

		@Override
		public void run() {
			// TODO 自動生成されたメソッド・スタブ
			
		}
	        }
		
		

			
			//////////////ここまでを見てね。///////////////////////
		
	

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
