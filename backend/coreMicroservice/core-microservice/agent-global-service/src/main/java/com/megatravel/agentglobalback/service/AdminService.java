package com.megatravel.agentglobalback.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.NeaktiviranAgent;
import com.megatravel.agentglobalback.repository.AgentRepository;
import com.megatravel.agentglobalback.repository.NeaktiviranAgentRepository;

@Service
public class AdminService 
{
	@Autowired
	private NeaktiviranAgentRepository neregRepo;
	
	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private MailServiceImpl mailService;
	
	
	
	public List<NeaktiviranAgent> getAllZahteviNeregAgenata()
	{
		Optional<List<NeaktiviranAgent>> agenti = Optional.of(neregRepo.findAll());
		
		if(agenti.isPresent())
		{
			return agenti.get();
		}
		else
			return null;
	}


	public String createPotvrdiZahtev(Long id)
	{
		Optional<NeaktiviranAgent> nereg = neregRepo.findById(id);
		//treba validacija za poslovni broj i email
		
		if(!nereg.isPresent())
			return "ERR";
		
		
		//ova provera treba da ide na agent-service
//		for(NeregistrovaniAgent agent : this.getAllZahteviNeregAgenata())
//		{
//			if(agent.getEmail().equals(nereg.get().getEmail()))
//				return "POSTOJI-MAIL";
//			else if(agent.getPoslovniMaticniBroj().equals(nereg.get().getPoslovniMaticniBroj()))
//				return "POSTOJI-PMB";
//		}
		
		for(Agent agent : agentRepo.findAll())
		{
			if(agent.getEmail().equals(nereg.get().getEmail()))
				return "POSTOJI-MAIL";
			else if(agent.getPoslovniMaticniBroj().equals(nereg.get().getPoslovniMaticniBroj()))
				return "POSTOJI-PMB";
		}
		
		
//		if(zahtev.getLozinka() != null || zahtev.getPoslovniMaticniBroj() != null) //proverava se prvo na agentu
//			return false;
		
//		Random rand = new Random();
//		
//		int pt1 = rand.nextInt(99);
//		int pt2 = rand.nextInt(99);
//		int pt3 = rand.nextInt(99);
//		String pbm = "10" + pt1 + pt2 + pt3;
//		
//		while(true)
//		{
//			if(agentRepo.existsByPoslovniMaticniBroj(pbm))
//			{
//				pt2 = rand.nextInt(99);
//				pbm = "10" + pt1 + pt2 + pt3;
//				continue;
//			}
//			else
//				break;
//		}
		
		String lozinka = UUID.randomUUID().toString().split("-")[0];
		
		Agent agent = new Agent();
		agent.setIme(nereg.get().getIme());
		agent.setPrezime(nereg.get().getPrezime());
		agent.setEmail(nereg.get().getEmail());
		agent.setLozinka(lozinka);
		agent.setPoslovniMaticniBroj(nereg.get().getPoslovniMaticniBroj());\
		agent.setDatumClanstva(new Date(id));
		
		neregRepo.delete(nereg.get());
		agentRepo.save(agent);
		
		try {
			mailService.sendNotificaitionAsync(agent);
		} catch (MailException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "OK";
	}


	public boolean deleteOdbijZahtev(Long id)
	{
		Optional<NeaktiviranAgent> agent = neregRepo.findById(id);
		
		if(agent.isPresent())
		{
			neregRepo.deleteById(agent.get().getIdNeaktiviranogAgenta());
			return true;
		}
		else
			return false;
	}

}
