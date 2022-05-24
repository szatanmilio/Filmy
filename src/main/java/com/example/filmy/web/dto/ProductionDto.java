package com.example.filmy.web.dto;

public class ProductionDto {
	private String type;
	private long idProduction;
	private String status;

	public ProductionDto() {

	}

	public ProductionDto(String type, long idProduction, String status) {
		this.type = type;
		this.idProduction = idProduction;
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getIdProduction() {
		return idProduction;
	}

	public void setIdProduction(long idProduction) {
		this.idProduction = idProduction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
