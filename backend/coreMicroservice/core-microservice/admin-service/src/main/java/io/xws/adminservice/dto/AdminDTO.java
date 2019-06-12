package io.xws.adminservice.dto;

public class AdminDTO
{
	protected long idAdmina;
    protected String ime;
    protected String prezime;
    protected String email;
    protected String lozinka;
	public long getIdAdmina() {
		return idAdmina;
	}
	public void setIdAdmina(long idAdmina) {
		this.idAdmina = idAdmina;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
