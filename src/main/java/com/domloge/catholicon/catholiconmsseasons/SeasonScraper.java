package com.domloge.catholicon.catholiconmsseasons;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domloge.catholicon.ms.common.Loader;
import com.domloge.catholicon.ms.common.ScraperException;

@Component
public class SeasonScraper {
	
	private static final String url = "/Main.asp";
	
	private static final Pattern firstSeasonPattern = Pattern.compile("var firstSeason = (.*?);");
	private static final Pattern latestSeasonPattern = Pattern.compile("var latestSeason = (.*?);");
	
	@Autowired
	private Loader loader;
	
	@Autowired
	private LeagueScraper leagueScraper;
	
	
	public Season[] loadSeasons() throws ScraperException {
		String page = loader.load(url);
		int firstSeason = find(page, firstSeasonPattern);
		int latestSeason = find(page, latestSeasonPattern);
		List<Season> seasons = new LinkedList<>();
		
		for(int i=0; i <= latestSeason-firstSeason; i++) {
			int seasonStartYear = latestSeason - i;
			int seasonEndYear = seasonStartYear + 1;
			boolean current = latestSeason == seasonStartYear;
			League[] leagues = leagueScraper.loadLeagues(current ? 0 : seasonStartYear);
			Season s = new Season(seasonStartYear, seasonEndYear, current, Arrays.asList(leagues));
			seasons.add(s);
		}
		
		return seasons.toArray(new Season[seasons.size()]);
	}

	private int find(String page, Pattern p) throws ScraperException {
		Matcher m = p.matcher(page);
		if(m.find()) {
			String season = m.group(1);
			return Integer.parseInt(season);	
		}
		throw new ScraperException("Could not find season for "+p.pattern());
	}
}