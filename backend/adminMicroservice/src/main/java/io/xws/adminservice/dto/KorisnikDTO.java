package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class KorisnikDTO 
{
	protected long idKorisnik;
    protected String email;
    protected String ime;
    protected String prezime;
    protected String lozinka;
    protected Date datumClanstva;
    protected boolean registrovan;
    protected boolean blokiran;
    protected boolean aktiviran;
}
