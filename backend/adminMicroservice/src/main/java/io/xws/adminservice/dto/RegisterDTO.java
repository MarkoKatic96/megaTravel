package io.xws.adminservice.dto;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class RegisterDTO 
{
    protected String email;
    protected String ime;
    protected String prezime;
    protected String lozinka;
    protected String potvrdaLozinke;

}
