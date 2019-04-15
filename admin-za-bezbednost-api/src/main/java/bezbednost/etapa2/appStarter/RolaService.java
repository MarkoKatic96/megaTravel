package bezbednost.etapa2.appStarter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.Servis;

@Service
public class RolaService {

	@Autowired
	private RolaRepository rolaRepository;
	
	@Autowired
	private ServisRepository servisRepository;
	
	public List<Rola> getAllRoles(){
		return rolaRepository.findAll();
	}
	
	public Rola getRolaById(Long id) {
		Rola r = rolaRepository.findOne(id);
		if(r!=null) {
			return r;
		}else {
			return null;
		}
	}
	
	public String deleteRola(Long id) {
		Rola r = rolaRepository.findOne(id);
		if(r!=null) {
			rolaRepository.delete(id);
			return "Odabrana rola uspesno obrisana.";
		}else {
			return "Odabrana rola nije pronadjena";
		}
	}
	
	public Rola createRola(Rola rola) {
		List<Rola> lista = rolaRepository.findAll();
		for (Rola rola2 : lista) {
			if(rola2.getNaziv().equals(rola.getNaziv())) {
				return null;
			}
		}
		
		return rolaRepository.save(rola);
	}
	
	public Rola dodajServis(Long rolaId, Long servisId) {
		Rola r = getRolaById(rolaId);
		List<Servis> listaServisaRole = new ArrayList<Servis>();
		if(r.getServisi()!=null) {
			listaServisaRole = (List<Servis>) r.getServisi();//lista svih servisa(privilegija) koje rola ima
		}
		
		List<Servis> listaServisa = servisRepository.findAll();//svi servisi
		for (Servis servis : listaServisa) {
			if(servis.getId()==servisId) {
				for(int i = 0; i<listaServisaRole.size(); i++) {//proveravamo da li rola vec ima taj servis
					if(listaServisaRole.get(i).getId() == servis.getId()) {
						return null;
					}
				}
				listaServisaRole.add(servis);//ako nema dodajemo ga
				r.setServisi(listaServisaRole);
				break;
			}
		}
		return rolaRepository.save(r);
	}
	
	public Rola ukloniServis(Long rolaId, Long servisId) {
		Rola r = getRolaById(rolaId);
		List<Servis> listaServisaRole = (List<Servis>) r.getServisi();
		if(r.getServisi()==null || r.getServisi().isEmpty()) {
			return null;//nema sta da se ukloni
		}
		
		for (Servis servis : listaServisaRole) {
			if(servis.getId()==servisId) {
				listaServisaRole.remove(servis);
				break;
			}
		}
		return rolaRepository.save(r);
	}
	
	public List<Servis> getServiseRole(Long rolaId){
		Rola r = getRolaById(rolaId);
		List<Servis> lista = (List<Servis>) r.getServisi();
		return lista;
	}
	
}
