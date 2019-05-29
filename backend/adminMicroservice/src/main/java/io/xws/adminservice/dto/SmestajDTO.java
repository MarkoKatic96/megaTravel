package io.xws.adminservice.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class SmestajDTO 
{
	protected long idSmestaja;
    protected String adresa;
    protected float longitude;
    protected float latitude;
    protected TipSmestajaDTO tipSmestaja;
    protected KategorijaSmestajaDTO kategorijaSmestaja;
    protected String opis;
    protected BigInteger maxOsoba;
    protected BigInteger maxDanaZaOtkazivanje;
    protected BigDecimal cenaProlece;
    protected BigDecimal cenaLeto;
    protected BigDecimal cenaJesen;
    protected BigDecimal cenaZima;
    protected long vlasnik;
    protected List<DodatneUslugeDTO> listaDodatnihUsluga;
//    protected Smestaj.ListaSlika listaSlika;
}
