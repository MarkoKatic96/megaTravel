package io.xws.adminservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DodatneUsluge 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idDodatneUsluge;
    
    @XmlElement(required = true)
    protected String naziv;
    
    @ManyToMany(mappedBy = "listaDodatnihUsluga")
	private List<Smestaj> listaSmestaja;
}
