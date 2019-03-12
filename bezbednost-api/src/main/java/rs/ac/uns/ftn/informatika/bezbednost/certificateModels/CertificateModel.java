package rs.ac.uns.ftn.informatika.bezbednost.certificateModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="sertifikati")
public class CertificateModel {

	@Id //kazemo da je id 
	@GeneratedValue (strategy=GenerationType.IDENTITY) //generisemo id
	@Column (name="id") //ime kolone
	private Long id;
	
}
