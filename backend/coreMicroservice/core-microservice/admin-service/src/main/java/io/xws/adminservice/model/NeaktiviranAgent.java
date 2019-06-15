package io.xws.adminservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Agent")
public class NeaktiviranAgent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
    private Long idNeaktiviranogAgenta;
	
	@NotNull
    @XmlElement(required = true)
    private String ime;
	
	@NotNull
    @XmlElement(required = true)
    private String prezime;
	
	@NotNull
	@XmlElement(required = true)
    private Long poslovniMaticniBroj;

	@NotNull
    @XmlElement(required = true)
    private String email;
	
    public NeaktiviranAgent() {
		super();
	}

	public NeaktiviranAgent(Long idNeaktiviranogAgenta, @NotNull String ime, @NotNull String prezime,
			@NotNull Long poslovniMaticniBroj, @NotNull String email) {
		super();
		this.idNeaktiviranogAgenta = idNeaktiviranogAgenta;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.email = email;
	}

	public Long getIdNeaktiviranogAgenta() {
		return idNeaktiviranogAgenta;
	}

	public void setIdNeaktiviranogAgenta(Long idNeaktiviranogAgenta) {
		this.idNeaktiviranogAgenta = idNeaktiviranogAgenta;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Long getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public void setPoslovniMaticniBroj(Long poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}