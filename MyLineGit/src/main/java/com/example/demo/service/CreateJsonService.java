package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CreateJsonService {

	public JsonNode loadFlexMessageJson() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = null;

		ClassPathResource resource = new ClassPathResource("json/wreport.json");
		try {
			File jsonFile = resource.getFile();
			rootNode = objectMapper.readTree(jsonFile);

			// "messages"配列の最初の要素にアクセス
			JsonNode messagesNode = rootNode.path("messages");
			if (messagesNode.isArray()) {
				JsonNode firstMessageNode = messagesNode.get(0);

				// "contents" -> "body" -> "contents"へのパスを辿る
				JsonNode contentsNode = firstMessageNode.path("contents");

				//body部
				JsonNode bodyNode = contentsNode.path("body");
				JsonNode bodyContentsNode = bodyNode.path("contents");

				// "contents"配列の要素を順番に処理
				if (bodyContentsNode.isArray()) {
					for (JsonNode contentNode : bodyContentsNode) {
						JsonNode innerContentsNode = contentNode.path("contents");
						if (innerContentsNode.isArray()) {
							for (JsonNode innerContent : innerContentsNode) {
								// "url"プロパティを見つけたら値を更新
								if (innerContent.has("url")) {
									((ObjectNode) innerContent).put("url",
											"https://www.jma.go.jp/bosai/forecast/img/100.svg");
								}
							}
						}
					}
				}
			}
			// 更新後のJSONデータを出力
			return rootNode;
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 更新後のJSONデータを出力
		return rootNode;
	}

}
