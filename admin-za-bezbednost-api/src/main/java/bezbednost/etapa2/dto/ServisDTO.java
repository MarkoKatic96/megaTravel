package bezbednost.etapa2.dto;

import bezbednost.etapa2.model.Servis;

public class ServisDTO {
	
	private Long id;
	private String naziv;
	
	public ServisDTO(String naziv) {
		this.naziv = naziv;
	}
	
	public ServisDTO(Servis servis) {
		this.id = servis.getId();
		this.naziv = servis.getNaziv();
	}
	
	public ServisDTO() {
		
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
