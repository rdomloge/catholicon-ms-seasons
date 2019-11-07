package com.domloge.catholicon.catholiconmsseasons;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domloge.catholicon.ms.common.Loader;
import com.domloge.catholicon.ms.common.ParserUtil;
import com.domloge.catholicon.ms.common.ScraperException;

@Component
public class LeagueScraper {
	
	public static String listUrl = "/Leagues.asp?Season=%1$s&website=1";
	
	private static final Pattern allLeagueRegExp = Pattern.compile("var leagueMenu = (.*?)];");
	
	@Autowired
	private Loader loader;
	
	@Autowired
	private DivisionScraper divisionScraper;
	
	
	//var leagueMenu = [{label:"Ladies 4",leagueTypeID:14,action:changeLeague},{label:"Mixed",leagueTypeID:15,action:changeLeague},{label:"Open",leagueTypeID:13,action:changeLeague}];
	public League[] loadLeagues(int seasonStartYear) throws ScraperException {
		String page = loader.load(String.format(listUrl, seasonStartYear));
		Matcher m = allLeagueRegExp.matcher(page);
		if(m.find()) {
			String group = m.group(1) + ']';
			List<League> leagues = parse(group, seasonStartYear);
			return leagues.toArray(new League[0]);
		}
		
		throw new ScraperException("Could not find league names data for "+seasonStartYear);
	}
	
	private List<League> parse(String data, int season) throws ScraperException {
		List<League> leagues = new LinkedList<>();
		String[] parts = data.split("\\},\\{");
		
		for (String part : parts) {
			if(part.startsWith("[")) part = part.substring(1);
			if(part.startsWith("{")) part = part.substring(1);
			String[] props = ParserUtil.splitOnUnquotedCommas(part);
			String label = null;
			int leagueTypeId = -1;
			for (String propertyKeyPair : props) {
				String[] splitProps = ParserUtil.splitOnUnquotedColons(propertyKeyPair);
				if("label".equalsIgnoreCase(splitProps[0])) {
					label = splitProps[1].replace("\"", "");
				}
				else if("leagueTypeId".equalsIgnoreCase(splitProps[0])) {
					leagueTypeId = Integer.parseInt(splitProps[1]);
				}
			}
			List<Division> divisionsForLeague = 
					divisionScraper.loadDivisionsForLeague(String.valueOf(leagueTypeId), season);
			leagues.add(new League(label, leagueTypeId, season, divisionsForLeague));
		}
		return leagues;
	}

}
