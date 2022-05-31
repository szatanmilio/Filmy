package com.example.filmy.web.dto;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.tv.TvSeries;

import java.util.List;

public class TVStatusDto {
	TvSeries myProduction;
	String status;
	String type;

	public TVStatusDto(TvSeries myProduction, String status, String type) {
		this.myProduction = myProduction;
		this.status = status;
		this.type = type;
	}

	public TvSeries getMyProduction() {
		return myProduction;
	}

	public void setMyProduction(TvSeries myProduction) {
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
