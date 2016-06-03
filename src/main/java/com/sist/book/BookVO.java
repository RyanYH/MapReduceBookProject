package com.sist.book;

public class BookVO {
	private int	no;
	private String title;
	private String author;
	private String publisher;
	private int oprice;	// origin	  price
	private int sprice;	// discounted price
	private int rank;
	private String regdate;
	private String review;
	private String poster;
	private String priceData;
	private String titleData;
	
	public String getPriceData() {
		return priceData;
	}
	public void setPriceData(String priceData) {
		this.priceData = priceData;
	}
	public String getTitleData() {
		return titleData;
	}
	public void setTitleData(String titleData) {
		this.titleData = titleData;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getOprice() {
		return oprice;
	}
	public void setOprice(int oprice) {
		this.oprice = oprice;
	}
	public int getSprice() {
		return sprice;
	}
	public void setSprice(int sprice) {
		this.sprice = sprice;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
}
