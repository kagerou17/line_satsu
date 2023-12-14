//package com.example.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//import com.linecorp.bot.model.event.FollowEvent;
//
//@Service
//public class LineUserRegService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	public void handleFollowEvent(FollowEvent event) {
//		String userId = event.getSource().getUserId();
//
//		// Check if the user already exists in the database
//		User user = userRepository.findByLineUserId(userId);
//
//		if (user == null) {
//			// If not, create a new user and save it to the database
//			User newUser = new User();
//			newUser.setLineUserId(userId);
//			userRepository.save(newUser);
//		}
//	}
//}
