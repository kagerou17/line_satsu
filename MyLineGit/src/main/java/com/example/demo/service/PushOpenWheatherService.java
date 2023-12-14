//package com.example.demo.service;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.time.format.TextStyle;
//import java.util.Locale;
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
//public class PushOpenWheatherService {
//
//	// 紫外線指数を元に強さを文字列で取得するメソッド
//	private String getUVIntensity(double uvIndex) {
//		if (uvIndex < 3)
//			return "非常に弱い";
//		else if (uvIndex < 6)
//			return "弱め";
//		else if (uvIndex < 8)
//			return "強め";
//		else
//			return "非常に強い";
//	}
//
//	// 風速を元に強さを文字列で取得するメソッド
//	private String getWindStrength(double windSpeed) {
//		if (windSpeed < 2)
//			return "弱い";
//		else if (windSpeed < 5)
//			return "中程度";
//		else if (windSpeed < 8)
//			return "強め";
//		else
//			return "非常に強い";
//	}
//
//	public String[] getWeather() {
//		String[] responseMessages = new String[2];
//		OkHttpClient client = new OkHttpClient();
//		String api_key = "1ba8b4fbaac51186591425f6194869ee";
//		String city = "inage";
//
//		// 天気予報のリクエストを作成
//		Request weatherRequest = new Request.Builder()
//				.url("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + api_key
//						+ "&units=metric&lang=ja")
//				.build();
//
//		// 天気予報のレスポンスを取得
//		try (Response weatherResponse = client.newCall(weatherRequest).execute()) {
//			String weatherJsonData = weatherResponse.body().string();
//			JSONObject weatherJsonObject = new JSONObject(weatherJsonData);
//
//			// 天気予報のJSONから緯度と経度を取得
//			double lat = weatherJsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lat");
//			double lon = weatherJsonObject.getJSONObject("city").getJSONObject("coord").getDouble("lon");
//
//			// 紫外線指数のリクエストを作成
//			Request uvIndexRequest = new Request.Builder()
//					.url("http://api.openweathermap.org/data/2.5/uvi?appid=" + api_key
//							+ "&lat=" + lat + "&lon=" + lon)
//					.build();
//
//			double uvIndex = 0;
//			// 紫外線指数のレスポンスを取得
//			try (Response uvIndexResponse = client.newCall(uvIndexRequest).execute()) {
//				String uvIndexJsonData = uvIndexResponse.body().string();
//				JSONObject uvIndexJsonObject = new JSONObject(uvIndexJsonData);
//				uvIndex = uvIndexJsonObject.getDouble("value");
//			}
//
//			JSONArray list = weatherJsonObject.getJSONArray("list");
//
//			// ① - 日々の天気予報
//			JSONObject firstForecast = list.getJSONObject(0);
//			String weatherDescription = firstForecast.getJSONArray("weather").getJSONObject(0).getString("description");
//			if (weatherDescription.equals("雲")) {
//				weatherDescription = "くもり";
//			} else if (weatherDescription.equals("薄い雲")) {
//				weatherDescription = "うすい曇り";
//			} else if (weatherDescription.equals("厚い雲")) {
//				weatherDescription = "どんより曇り";
//			}
//			Double minTemp = firstForecast.getJSONObject("main").getDouble("temp_min");
//			Double maxTemp = firstForecast.getJSONObject("main").getDouble("temp_max");
//			Double windSpeed = firstForecast.getJSONObject("wind").getDouble("speed"); // 風速を取得
//			Double rainProbability = firstForecast.has("rain") ? firstForecast.getJSONObject("rain").getDouble("3h")
//					: 0;
//
//			long unixSeconds = firstForecast.getLong("dt");
//			LocalDateTime forecastDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixSeconds),
//					ZoneId.systemDefault());
//			String dayOfWeek = forecastDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.JAPANESE);
//			String formattedDate = forecastDateTime.format(DateTimeFormatter.ofPattern("M月d日"));
//
//			long sunsetUnixSeconds = weatherJsonObject.getJSONObject("city").getLong("sunset");
//			LocalDateTime sunsetDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(sunsetUnixSeconds),
//					ZoneId.systemDefault());
//			String sunsetTime = sunsetDateTime.format(DateTimeFormatter.ofPattern("H時mm分"));
//
//			String uvIntensity = getUVIntensity(uvIndex);
//			String windStrength = getWindStrength(windSpeed);
//
//			responseMessages[0] = String.format(
//					"【%s（%s）】\n天気は「%s」の予報。\n最低気温は「%.1f℃」\n最高気温は「%.1f℃」\n降水確率は「%.1f％」です。\n\n紫外線の強さは「%s」\n風の強さは「%s（%.1f m/s）」です。\n%s頃、日没予定です。\nお出かけの際の参考に！",
//					formattedDate, dayOfWeek, weatherDescription, minTemp, maxTemp,
//					rainProbability, uvIntensity, windStrength, windSpeed, sunsetTime);
//
//			// ② - 3時間ごとの天気予報
//			StringBuilder threeHourlyForecastMessage = new StringBuilder();
//			LocalDate initialDate = forecastDateTime.toLocalDate();
//
//			for (int i = 0; i < list.length(); i++) {
//				JSONObject forecast = list.getJSONObject(i);
//				long threeHourUnixSeconds = forecast.getLong("dt");
//				LocalDateTime threeHourForecastDateTime = LocalDateTime
//						.ofInstant(Instant.ofEpochSecond(threeHourUnixSeconds), ZoneId.systemDefault());
//
//				// 予報日が初日と違う場合は処理を終了
//				if (!threeHourForecastDateTime.toLocalDate().equals(initialDate)) {
//					break;
//				}
//
//				String threeHourlyWeatherDescription = forecast.getJSONArray("weather").getJSONObject(0)
//						.getString("description");
//				if (threeHourlyWeatherDescription.equals("雲")) {
//					threeHourlyWeatherDescription = "くもり";
//				} else if (threeHourlyWeatherDescription.equals("薄い雲")) {
//					threeHourlyWeatherDescription = "うすい曇り";
//				} else if (threeHourlyWeatherDescription.equals("厚い雲")) {
//					threeHourlyWeatherDescription = "どんより曇り";
//				}
//				Double temp = forecast.getJSONObject("main").getDouble("temp");
//				Double rainVolume = forecast.has("rain") ? forecast.getJSONObject("rain").optDouble("3h", 0.0) : 0.0;
//				String forecastTime = threeHourForecastDateTime.format(DateTimeFormatter.ofPattern("H時"));
//
//				threeHourlyForecastMessage.append(String.format(
//						"【%s】\n天気は「%s」\n気温は「%.1f℃」\n降水量は「%.1f mm」\n",
//						forecastTime, threeHourlyWeatherDescription, temp, rainVolume));
//			}
//			responseMessages[1] = threeHourlyForecastMessage.toString();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return responseMessages;
//	}
//}