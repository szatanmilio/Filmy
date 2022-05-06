package com.example.filmy.web.dto;

import com.example.filmy.model.Lists;


public class MovieSaveDto {
	private Integer Rating;

	private enum status {
		OBEJRZANY, OGLADAM, PLANUJE;
	}

	private Lists list;

	public MovieSaveDto() {
	}

	public MovieSaveDto(Integer rating, Lists list) {
		Rating = rating;
		this.list = list;
	}

	public Integer getRating() {
		return Rating;
	}

	public void setRating(Integer rating) {
		Rating = rating;
	}

	public Lists getList() {
		return list;
	}

	public void setList(Lists list) {
		this.list = list;
	}
}
