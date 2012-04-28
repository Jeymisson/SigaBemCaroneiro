package Grupo3Si1.testes;

import org.junit.Test;

import Grupo3Si1.handles.Data;

import junit.framework.Assert;



public class TesteHora {
	
	private String hora1 = "23:59";
	private String hora2 = "23:60";
	private String hora3 = "25:59";
	private String hora4 = "00:00";
	private String hora5 = "00:59";
	private String hora6 = "24:00";
	private String hora7 = "23:70";
	
	@Test
	public void test() {
		
		Assert.assertTrue(Data.isHoraValida(hora1));
		Assert.assertFalse(Data.isHoraValida(hora2));
		Assert.assertFalse(Data.isHoraValida(hora3));
		Assert.assertTrue(Data.isHoraValida(hora4));
		Assert.assertTrue(Data.isHoraValida(hora5));
		Assert.assertFalse(Data.isHoraValida(hora6));
		Assert.assertFalse(Data.isHoraValida(hora7));
		Assert.assertFalse(Data.isHoraValida(""));
		Assert.assertFalse(Data.isHoraValida("222:1"));
		Assert.assertFalse(Data.isHoraValida("hashahs"));
		Assert.assertFalse(Data.isHoraValida("88:60"));
		Assert.assertFalse(Data.isHoraValida(null));
		Assert.assertFalse(Data.isHoraValida("0"));
		
	}

}
