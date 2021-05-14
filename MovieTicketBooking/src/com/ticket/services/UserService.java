package com.ticket.services;

import com.ticket.entities.UserDetail;

public interface UserService {

	UserDetail findUserByEmailAndPassword(String email, String password);
}
