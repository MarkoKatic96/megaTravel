package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AdminDTO
{
	protected long idAdmina;
    protected String ime;
    protected String prezime;
    protected Date datumClanstva;
    protected String email;
    protected String lozinka;
}
