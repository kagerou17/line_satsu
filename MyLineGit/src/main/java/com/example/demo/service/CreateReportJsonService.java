//package com.example.demo.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.pocket.ConvertCodeAndCity;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//@Service
//public class CreateReportJsonService {
//
//	public JsonNode loadFlexMessageJson(String cityCode) throws IOException {
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
//
//			//目的物①　天気コード
//			String weatherCode = firstAreasArray.getJSONObject(0).getJSONArray("weatherCodes").getString(0);
//
//			//目的物②　天気予報
//			weather = weather.replace("　", " ");
//
//			// 降水確率の取得と計算
//			JSONObject secondTimeSeriesObject = timeSeriesArray.getJSONObject(1);
//			JSONArray secondAreasArray = secondTimeSeriesObject.getJSONArray("areas");
//			JSONArray pops = secondAreasArray.getJSONObject(0).getJSONArray("pops");
//
//			//目的物③　降水確率
//			String popMorning = pops.getString(0) + "%";
//			String popNoon = pops.getString(1) + "%";
//			String popNight = pops.getString(2) + "%";
//
//			// 気温の取得
//			JSONObject thirdTimeSeriesObject = timeSeriesArray.getJSONObject(2);
//			JSONArray thirdAreasArray = thirdTimeSeriesObject.getJSONArray("areas");
//
//			//目的物④　各地方のmin,max
//			List<Map<String, String>> minmaxs = new ArrayList<Map<String, String>>();
//
//			for (int a = 0; a < thirdAreasArray.length(); a++) {
//				JSONObject areaObject = thirdAreasArray.getJSONObject(a);
//				String areaName = areaObject.getJSONObject("area").getString("name");
//				JSONArray tempsArray = areaObject.getJSONArray("temps");
//				String minTemp = "";
//				String maxTemp = "";
//				if (tempsArray.length() > 1) {
//					minTemp = tempsArray.getString(2);
//					maxTemp = tempsArray.getString(tempsArray.length() - 1);
//				} else {
//					minTemp = tempsArray.getString(0);
//					maxTemp = tempsArray.getString(1);
//				}
//
//				Map<String, String> tempMinMax = new HashMap<String, String>();
//
//				tempMinMax.put("areaName", areaName);
//				tempMinMax.put("min", minTemp + "℃");
//				tempMinMax.put("max", maxTemp + "℃");
//
//				minmaxs.add(tempMinMax);
//			}
//			/////////
//			ObjectMapper objectMapper = new ObjectMapper();
//			JsonNode rootNode = null;
//
//			ClassPathResource resource = new ClassPathResource("json/wreport.json");
//			File jsonFile = resource.getFile();
//			rootNode = objectMapper.readTree(jsonFile);
//
//			// "messages"配列の最初の要素にアクセス
//			JsonNode messagesNode = rootNode.path("messages");
//			if (messagesNode.isArray()) {
//				JsonNode firstMessageNode = messagesNode.get(0);
//
//				// "contents" -> "body" -> "contents"へのパスを辿る
//				JsonNode contentsNode = firstMessageNode.path("contents");
//
//				//headerの取得
//				JsonNode headerNode = contentsNode.path("header");
//				//ヘッダの文字列を都道府県に更新.
//				ConvertCodeAndCity conv = new ConvertCodeAndCity();
//				((ObjectNode) headerNode.path("contents").get(0)).put("text",
//						"🌈 " + conv.codeToCity(cityCode) + "の気象情報 🌈");
//
//				//bodyの取得
//				JsonNode bodyNode = contentsNode.path("body");
//				JsonNode bodyContentsNode = bodyNode.path("contents");
//
//				// "contents"配列の要素を順番に処理
//				if (bodyContentsNode.isArray()) {
//					for (JsonNode contentNode : bodyContentsNode) {
//						JsonNode innerContentsNode = contentNode.path("contents");
//						if (innerContentsNode.isArray()) {
//							for (JsonNode innerContent : innerContentsNode) {
//
//								//天気のアイコンを設定
//								if (innerContent.has("url")) {
//									((ObjectNode) innerContent).put("url",
//											"https://www.jma.go.jp/bosai/forecast/img/" + weatherCode + ".png");
//								}
//								//気温を設定（気温はinnerContentから更にcontentsに潜る必要がある)
//								if (innerContent.has("contents")) {
//									((ObjectNode) innerContent.path("contents").get(1)).put("text",
//											minmaxs.get(0).get("max"));
//									((ObjectNode) innerContent.path("contents").get(3)).put("text",
//											minmaxs.get(0).get("min"));
//								}
//								//降水確率と各地域の気温を設定
//								if (innerContent.has("text")) {
//									String textValue = innerContent.path("text").asText();
//									if ("地点A".equals(textValue) && minmaxs.size() == 1) {
//										((ObjectNode) innerContent).put("text", minmaxs.get(0).get("areaName") + "の気温："
//												+ minmaxs.get(0).get("max") + "/ " + minmaxs.get(0).get("min"));
//									}
//									if ("地点B".equals(textValue) && minmaxs.size() == 1) {
//										((ObjectNode) innerContent).put("text", "　");
//									}
//									if ("地点A".equals(textValue) && minmaxs.size() == 2) {
//										((ObjectNode) innerContent).put("text", minmaxs.get(0).get("areaName") + "の気温："
//												+ minmaxs.get(0).get("max") + "/ " + minmaxs.get(0).get("min"));
//									}
//									if ("地点B".equals(textValue) && minmaxs.size() == 2) {
//										((ObjectNode) innerContent).put("text", minmaxs.get(1).get("areaName") + "の気温："
//												+ minmaxs.get(1).get("max") + "/ " + minmaxs.get(1).get("min"));
//									}
//
//									if (textValue.equals("朝の降水確率")) {
//										((ObjectNode) innerContent).put("text", "朝の降水確率：" + popMorning);
//									} else if (textValue.equals("昼の降水確率")) {
//										((ObjectNode) innerContent).put("text", "昼の降水確率：" + popNoon);
//									} else if (textValue.equals("夜の降水確率")) {
//										((ObjectNode) innerContent).put("text", "夜の降水確率：" + popNight);
//									} else if (textValue.equals("天気予報本体")) {
//										((ObjectNode) innerContent).put("text", weather);
//									} else if (textValue.equals("地点A") && minmaxs.size() >= 3) {
//										((ObjectNode) innerContent).put("text", minmaxs.get(1).get("areaName") + "の気温："
//												+ minmaxs.get(1).get("max") + "/ " + minmaxs.get(1).get("min"));
//									} else if (textValue.equals("地点B") && minmaxs.size() >= 3) {
//										((ObjectNode) innerContent).put("text", minmaxs.get(2).get("areaName") + "の気温："
//												+ minmaxs.get(2).get("max") + "/ " + minmaxs.get(2).get("min"));
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			return rootNode;
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
//	}
//}
