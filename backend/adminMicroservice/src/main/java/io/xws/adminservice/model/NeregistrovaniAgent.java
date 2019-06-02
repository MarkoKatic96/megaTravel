package io.xws.adminservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.hibernate.annotations.CreationTimestamp;

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
	
}
