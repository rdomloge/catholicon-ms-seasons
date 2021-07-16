package com.domloge.catholicon.catholiconmsseasons;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Season {

	@Id
	private String id;

	private int seasonStartYear;
	
	private int seasonEndYear;
	
	private int apiIdentifier;

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

	// public String getId() {
	// 	return id;
	// }

	// public void setId(String id) {
	// 	this.id = id;
	// }

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
