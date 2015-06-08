package org.geoserver.wfs;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculateSizeBannedForFeaturesTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GetFeature.calculateSizeBannedForFeatures = Collections.emptySet();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GetFeature.calculateSizeBannedForFeatures = Collections.emptySet();
	}

	@Test
	public void testLoadResource() {
		Set<QName> set = GetFeature.readBannedFeatureTypeNames("CalculateSizeBannedForFeaturesTest_testLoadResource.txt");
		
		assertEquals(1, set.size());
		assertTrue(set.contains(new QName("http://nls.fi/test/namespace", "fooType")));
	}

	@Test
	public void testIsBanned() {
		GetFeature.calculateSizeBannedForFeatures = GetFeature.readBannedFeatureTypeNames("CalculateSizeBannedForFeaturesTest_testLoadResource.txt");
		assertTrue(GetFeature.testIfCalculateSizeIsBanned(new QName("http://nls.fi/test/namespace", "fooType")));
	}
	
	@Test
	public void testIsNotBanned() {
		GetFeature.calculateSizeBannedForFeatures = GetFeature.readBannedFeatureTypeNames("CalculateSizeBannedForFeaturesTest_testLoadResource.txt");
		assertFalse(GetFeature.testIfCalculateSizeIsBanned(new QName("http://nls.fi/test/namespace", "barType")));
	}
}
