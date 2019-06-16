package io.xws.adminservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CreationTimestamp;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "RevokedTokens")
@Entity
public class RevokedTokens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
	private Long idTokena;
	
	@NotNull
	@Column(length=500)
	@XmlElement(required = true)
	private String token;
	
	@NotNull
	@CreationTimestamp
	@XmlElement(required = true)
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
