package com.megatravel.agentglobalback.model;

public enum StatusRezervacije {
	KREIRANA,	// klijent je kreirao rezervaciju
	OTKAZANA,	// klijent je otkazao rezervaciju
	U_TOKU,		// trenutni datum je izmedju datuma pocetka i zavrsetka rezervacije
	ISTEKLA,	// datum rezervacije je prosao, ali agent nije potvrdio niti opovrgnuo da je klijent dosao u smestaj, a nije ni otkazana
	POTVRDJENA,	// agent je potvrdio da klijent DOSAO u smestaj
	NEIZVRSENA	// agent je potvrdio da klijent NIJE DOSAO u smestaj
}
