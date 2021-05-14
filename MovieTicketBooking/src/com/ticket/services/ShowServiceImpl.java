package com.ticket.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ticket.data.ApplicationCache;
import com.ticket.data.DummyData;
import com.ticket.entities.ShowDetail;

public class ShowServiceImpl implements ShowService {

	@Override
	public int addShow(ShowDetail showDetail) {
		return ApplicationCache.getInstance().addShowData(showDetail);
	}

	@Override
	public List<ShowDetail> findShowByCurrentDate(Date date) {
		List<ShowDetail> list = ApplicationCache.getInstance().getShowData();
		List<ShowDetail> newList = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
		for(ShowDetail showDetail : list) {
			if(formatter.format(date).equals(formatter.format(showDetail.getStartDateTime()))) {
				newList.add(showDetail);
			}
		}
		return newList;
	}

	@Override
	public List<ShowDetail> findAll() {
		return ApplicationCache.getInstance().getShowData();
	}

	@Override
	public int updateShow(ShowDetail showDetail) {
		return 0;
	}

}
