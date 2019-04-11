package bezbednost.etapa2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//mozda nece trebati, moram jos videti
@Entity
@Table(name="nadleznost")
public class Nadleznost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long rolaId;//ili naziv
	
	@NotNull
	private Long servisId;//ili naziv

	public Nadleznost(Long id, Long rolaId, Long servisId) {
		super();
		this.id = id;
		this.rolaId = rolaId;
		this.servisId = servisId;
	}
	
	public Nadleznost(Long rolaId, Long servisId) {
		super();
		this.rolaId = rolaId;
		this.servisId = servisId;
	}
	
	public Nadleznost() {
		
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
