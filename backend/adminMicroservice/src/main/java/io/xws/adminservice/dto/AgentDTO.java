package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AgentDTO 
{
    protected Long idAgenta;
    protected String ime;
    protected String prezime;
    protected String poslovniMaticniBroj;
    protected Date datumClanstva;
    protected String email;
    protected String lozinka;
}
