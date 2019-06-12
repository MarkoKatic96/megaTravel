package io.xws.adminservice.service;

import io.xws.adminservice.dto.AdminDTO;
import io.xws.adminservice.dto.RegisterDTO;
import io.xws.adminservice.model.Admin;

public interface IAdminService {

	public Admin findByEmail(String email);

	public String signin(String email, String lozinka);

	public AdminDTO register(RegisterDTO registracija);

}
