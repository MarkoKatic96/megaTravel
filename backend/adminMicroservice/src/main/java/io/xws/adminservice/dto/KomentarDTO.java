package io.xws.adminservice.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

import lombok.Data;

@Data
public class KomentarDTO 
{
	protected long idKomentara;
    protected long idSmestaja;
    protected long idRezervacije;
    protected long idKorisnika;
    protected String komentar;
    protected Date datum;
    protected boolean objavljen;

}
