package com.ticket.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ticket.entities.MovieDetail;
import com.ticket.entities.ShowDetail;
import com.ticket.entities.TicketDetail;

public class ApplicationCache {

	public static final int CACHE_MOVIEDETAIL_DATA = 1;
	public static final int CACHE_SHOWDETAIL_DATA = 2;
	public static final int CACHE_TICKETDETAIL_DATA = 3;
	
	private Map<Object, Object> cacheMap = new HashMap<>();
	
	private ApplicationCache()
	{
	}
	
	private static ApplicationCache aMang = null;
	public static synchronized ApplicationCache getInstance()
	{
		if (aMang == null)
		{
			aMang = new ApplicationCache();
		}
		return aMang;
	}
	
	private boolean ifCacheExists(int i) {
		return cacheMap.get(i) != null;
	}
	
	public Map<String, MovieDetail> getMovieData() {
		if (!ifCacheExists(CACHE_MOVIEDETAIL_DATA)) {
			cacheMap.put(CACHE_MOVIEDETAIL_DATA, new HashMap<String, MovieDetail>());
		}
		return (HashMap<String, MovieDetail>) cacheMap.get(CACHE_MOVIEDETAIL_DATA);
	}
	
	public int addMovieData(MovieDetail movieDetail) {
		if (!ifCacheExists(CACHE_MOVIEDETAIL_DATA)) {
			movieDetail.setId(1);
			Map<String, MovieDetail> map = new HashMap<>();
			map.put(movieDetail.getName(), movieDetail);
			cacheMap.put(CACHE_MOVIEDETAIL_DATA, map);
			return 1;
		} else {
			Map<String, MovieDetail> list = (Map<String, MovieDetail>) cacheMap.get(CACHE_MOVIEDETAIL_DATA);
			int id = list.size()+1;
			movieDetail.setId(id);
			list.put(movieDetail.getName(), movieDetail);
			return id;
		}
	}
	
	public List<ShowDetail> getShowData() {
		if (!ifCacheExists(CACHE_SHOWDETAIL_DATA)) {
			cacheMap.put(CACHE_SHOWDETAIL_DATA, new ArrayList<ShowDetail>());
		}
		return (List<ShowDetail>) cacheMap.get(CACHE_SHOWDETAIL_DATA);
	}
	
	public int addShowData(ShowDetail showDetail) {
		if (!ifCacheExists(CACHE_SHOWDETAIL_DATA)) {
			showDetail.setId(1);
			cacheMap.put(CACHE_SHOWDETAIL_DATA, getFreshList(showDetail));
			return 1;
		} else {
			List<ShowDetail> list = (List<ShowDetail>) cacheMap.get(CACHE_SHOWDETAIL_DATA);
			if(!list.contains(showDetail)) {
				int id = list.size()+1;
				showDetail.setId(id);
				list.add(showDetail);
				return id;
			}
		}
		return 0;
	}
	
	public List<TicketDetail> getTicketData() {
		if (!ifCacheExists(CACHE_TICKETDETAIL_DATA)) {
			cacheMap.put(CACHE_TICKETDETAIL_DATA, new ArrayList<TicketDetail>());
		}
		return (List<TicketDetail>) cacheMap.get(CACHE_TICKETDETAIL_DATA);
	}
	
	public int addTicketData(TicketDetail ticketDetail) {
		if (!ifCacheExists(CACHE_TICKETDETAIL_DATA)) {
			ticketDetail.setId(1);
			cacheMap.put(CACHE_TICKETDETAIL_DATA, getFreshList(ticketDetail));
			return 1;
		} else {
			List<TicketDetail> list = (List<TicketDetail>) cacheMap.get(CACHE_TICKETDETAIL_DATA);
			if(!list.contains(ticketDetail)) {
				int id = list.size()+1;
				ticketDetail.setId(id);
				list.add(ticketDetail);
				return id;
			}
		}
		return 0;
	}
	
	public void clearCache(int i) {
		cacheMap.remove(i);
		cacheMap.put(i, null);
	}
	
	public void clearAllCache() {
		cacheMap.clear();
	}
	
	private List getFreshList(Object obj) {
		List freshList = new ArrayList<>();
		freshList.add(obj);
		return freshList;
	}
}
