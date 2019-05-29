package io.xws.adminservice.dto;

import java.util.Date;

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
