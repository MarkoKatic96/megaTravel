package io.xws.adminservice.dto;

import io.xws.adminservice.model.Smestaj;
import lombok.Data;

@Data
public class TAdresaDTO {
	
	protected Long adresaId;
	protected Smestaj smestaj;
	protected String grad;
    protected String ulica;
    protected int broj;
    
	public Long getAdresaId() {
		return adresaId;
	}
	public Smestaj getSmestaj() {
		return smestaj;
	}
	public String getGrad() {
		return grad;
	}
	public String getUlica() {
		return ulica;
	}
	public int getBroj() {
		return broj;
	}
	public void setAdresaId(Long adresaId) {
		this.adresaId = adresaId;
	}
	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
}
