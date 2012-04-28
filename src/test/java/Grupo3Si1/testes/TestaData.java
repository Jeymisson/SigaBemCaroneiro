package Grupo3Si1.testes;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Grupo3Si1.handles.Data;

public class TestaData {
	
	private Data data;
	
	@Before
	public void TestaData(){
		data=new Data();
	}

	@Test
	public void test() {
		//dd/mm/aaaa
		Assert.assertTrue(data.isDataValida("10/02/2231","10:00"));
		Assert.assertFalse(data.isDataValida("05/04/2012","05:00"));
		Assert.assertFalse(data.isDataValida("05/04/2012","04:00"));
		Assert.assertFalse(data.isDataValida("05/04/2012","02:00"));
		Assert.assertFalse(data.isDataValida("05/04/2012","01:00"));
		Assert.assertFalse(data.isDataValida("05/04/2012","05:30"));
		Assert.assertFalse(data.isDataValida("03/04/2012","05:00"));
		Assert.assertFalse(data.isDataValida("05/04/2011","05:00"));
		
		Assert.assertFalse(data.isDataValida("10/03/1111",null));
		Assert.assertFalse(data.isDataValida("10/01/2012", null));
		Assert.assertTrue(data.isDataValida("30/10/3000", null));
		Assert.assertTrue(data.isDataValida("01/12/2020",null));
		Assert.assertTrue(data.isDataValida("01/12/3131", null));
		Assert.assertFalse(data.isDataValida("29/02/2012","10:00"));
		Assert.assertFalse(data.isDataValida("29/02/2013",null));
		Assert.assertFalse(data.isDataValida("31/02/2012", null));
		Assert.assertFalse(data.isDataValida("31/02/2011", null));
		Assert.assertFalse(data.isDataValida("31/00/2012",null));
		Assert.assertFalse(data.isDataValida("00/00/2012",null));
		Assert.assertFalse(data.isDataValida("1/2/2012",null));
		Assert.assertFalse(data.isDataValida("31/02/12",null));
		Assert.assertFalse(data.isDataValida("31/04/2012",null));
		Assert.assertFalse(data.isDataValida("31/06/2012",null));
		Assert.assertTrue(data.isDataValida("31/08/2012",null));
		Assert.assertFalse(data.isDataValida("31/09/2012",null));
		Assert.assertFalse(data.isDataValida("31/02/2012",null));
		Assert.assertFalse(data.isDataValida("31/11/2012",null));
		Assert.assertFalse(data.isDataValida("3177/02/2012",null));
		Assert.assertFalse(data.isDataValida("31/02000/2012",null));
		Assert.assertFalse(data.isDataValida("310/0200/2012",null));
		Assert.assertFalse(data.isDataValida("",""));
		Assert.assertFalse(data.isDataValida(null,""));
		Assert.assertFalse(data.isDataValida("dd/mm/aaaa",null));
		Assert.assertFalse(data.isDataValida("31*02*2012",null));
		Assert.assertFalse(data.isDataValida("31 02 2012",null));
		Assert.assertFalse(data.isDataValida("31022012",""));
		Assert.assertFalse(data.isDataValida("20",""));
	}

}
