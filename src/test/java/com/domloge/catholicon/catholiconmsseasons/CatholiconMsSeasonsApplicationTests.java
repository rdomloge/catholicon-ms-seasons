package com.domloge.catholicon.catholiconmsseasons;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import com.domloge.catholicon.ms.common.ScraperException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatholiconMsSeasonsApplicationTests {
	
	@Autowired
	private SyncSchedulingAndPersistence syncScheduling;
	
	@Autowired
	private SeasonRepository repo;

	@Test
	@DirtiesContext
	public void contextLoads() {
	}

	@Test
	@DirtiesContext
	public void saveSingle() {
		Division[] divisions = {new Division()};
		List<Division> divList = Arrays.asList(divisions);
		League[] leagues = {new League("Test league", 1, 0, divList)};
		List<League> leagueList = Arrays.asList(leagues);
		repo.save(new Season(0, 1, true, leagueList));
	}

	@Test
	@DirtiesContext
	public void testSpeed() throws ScraperException {
		StopWatch sw = new StopWatch();
		sw.start("Sync");
		syncScheduling.sync();
		sw.stop();
		sw.start("Find all");
		List<Season> seasons = repo.findAll();
		sw.stop();
		System.out.println(String.format("Found %d seasons", seasons.size()));
		System.out.println(sw.prettyPrint());
	}


	@Test
	@DirtiesContext
	public void testSync() throws ScraperException {
		syncScheduling.sync();
		List firstSync = repo.findAll();
		long count = firstSync.size();
		
		syncScheduling.sync();
		
		List secondList = repo.findAll();
		long count2 = secondList.size();
		System.out.println(count + "::" + count2);
		assertTrue("Number of seasons in repo changed after a re-synch: "+count+"!="+count2, count == count2);
	}
}
