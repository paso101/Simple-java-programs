package it.univr.Data;
import org.junit.Assert;
import org.junit.Test;

public class DataTest {

	@Test
	public void testEmptyConstructor() {
		Data data = new Data();
		Assert.assertEquals("13 / Gennaio / 2008", data.toString());
	}

	@Test
	public void testUnaryConstructor() {
		Data data = new Data(30);
		Assert.assertEquals("30 / Gennaio / 2008", data.toString());
	}

	@Test
	public void testBinaryConstructor() {
		Data data = new Data(30, 10);
		Assert.assertEquals("30 / Ottobre / 2008", data.toString());
	}
	
	@Test
	public void testAllConstructor() {
		Data data = new Data(8, 3, 1991);
		Assert.assertEquals("8 / Marzo / 1991", data.toString());
	}

	@Test
	public void testPrecede() {
		Data data1 = new Data(13, 2, 2009);
		Data data2 = new Data(10, 1, 2011);
		Assert.assertTrue(data1.precede(data2));
	}

	@Test
	public void testGiorniDallInizioBisestile() {
		Data data = new Data(1, 3, 2012);
		Assert.assertEquals(61, data.passatiDallInizio());
	}

	@Test
	public void testGiorniDallInizioNonBisestile() {
		Data data = new Data(1, 3, 2013);
		Assert.assertEquals(60, data.passatiDallInizio());
	}
}