package com.ticket.services;

import java.util.Map;

import com.ticket.data.DummyData;
import com.ticket.entities.UserDetail;

public class UserServiceImpl implements UserService {

	@Override
	public UserDetail findUserByEmailAndPassword(String email, String password) {
		
		Map<Integer, UserDetail> listOfUsers = DummyData.LIST_OF_USERS;
		for(Map.Entry<Integer, UserDetail> userDetails : listOfUsers.entrySet()) 
		{
			UserDetail userDetail = userDetails.getValue();
			if(userDetail.getEmail().equals(email) && userDetail.getPassword().equals(password)) {
				return userDetail;
			}
		}
		return null;
	}

}
