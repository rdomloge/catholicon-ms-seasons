package com.domloge.catholicon.catholiconmsseasons;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.domloge.catholicon.ms.common.Diff;
import com.domloge.catholicon.ms.common.ScraperException;
import com.domloge.catholicon.ms.common.Sync;

@Component
public class SyncSchedulingAndPersistence {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncSchedulingAndPersistence.class); 
	
	
	@Autowired
	private SeasonScraper scraper;
	
	@Autowired
	private SeasonRepository repository;
	
	@Autowired
	private Sync<Season> sync;
	
	@Scheduled(cron="0 0 */3 * * *")
	@PostConstruct
	public void sync() throws ScraperException {
		
		LOGGER.debug("Synchronising...");
		
		Map<Integer, Season> scrapedSeasons = map(scraper.loadSeasons());
		Map<Integer, Season> dbSeasons = map(StreamSupport.stream(repository.findAll().spliterator(), false).toArray(Season[]::new));
		
		Diff<Season> compare = sync.compare(scrapedSeasons, dbSeasons);
		
		for (Season season : compare.getDelete()) {
			repository.delete(season);
		}
		
		for (Season season : compare.getNewValues()) {
			repository.save(season);
		}
		
		for (Season season : compare.getUpdate()) {
			repository.save(season);
		}
		
		LOGGER.debug("Sync complete");
	}
	
	private Map<Integer, Season> map(Season[] seasons) {
		HashMap<Integer, Season> map = new HashMap<>();
		for (Season season : seasons) {
			map.put(season.getSeasonStartYear(), season);
		}
		return map;
	}
}
