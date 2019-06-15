package io.xws.adminservice.dto;

import java.math.BigDecimal;

import io.xws.adminservice.model.Smestaj;
import lombok.Data;

@Data
public class TKoordinateDTO {
	protected Long koordinateId;
	protected Smestaj smestaj;
    protected BigDecimal latitude;
    protected BigDecimal Longitude;
	public Long getKoordinateId() {
		return koordinateId;
	}
	public Smestaj getSmestaj() {
		return smestaj;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public BigDecimal getLongitude() {
		return Longitude;
	}
	public void setKoordinateId(Long koordinateId) {
		this.koordinateId = koordinateId;
	}
	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(BigDecimal longitude) {
		Longitude = longitude;
	}
}
