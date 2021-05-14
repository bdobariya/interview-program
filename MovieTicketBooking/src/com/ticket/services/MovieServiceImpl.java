package com.ticket.services;

import java.util.Map;

import com.ticket.data.ApplicationCache;
import com.ticket.entities.MovieDetail;

public class MovieServiceImpl implements MovieService {

	@Override
	public int addMovie(MovieDetail movie) {
		return ApplicationCache.getInstance().addMovieData(movie);
	}

	@Override
	public Map<String, MovieDetail> findAll() {
		return ApplicationCache.getInstance().getMovieData();
	}

}
