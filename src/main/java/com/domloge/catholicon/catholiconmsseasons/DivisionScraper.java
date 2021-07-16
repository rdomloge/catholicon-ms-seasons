package com.domloge.catholicon.catholiconmsseasons;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domloge.catholicon.ms.common.Loader;
import com.domloge.catholicon.ms.common.ScraperException;

@Component
public class DivisionScraper {
	
	//private static Logger LOGGER = LoggerFactory.getLogger(DivisionScraper.class);
	
	public static final String divisionListurl = 
			"/Leagues.asp?LeagueTypeID=%1$s&CompetitionStyle=0&Season=%2$s&Juniors=false&Schools=false&Website=1";
	
	public static String leagueUrl = 
			"/Division.asp?LeagueTypeID=%1$s&Division=%2$s&Season=%3$s&Juniors=false&Schools=false&Website=1";

	
	@Autowired
	private Loader loader;
	
	public List<Division> loadDivisionsForLeague(String leagueTypeId, int seasonStartYear) throws ScraperException {
		String page = loader.load(String.format(divisionListurl, leagueTypeId, seasonStartYear));
		List<Division> divisions = new LinkedList<>();
		
		Document doc = Jsoup.parse(page);
		Elements divs = doc.select("#Divs option");
		if(divs.size() == 0) {
			throw new ScraperException("No divisions found for "+leagueTypeId+ " in season "+seasonStartYear);
		}
		
		for (Element option : divs) {
			String label = option.text();
			int divisionId = Integer.parseInt(option.attr("value"));
			if(0 == divisionId) continue;
			Division division = new Division(label, divisionId, leagueTypeId, seasonStartYear);
			divisions.add(division);
		}
		
		return divisions;
	}
	
}
