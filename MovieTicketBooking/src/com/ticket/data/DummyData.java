package com.ticket.data;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import com.ticket.entities.UserDetail;

public class DummyData {

	public static final String DATE_FORMAT = "d/M/yyyy h:mm a";
	
	public static final Map<Integer, UserDetail> LIST_OF_USERS;
    static {
    	
    	UserDetail user1 = new UserDetail();
		user1.setId(1);
		user1.setFirstName("Admin");
		user1.setLastName("User");
		user1.setRole(1);	
		user1.setEmail("admin.user@gmail.com");
		user1.setPassword("Admin@123");
		
		UserDetail user2 = new UserDetail();
		user2.setId(2);
		user2.setFirstName("Customer");
		user2.setLastName("User");
		user2.setRole(2);	
		user2.setEmail("customer.user@gmail.com");
		user2.setPassword("Admin@123");
		
        Map<Integer, UserDetail> aMap = new HashMap<>();
        aMap.put(1, user1);
        aMap.put(2, user2);
        LIST_OF_USERS = Collections.unmodifiableMap(aMap);
    }
}
