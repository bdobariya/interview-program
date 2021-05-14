package com.ticket.services;

import java.util.Date;
import java.util.List;

import com.ticket.entities.ShowDetail;

public interface ShowService {

	public int addShow(ShowDetail showDetail);
	public List<ShowDetail> findShowByCurrentDate(Date date);
	public List<ShowDetail> findAll();
	public int updateShow(ShowDetail showDetail);
}
