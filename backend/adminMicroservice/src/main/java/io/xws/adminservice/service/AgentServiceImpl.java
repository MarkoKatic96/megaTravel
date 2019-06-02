package io.xws.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	
	@Override
	public List<AgentDTO> getAllZahteviAgenata() 
	{
		Optional<List<Agent>> agenti = Optional.of(agentRepo.findAll());
		
		List<AgentDTO> dtoList = new ArrayList<AgentDTO>();
		
		if(agenti.isPresent())
		{
			for(Agent agent : agenti.get())
			{
				if(agent.getLozinka() != null)
					continue;
				else
					dtoList.add(agentConv.convertToDTO(agent));
			}
			
			return dtoList;
		}
		else
			return null;
	}


	@Override
	public boolean createPotvrdiZahtev(NeregistrovaniAgentDTO zahtev)
	{
		if(agentRepo.existsByEmail(zahtev.getEmail())) //proverava se prvo na agentu
			return false;
		
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
		agent.setIme(zahtev.getIme());
		agent.setPrezime(zahtev.getPrezime());
		agent.setEmail(zahtev.getEmail());
		agent.setLozinka(lozinka);
		agent.setPoslovniMaticniBroj(zahtev.getPoslovniMaticniBroj());		
		agentRepo.save(agent);
		
		//treba jos napraviti mehanizam da posalje mail-om parametre za logovanje
		
		
		
		return true;
	}


	@Override
	public boolean deleteOdbijZahtev(NeregistrovaniAgentDTO zahtev)
	{
		Optional<NeregistrovaniAgent> agent = neregRepo.findByEmail(zahtev.getEmail());
		
		if(agent.isPresent())
		{
			neregRepo.deleteById(agent.get().getIdNeregAgenta());
			return true;
		}
		else
			return false;
	}
}
