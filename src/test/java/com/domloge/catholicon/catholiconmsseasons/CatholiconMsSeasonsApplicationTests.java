package com.domloge.catholicon.catholiconmsseasons;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void testSync() throws ScraperException {
		syncScheduling.sync();
		long count = repo.count();
		
		syncScheduling.sync();
		
		long count2 = repo.count();
		System.out.println(count + "::" + count2);
		assertTrue(count == count2);
	}
}
