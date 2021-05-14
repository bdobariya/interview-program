package com.ticket.services;

import java.util.Map;

import com.ticket.entities.MovieDetail;

public interface MovieService {
	
	public int addMovie(MovieDetail movie);
	public Map<String, MovieDetail> findAll();
}
