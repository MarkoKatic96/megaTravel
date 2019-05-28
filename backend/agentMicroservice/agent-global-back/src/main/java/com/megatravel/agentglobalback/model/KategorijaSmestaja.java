package com.megatravel.agentglobalback.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KategorijaSmestaja 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long idKategorijeSmestaja;
    
    @XmlElement(required = true)
    protected String naziv;
    
    @OneToMany(mappedBy="kategorijaSmestaja")
    protected List<Smestaj> listaSmestaja;
}
