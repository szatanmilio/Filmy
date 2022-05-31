package com.example.filmy.web.dto;

import info.movito.themoviedbapi.model.MovieDb;

import java.util.List;

public class MovieStatusDto {
	MovieDb myProduction;
	String status;
	String type;

	public MovieStatusDto(MovieDb myProduction, String status, String type) {
		this.myProduction = myProduction;
		this.status = status;
		this.type = type;
	}

	public MovieDb getMyProduction() {
		return myProduction;
	}

	public void setMyProduction(MovieDb myProduction) {
		this.myProduction = myProduction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
