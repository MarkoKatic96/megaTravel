package bezbednost.etapa2.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;


@Entity
public class Rola {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1, max=100)
	private String naziv; //dobro bi bilo i da naziv role bude unikatan
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_services", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "service_id", referencedColumnName = "id"))
    private Collection<Servis> servisi;   

	public Rola(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	public Rola(String naziv) {
		super();
		this.naziv = naziv;
	}
	
	public Rola() {
		
	}

	public Collection<Servis> getServisi() {
		return servisi;
	}

	public void setServisi(Collection<Servis> servisi) {
		this.servisi = servisi;
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
