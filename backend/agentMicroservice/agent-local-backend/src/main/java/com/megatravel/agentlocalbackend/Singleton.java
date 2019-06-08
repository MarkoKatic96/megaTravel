package com.megatravel.agentlocalbackend;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.agentlocalbackend.model.Rezervacija;

public class Singleton {
	private static Singleton instance = null;
	private List<Rezervacija> rezZaUpdate = new ArrayList<>();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		
		return instance;
	}

	public List<Rezervacija> getRezZaUpdate() {
		return rezZaUpdate;
	}

	public void setRezZaUpdate(List<Rezervacija> rezZaUpdate) {
		this.rezZaUpdate = rezZaUpdate;
	}
	
}
