package bezbednost.etapa2.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="servis")
public class Servis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1, max=100)
	private String naziv; 	//dobro bi bilo i da naziv servisa bude unikatan
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "servisi")
    private Collection<Rola> roles;

	public Servis(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	public Servis(String naziv) {
		super();
		this.naziv = naziv;
	}
	
	public Servis() {
		
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
