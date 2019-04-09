package bezbednost.etapa2.dto;

import bezbednost.etapa2.model.Rola;

public class RolaDTO {
	
	private Long id;
	private String naziv;
	
	public RolaDTO(String naziv) {
		this.naziv = naziv;
	}
	
	public RolaDTO(Rola rola) {
		this.id = rola.getId();
		this.naziv = rola.getNaziv();
	}
	
	public RolaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
	
}
