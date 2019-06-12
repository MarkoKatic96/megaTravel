package io.xws.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import io.xws.adminservice.converter.DTOAgentConverter;
import io.xws.adminservice.dto.AgentDTO;
import io.xws.adminservice.dto.NeregistrovaniAgentDTO;
import io.xws.adminservice.model.Agent;
import io.xws.adminservice.model.NeregistrovaniAgent;
import io.xws.adminservice.repository.AgentRepository;
import io.xws.adminservice.repository.NeregistrovaniAgentRepository;

@Service
public class AgentServiceImpl implements IAgentService
{
	@Autowired
	private AgentRepository agentRepo;
	
	@Autowired
	private DTOAgentConverter agentConv;
	
	@Autowired
	private NeregistrovaniAgentRepository neregRepo;
	
	@Autowired
	private MailServiceImpl mailService;
	
	
	
	@Override
	public List<NeregistrovaniAgent> getAllZahteviNeregAgenata()
	{
		Optional<List<NeregistrovaniAgent>> agenti = Optional.of(neregRepo.findAll());
		
		if(agenti.isPresent())
		{
			return agenti.get();
		}
		else
			return null;
	}


	@Override
	public String createPotvrdiZahtev(Long id)
	{
		Optional<NeregistrovaniAgent> nereg = neregRepo.findById(id);
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
		agent.setPoslovniMaticniBroj(nereg.get().getPoslovniMaticniBroj());
		
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


	@Override
	public boolean deleteOdbijZahtev(Long id)
	{
		Optional<NeregistrovaniAgent> agent = neregRepo.findById(id);
		
		if(agent.isPresent())
		{
			neregRepo.deleteById(agent.get().getIdNeregAgenta());
			return true;
		}
		else
			return false;
	}
}
