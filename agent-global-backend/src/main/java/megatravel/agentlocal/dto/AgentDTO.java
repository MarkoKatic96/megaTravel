package megatravel.agentlocal.dto;

import java.sql.Date;

import megatravel.agentlocal.model.AgentModel;

public class AgentDTO {
	
	private Long id;
	private String ime;
	private String prezime;
	private Long poslovniMaticniBroj;
	private Date datumClanstva;
	private String email;
	private boolean aktiviranNalog;
	
	public AgentDTO(Long id, String ime, String prezime, Long poslovniMaticniBroj, Date datumClanstva,
			String email, boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.datumClanstva = datumClanstva;
		this.email = email;
		this.aktiviranNalog = aktiviranNalog;
	}

	public AgentDTO(AgentModel agent) {
		super();
		this.id = agent.getId();
		this.ime = agent.getIme();
		this.prezime = agent.getPrezime();
		this.poslovniMaticniBroj = agent.getPoslovniMaticniBroj();
		this.datumClanstva = agent.getDatumClanstva();
		this.email = agent.getEmail();
		this.aktiviranNalog = agent.isAktiviranNalog();
	}
	
	public AgentDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isAktiviranNalog() {
		return aktiviranNalog;
	}

	public void setAktiviranNalog(boolean aktiviranNalog) {
		this.aktiviranNalog = aktiviranNalog;
	}

	public Long getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public void setPoslovniMaticniBroj(Long poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}

	public Date getDatumClanstva() {
		return datumClanstva;
	}

	public void setDatumClanstva(Date datumClanstva) {
		this.datumClanstva = datumClanstva;
	}
}
