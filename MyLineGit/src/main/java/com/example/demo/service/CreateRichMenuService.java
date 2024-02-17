package com.example.demo.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CreateRichMenuService {

	public String loadFlexMessageJson(String fileName) throws IOException {
		String jsonName = "/static/json/shimadzu.json";
		ClassPathResource resource = new ClassPathResource(jsonName);
		return new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
	}
	public String loadFlexMessageJson1(String fileName) throws IOException {
		String jsonName = "/static/json/event.json";
		ClassPathResource resource = new ClassPathResource(jsonName);
		return new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
	}
	public String loadFlexMessageJson2(String fileName) throws IOException {
		String jsonName = "/static/json/timeschedule.json";
		ClassPathResource resource = new ClassPathResource(jsonName);
		return new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
	}
	public String loadFlexMessageJson3(String fileName) throws IOException {
		String jsonName = "/static/json/others.json";
		ClassPathResource resource = new ClassPathResource(jsonName);
		return new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
	}
	public String loadFlexMessageJson4(String fileName) throws IOException {
		String jsonName = "/static/json/schedule.json";
		ClassPathResource resource = new ClassPathResource(jsonName);
		return new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);
	}
}
