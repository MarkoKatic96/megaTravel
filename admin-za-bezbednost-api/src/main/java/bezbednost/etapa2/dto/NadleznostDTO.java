package bezbednost.etapa2.dto;

import bezbednost.etapa2.model.Nadleznost;

public class NadleznostDTO {
	private Long id;
	private Long rolaId;
	private Long servisId;
	
	public NadleznostDTO(Long rolaId, Long servisId) {
		super();
		this.rolaId = rolaId;
		this.servisId = servisId;
	}
	
	public NadleznostDTO(Nadleznost nadleznost) {
		this.id = nadleznost.getId();
		this.rolaId = nadleznost.getRolaId();
		this.servisId = nadleznost.getServisId();
	}
	
	public NadleznostDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRolaId() {
		return rolaId;
	}

	public void setRolaId(Long rolaId) {
		this.rolaId = rolaId;
	}

	public Long getServisId() {
		return servisId;
	}

	public void setServisId(Long servisId) {
		this.servisId = servisId;
	}
	
	
}
