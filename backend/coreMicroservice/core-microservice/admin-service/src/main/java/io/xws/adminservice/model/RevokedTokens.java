package io.xws.adminservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokedTokens
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTokena;
	
	@NotNull
	@Column(length=500)
	private String token;
	
	@NotNull
	@CreationTimestamp
	private Date revocationDate;

	public Long getIdTokena() {
		return idTokena;
	}

	public String getToken() {
		return token;
	}

	public Date getRevocationDate() {
		return revocationDate;
	}

	public void setIdTokena(Long idTokena) {
		this.idTokena = idTokena;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setRevocationDate(Date revocationDate) {
		this.revocationDate = revocationDate;
	}
}
