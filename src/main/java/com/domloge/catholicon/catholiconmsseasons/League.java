package com.domloge.catholicon.catholiconmsseasons;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class League {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int leagueTypeId;
	
	private int season;
	
	private String label;
	
	@OrderColumn(name = "DIVISIONORDER")
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Division> divisions;
	
	public League() {
		
	}

	public League(String label, int leagueTypeId, int season, List<Division> divisions) {
		this.season = season;
		this.label = label;
		this.leagueTypeId = leagueTypeId;
		this.divisions = divisions;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public int getLeagueTypeId() {
		return leagueTypeId;
	}
	
	public int getSeason() {
		return season;
	}

	public List<Division> getDivisions() {
		return divisions;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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
