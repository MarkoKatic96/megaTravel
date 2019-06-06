package com.megatravel.agentlocalbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class RevokedTokens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTokena;
	
	@NotNull
	@Column(length=500)
	private String token;
	
	@NotNull
	@CreationTimestamp
	private Date revocationDate;
	
	public RevokedTokens() {
	
	}

	public RevokedTokens(Long idPoruke, @NotNull String token) {
		super();
		this.idTokena = idPoruke;
		this.token = token;
		this.revocationDate = new Date(System.currentTimeMillis());
	}

	public Long getIdPoruke() {
		return idTokena;
	}

	public void setIdPoruke(Long idPoruke) {
		this.idTokena = idPoruke;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getRevocationDate() {
		return revocationDate;
	}

	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}
}
