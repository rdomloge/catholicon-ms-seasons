package com.domloge.catholicon.catholiconmsseasons;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Division {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String leagueTypeId;
	
	private int divisionId;
	
	private int seasonStartYear;
	
	private String label;
	
	
	public Division() {
		
	}

	
	public Division(String label, int divisionId, String leagueTypeId, int seasonStartYear) {
		this.label = label;
		this.leagueTypeId = leagueTypeId;
		this.divisionId = divisionId;
		this.seasonStartYear = seasonStartYear;
	}

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getLeagueTypeId() {
		return leagueTypeId;
	}

	public int getDivisionId() {
		return divisionId;
	}

	public int getSeasonStartYear() {
		return seasonStartYear;
	}

	public void setLeagueTypeId(String leagueTypeId) {
		this.leagueTypeId = leagueTypeId;
	}

	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	public void setSeasonStartYear(int seasonStartYear) {
		this.seasonStartYear = seasonStartYear;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "id");
	}
}
