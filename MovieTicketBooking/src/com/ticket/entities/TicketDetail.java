package com.ticket.entities;

public class TicketDetail {

	private int id;
	private int showId;
	private int movieId;
	private String customerName;
	private float price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + id;
		result = prime * result + movieId;
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + showId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketDetail other = (TicketDetail) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (id != other.id)
			return false;
		if (movieId != other.movieId)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (showId != other.showId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TicketDetail [id=" + id + ", showId=" + showId + ", movieId=" + movieId + ", customerName="
				+ customerName + ", price=" + price + "]";
	}
	
	
}
