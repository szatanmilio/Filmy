package com.example.filmy.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "production")
public class Production {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;
	@Basic
	@Column(name = "id_production")
	private long idProduction;
	@Basic
	@Column(name = "type")
	private String type;
	@Basic
	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_user", nullable = false)
	private User userByIdUser;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdProduction() {
		return idProduction;
	}

	public void setIdProduction(long idProduction) {
		this.idProduction = idProduction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Production that = (Production) o;
		return id == that.id && idProduction == that.idProduction;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idProduction);
	}

	public User getUserByIdUser() {
		return userByIdUser;
	}

	public void setUserByIdUser(User userByIdUser) {
		this.userByIdUser = userByIdUser;
	}
}
