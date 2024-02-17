//package com.example.demo.service;
//
//import java.time.LocalDate;
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
//public class PushKishoWhetherService {
//	public String getWeather() {
//		String cityCode = "140000";
//		StringBuilder responseMessage = new StringBuilder();
//		OkHttpClient client = new OkHttpClient();
//
//		// 気象庁のAPIエンドポイント
//		String endpoint = "https://www.jma.go.jp/bosai/forecast/data/forecast/" + cityCode + ".json";
//
//		// 天気予報のリクエストを作成
//		Request weatherRequest = new Request.Builder()
//				.url(endpoint)
//				.build();
//
//		// 天気予報のレスポンスを取得
//		try (Response weatherResponse = client.newCall(weatherRequest).execute()) {
//			String weatherJsonData = weatherResponse.body().string();
//			JSONArray weatherJsonArray = new JSONArray(weatherJsonData);
//
//			// 天気予報のJSONから情報を取得
//			JSONObject weatherJsonObject = weatherJsonArray.getJSONObject(0);
//			JSONArray timeSeriesArray = weatherJsonObject.getJSONArray("timeSeries");
//
//			// 天気の取得
//			JSONObject firstTimeSeriesObject = timeSeriesArray.getJSONObject(0);
//			JSONArray firstAreasArray = firstTimeSeriesObject.getJSONArray("areas");
//			String weather = firstAreasArray.getJSONObject(0).getJSONArray("weathers").getString(0);
//			weather = weather.replace("　", " ");
//
//			// 日付の取得
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM月dd日");
//			String todayDate = LocalDate.now().format(dtf);
//
//			responseMessage.append("📅 ").append(todayDate).append("の天気予報 📅\n").append("---------------\n")
//					.append("本日は「").append(weather).append("」予報。\n");
//
//			// 降水確率の取得と計算
//			JSONObject secondTimeSeriesObject = timeSeriesArray.getJSONObject(1);
//			JSONArray secondAreasArray = secondTimeSeriesObject.getJSONArray("areas");
//			JSONArray pops = secondAreasArray.getJSONObject(0).getJSONArray("pops");
//			int popsSum = 0;
//			for (int p = 0; p < pops.length(); p++) {
//				popsSum += pops.getInt(p);
//			}
//			int popsAvg = popsSum / pops.length();
//			responseMessage.append("💧 降水確率は「").append(popsAvg).append("％」です！\n");
//
//			// 気温の取得
//			JSONObject thirdTimeSeriesObject = timeSeriesArray.getJSONObject(2);
//			JSONArray thirdAreasArray = thirdTimeSeriesObject.getJSONArray("areas");
//			for (int a = 0; a < thirdAreasArray.length(); a++) {
//				JSONObject areaObject = thirdAreasArray.getJSONObject(a);
//				String areaName = areaObject.getJSONObject("area").getString("name");
//				JSONArray tempsArray = areaObject.getJSONArray("temps");
//				String minTemp = tempsArray.getString(2);
//				String maxTemp = tempsArray.getString(tempsArray.length() - 1);
//
//				responseMessage.append("\n🌡️ ").append(areaName).append("の気温\n")
//						.append("最低気温：").append(minTemp).append("℃\n")
//						.append("最高気温：").append(maxTemp).append("℃\n");
//			}
//
//			responseMessage.append("---------------\n");
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		return responseMessage.toString();
//	}
//}
