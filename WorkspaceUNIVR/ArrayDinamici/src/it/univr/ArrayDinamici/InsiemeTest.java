package it.univr.ArrayDinamici;
import org.junit.Test;
import junit.framework.Assert;

public class InsiemeTest {

	@Test
	public void testNasceVuoto() {
		Insieme i = new Insieme();
		Assert.assertTrue(i.vuoto());
	}

	@Test
	public void testAggiungi() {
		Insieme i = new Insieme();
		i.aggiungi(13.5);
		Assert.assertFalse(i.vuoto());
	}

	@Test
	public void testAggiungiZero() {
		Insieme i = new Insieme();
		i.aggiungi(0.0);
		Assert.assertFalse(i.vuoto());
	}

	@Test
	public void testMax() {
		Insieme i = new Insieme();
		i.aggiungi(13.5);
		i.aggiungi(11.5);
		i.aggiungi(-3.7);
		Assert.assertEquals(13.5, i.max());
	}

	@Test
	public void testMin() {
		Insieme i = new Insieme();
		i.aggiungi(13.5);
		i.aggiungi(11.5);
		i.aggiungi(-3.7);
		Assert.assertEquals(-3.7, i.min());
	}

	@Test
	public void testMedia() {
		Insieme i = new Insieme();
		i.aggiungi(13.5);
		i.aggiungi(11.5);
		i.aggiungi(-3.7);
		Assert.assertEquals(7.1, i.media(), 0.001);
	}

	@Test
	public void testUnisci() {
		Insieme i1 = new Insieme();
		i1.aggiungi(13.5);
		i1.aggiungi(11.5);
		i1.aggiungi(-3.7);

		Insieme i2 = new Insieme();
		i2.aggiungi(3.5);
		i2.aggiungi(-14.5);
		i2.aggiungi(3.7);

		i1.unisci(i2);

		Assert.assertEquals(-14.5, i1.min());
	}	

	@Test
	public void testToString() {
		Insieme i = new Insieme();
		i.aggiungi(3.1415);

		Assert.assertEquals("[3.1415]", i.toString());
	}
}