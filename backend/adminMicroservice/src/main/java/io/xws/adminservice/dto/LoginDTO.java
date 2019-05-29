package io.xws.adminservice.dto;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class LoginDTO
{
	protected String email;
    protected String lozinka;
}
