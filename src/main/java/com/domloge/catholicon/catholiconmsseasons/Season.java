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
public class Season {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int seasonStartYear;
	
	private int seasonEndYear;
	
	private int apiIdentifier;

	@OrderColumn(name = "LEAGUEORDER")
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<League> leagues;

	public Season(int seasonStartYear, int seasonEndYear, boolean current, List<League> leagues) {
		this.seasonStartYear = seasonStartYear;
		this.seasonEndYear = seasonEndYear;
		this.apiIdentifier = current ? 0 : seasonStartYear;
		this.leagues = leagues;
	}
	
	public Season() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeasonStartYear() {
		return seasonStartYear;
	}

	public void setSeasonStartYear(int seasonStartYear) {
		this.seasonStartYear = seasonStartYear;
	}

	public int getSeasonEndYear() {
		return seasonEndYear;
	}

	public void setSeasonEndYear(int seasonEndYear) {
		this.seasonEndYear = seasonEndYear;
	}

	public int getApiIdentifier() {
		return apiIdentifier;
	}

	public void setApiIdentifier(int apiIdentifier) {
		this.apiIdentifier = apiIdentifier;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public List<League> getLeagues() {
		return leagues;
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
		return EqualsBuilder.reflectionEquals(this, obj, "id"); // exclude the ID field when comparing equality so we can compare with scraped entities
	}
}
