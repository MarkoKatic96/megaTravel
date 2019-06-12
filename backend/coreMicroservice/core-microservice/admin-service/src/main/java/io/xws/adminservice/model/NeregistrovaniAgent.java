package io.xws.adminservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeregistrovaniAgent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idNeregAgenta;
	
	@NotNull
    @XmlElement(required = true)
    protected String ime;
	
	@NotNull
    @XmlElement(required = true)
    protected String prezime;
	
	@NotNull
    protected String poslovniMaticniBroj;
	
	@NotNull
    @XmlElement(required = true)
    protected String email;

	public Long getIdNeregAgenta() {
		return idNeregAgenta;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public String getEmail() {
		return email;
	}

	public void setIdNeregAgenta(Long idNeregAgenta) {
		this.idNeregAgenta = idNeregAgenta;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setPoslovniMaticniBroj(String poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
