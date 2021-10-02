package com.clickshop.loja.utils;

import java.io.Serializable;

import lombok.Data;

@Data
public class Paginator implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer pageNumber;
	private Integer itemsPerPage;
	private String orderBy;
	private String direction;
	
	public Paginator(Integer pageNumber, Integer itemsPerPage, String orderBy, String direction) {
		this.pageNumber = pageNumber != null ? pageNumber : 0;
		this.itemsPerPage = itemsPerPage != null ? itemsPerPage : 24;
		this.orderBy = orderBy != null ? orderBy : "name";
		this.direction = direction != null ? direction : "DESC";
	}

	
}
