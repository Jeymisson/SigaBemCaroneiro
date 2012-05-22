package grupo3si.testes;

import grupo3si.server.model.RepositorioDeUsuarios;
import grupo3si.server.model.Usuario;
import grupo3si.server.model.Usuario;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestaRepositorioUsuario {
	
	private RepositorioDeUsuarios rep1=RepositorioDeUsuarios.getInstance();
	private Usuario user1 ,user2,user3,user4,user5;
	
	
	@After
	public void ZeraRepositorio(){
		rep1.clear();
	}
	
	@Before
	public void TestaRepositorioUsuario() throws Exception{
		
		
		user1 = new Usuario("luucas","1","Lucas Albuquerque","Campina Grande - centro","lucas.ufcg@gmail.com");
		user2 = new Usuario("jey","12","Jeymilson","Campina Grande - bodocongó","jey@gmail.com");
		user3 = new Usuario("jojo","123","Jordão","Campina Grande - bodocongó","jordao@gmail.com");
		user4 = new Usuario("thix","1234","Thiago","Campina Grande - fofex","thiago@gmail.com");
		user5 = new Usuario("cabeca","12345","Irvile","Campina Grande - centro","irvile@gmail.com");
		
		
		
		
		try {
			rep1.addUser(user1.getLogin(), user1);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		try {
			rep1.addUser(user2.getLogin(), user2);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		try {
			rep1.addUser(user3.getLogin(), user3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		try {
			rep1.addUser(user4.getLogin(), user4);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		try {
			rep1.addUser(user5.getLogin(), user5);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
	}
	
	

	@Test
	public void TestaGetUser() {
		
		try {
			Assert.assertEquals(user1, rep1.getUser(user1.getLogin()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		try {
			Assert.assertEquals(user2, rep1.getUser(user2.getLogin()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		try {
			Assert.assertEquals(user3, rep1.getUser(user3.getLogin()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		try {
			Assert.assertEquals(user4, rep1.getUser(user4.getLogin()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		try {
			Assert.assertEquals(user5, rep1.getUser(user5.getLogin()));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		try {
			Assert.assertFalse(rep1.getUser("").equals(user1));
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		try {
			Assert.assertFalse(rep1.getUser(null).equals(user1));
		} catch (Exception e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		
		try {
			Assert.assertFalse(rep1.getUser(user1.getLogin()).equals(user2));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
	}
	

	
	@Test
	public void TestaGetCarona(){
		
		try {
			user1.cadastraCarona("CG", "JP", "03/07/2012", "15:30", 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		try {
			user1.cadastraCarona("ITA", "JP", "03/07/2012", "15:30", 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		
		try {
			user1.cadastraCarona("RECIFE", "OLINDA", "03/07/2012", "15:30", 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		
		try {
			user1.cadastraCarona("SP", "RJ", "03/07/2012", "15:30", 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		try {
			user1.cadastraCarona("MACEIO", "RJ", "03/07/2012", "20:30", 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		
		try {
			user2.cadastraCarona("MACEIO", "RJ", "03/07/2012", "20:30", 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		
		String idCarona1 = user1.getPerfil().getHistoricoDeCaronas().get(0).getId()+"";
		String idCarona2 = user1.getPerfil().getHistoricoDeCaronas().get(1).getId()+"";
		String idCarona3 = user1.getPerfil().getHistoricoDeCaronas().get(2).getId()+"";
		String idCarona4 = user1.getPerfil().getHistoricoDeCaronas().get(3).getId()+"";
		String idCarona5 = user1.getPerfil().getHistoricoDeCaronas().get(4).getId()+"";
		String idCarona6 = user2.getPerfil().getHistoricoDeCaronas().get(0).getId()+"";
		
		Assert.assertEquals(user1.getCarona(idCarona1),rep1.getCarona(idCarona1));
		Assert.assertEquals(user1.getCarona(idCarona2),rep1.getCarona(idCarona2));
		Assert.assertEquals(user1.getCarona(idCarona3),rep1.getCarona(idCarona3));
		Assert.assertEquals(user1.getCarona(idCarona4),rep1.getCarona(idCarona4));
		Assert.assertNotSame(null,rep1.getCarona(idCarona3));
		Assert.assertEquals(user2.getCarona(idCarona6).getCarona(), rep1.getCarona(idCarona5).getCarona());
		
	}
	
	
	@Test
	public void TestaAdicioUser() throws Exception{
		
		Usuario user6, user7, user8, user9, user10;
		
		user6 = new Usuario("fulano1","1","FULANO","Campina Grande - centro","fulano@gmail.com");
		user7 = new Usuario("beltrano2","12","BELTRANO","Campina Grande - bodocongó","beltrano@gmail.com");
		user8 = new Usuario("sicrano3","123","CICRANO","Campina Grande - bodocongó","sicrano@gmail.com");
		user9 = new Usuario("ariolano4","1234","ARIOLANO","Campina Grande - fofex","ariolano@gmail.com");
		user10 = new Usuario("tiririca5","12345","TIRIRICA","Campina Grande - centro","tiririca@gmail.com");
		
		Assert.assertEquals(5,rep1.getRepositorio().size());
		
		rep1.addUser(user6.getLogin(), user6);
		
		Assert.assertEquals(6,rep1.getRepositorio().size());
		
		rep1.addUser(user7.getLogin(), user7);
		
		Assert.assertEquals(7,rep1.getRepositorio().size());
		
		rep1.addUser(user8.getLogin(), user8);
		
		Assert.assertEquals(8,rep1.getRepositorio().size());
		
		rep1.addUser(user9.getLogin(), user9);
		
		Assert.assertEquals(9,rep1.getRepositorio().size());
		
		rep1.addUser(user10.getLogin(), user10);
		
		Assert.assertEquals(10,rep1.getRepositorio().size());
		
	}
	
	//TODO
	@Test
	public void TestaGetSetRepostorio() throws Exception{
		
		AbstractMap<String, Usuario> repTeste = rep1.getRepositorio();
		AbstractMap<String, Usuario> repTeste2 = null;
		Assert.assertEquals(repTeste, rep1.getRepositorio());
		
		Usuario user6, user7, user8, user9, user10, user11,user12;
		
		user6 = new Usuario("fulano1","1","FULANO","Campina Grande - centro","fulano@gmail.com");
		user7 = new Usuario("beltrano2","12","BELTRANO","Campina Grande - bodocongó","beltrano@gmail.com");
		user8 = new Usuario("sicrano3","123","CICRANO","Campina Grande - bodocongó","sicrano@gmail.com");
		user9 = new Usuario("ariolano4","1234","ARIOLANO","Campina Grande - fofex","ariolano@gmail.com");
		user10 = new Usuario("tiririca5","12345","TIRIRICA","Campina Grande - centro","tiririca@gmail.com");
		user11 = new Usuario("Criolo","12345","Criolinho","Campina Grande - centro","criolo@gmail.com");
		user12 = new Usuario("xpto","12345","XPTO","Campina Grande - centro","xpto@gmail.com");
		
		//repTeste2.put("user6", user6);
		
		rep1.addUser(user6.getLogin(), user6);
		rep1.addUser(user7.getLogin(), user7);
		rep1.addUser(user8.getLogin(), user8);
		rep1.addUser(user9.getLogin(), user9);
		rep1.addUser(user10.getLogin(), user10);
		rep1.addUser(user11.getLogin(), user11);
		rep1.addUser(user12.getLogin(), user12);
	
	}
	
	@Test
	public void TestaGetUsuarios(){
		Iterator<Usuario> userIt = rep1.getUsuarios();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		while(userIt.hasNext()){
			listaUsuarios.add(userIt.next());	
		}	
		
		Assert.assertEquals(user5, listaUsuarios.get(0));
		Assert.assertEquals(user2, listaUsuarios.get(1));
		Assert.assertEquals(user3, listaUsuarios.get(2));
		Assert.assertEquals(user1, listaUsuarios.get(3));
		Assert.assertEquals(user4, listaUsuarios.get(4));
	}
	
	
	@Test
	public void TestacheckDuplicatedData() throws Exception{
		
		Usuario user6, user7, user8;
		
		user6 = new Usuario("fulano1","1","FULANO","Campina Grande - centro","lucas@gmail.com");
		user7 = new Usuario("jey","12","BELTRANO","Campina Grande - bodocongó","beltrano@gmail.com");
		user8 = new Usuario("jojo","123","CICRANO","Campina Grande - bodocongó","jordao@gmail.com");
		
		
		 try {
			rep1.addUser(user1.getLogin(), user1);
		} catch (Exception e) {
			Assert.assertEquals("Já existe um usuário com este login",e.getMessage());
		}
		 
		 
		 try {
				rep1.addUser(user6.getLogin(), user6);
			} catch (Exception e) {
				Assert.assertEquals("Já existe um usuário com este email",e.getMessage());
			}
		 
		 try {
				rep1.addUser(user7.getLogin(), user7);
			} catch (Exception e) {
				Assert.assertEquals("Já existe um usuário com este login",e.getMessage());
			}
		 
		 try {
				rep1.addUser(user8.getLogin(), user8);
			} catch (Exception e) {
				Assert.assertEquals("Já existe um usuário com este login",e.getMessage());
			}
		 
	}
	
	
}
