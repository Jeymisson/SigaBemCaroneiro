package grupo3si.testes;

import grupo3si.server.model.InvalidEmailException;
import grupo3si.server.model.InvalidLoginException;
import grupo3si.server.model.InvalidNameException;
import grupo3si.server.model.Usuario;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestaUsuario {

	private Usuario user1, user2, user3, user4, user5, userInvalido;

	@Before
	public void TestaUsuario() throws Exception {
		user1 = new Usuario("luucas", "1", "Lucas Albuquerque",
				"Campina Grande - centro", "lucas.ufcg@gmail.com");
		user2 = new Usuario("jey", "12", "Jeymilson",
				"Campina Grande - bodocongó", "jey@gmail.com");
		user3 = new Usuario("jojo", "123", "Jordão",
				"Campina Grande - bodocongó", "jordao@gmail.com");
		user4 = new Usuario("thix", "1234", "Thiago", "Campina Grande - fofex",
				"thiago@gmail.com");
		user5 = new Usuario("cabeca", "12345", "Irvile",
				"Campina Grande - centro", "irvile@gmail.com");
	}

	@Test
	public void TestaLoginInvalido() throws Exception {
		try {
			userInvalido = new Usuario(null, "123", "Fulano", "sem casa",
					"zeNinguem@bol.com");
		} catch (InvalidLoginException e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}

		try {
			userInvalido = new Usuario("", "123", "Fulano", "sem casa",
					"zeNinguem@bol.com");
		} catch (InvalidLoginException e) {
			Assert.assertEquals("Login inválido", e.getMessage());
		}
	}

	// Ta Bugado!
	@Test
	public void TestaNomeInvalido() throws Exception {
		try {
			userInvalido = new Usuario("fulano123", "1", "", "sem casa",
					"zeNinguem@bol.com");

		} catch (InvalidNameException e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}

		try {
			userInvalido = new Usuario("fulano123", "12", null, "sem casa",
					"zeNinguem@bol.com");
		} catch (Exception e) {

			Assert.assertEquals("Nome inválido", e.getMessage());
		}
	}

	// Ta bugado!
	@Test
	public void TestaEmailInvalido() throws Exception {
		try {
			userInvalido = new Usuario("fulano123", "123", "Fulano",
					"sem casa", "zeNinguem@bol.com");
		} catch (InvalidEmailException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}

		try {
			userInvalido = new Usuario("fulano123", "1234", "Fulano",
					"sem casa", "zeNinguem@bol.com");
			Usuario userInvalido2 = new Usuario("fuu", "1200e", "SeiLa",
					"sem casa", "zeNinguem@bol.com");
		} catch (InvalidEmailException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}

		try {
			userInvalido = new Usuario("fulano123", "123", "Fulano",
					"sem casa", "");
		} catch (InvalidEmailException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}
	}

	public void TestaGetLogin() {
		Assert.assertTrue(user1.getLogin().equals("luucas"));
		Assert.assertTrue(user2.getLogin().equals("jey"));
		Assert.assertTrue(user3.getLogin().equals("jojo"));
		Assert.assertTrue(user4.getLogin().equals("thix"));
		Assert.assertTrue(user5.getLogin().equals("cabeca"));

		Assert.assertFalse(user5.getLogin().equals(null));
		Assert.assertFalse(user4.getLogin().equals(""));
		Assert.assertFalse(user3.getLogin().equals("asas"));

		Assert.assertEquals(false, user1.getLogin().equals(user2.getLogin()));
		Assert.assertEquals(false, user1.getLogin().equals(user3.getLogin()));
		Assert.assertEquals(false, user1.getLogin().equals(user4.getLogin()));
		Assert.assertEquals(false, user1.getLogin().equals(user5.getLogin()));
		Assert.assertEquals(false, user2.getLogin().equals(user3.getLogin()));
		Assert.assertEquals(false, user2.getLogin().equals(user4.getLogin()));
		Assert.assertEquals(false, user2.getLogin().equals(user5.getLogin()));
		Assert.assertEquals(false, user3.getLogin().equals(user4.getLogin()));
		Assert.assertEquals(false, user3.getLogin().equals(user5.getLogin()));
		Assert.assertEquals(false, user4.getLogin().equals(user5.getLogin()));

	}

	@Test
	public void TestaSetLogin() {
		Assert.assertEquals("luucas", user1.getLogin());
		Assert.assertNotSame("Fulano", user1.getLogin());
		user1.setLogin("Fulano");
		Assert.assertSame("Fulano", user1.getLogin());
		Assert.assertNotSame("Coisinha", user2.getLogin());
		user2.setLogin("Coisinha");
		Assert.assertSame("Coisinha", user2.getLogin());
	}

	@Test
	public void TestaGetNome() {
		Assert.assertTrue(user1.getNome().equals("Lucas Albuquerque"));
		Assert.assertTrue(user2.getNome().equals("Jeymilson"));
		Assert.assertTrue(user3.getNome().equals("Jordão"));
		Assert.assertTrue(user4.getNome().equals("Thiago"));
		Assert.assertTrue(user5.getNome().equals("Irvile"));

		Assert.assertFalse(user5.getNome().equals(null));
		Assert.assertFalse(user4.getNome().equals(""));
		Assert.assertFalse(user3.getNome().equals("asas"));

		Assert.assertEquals(false, user1.getNome().equals(user2.getNome()));
		Assert.assertEquals(false, user1.getNome().equals(user3.getNome()));
		Assert.assertEquals(false, user1.getNome().equals(user4.getNome()));
		Assert.assertEquals(false, user1.getNome().equals(user5.getNome()));
		Assert.assertEquals(false, user2.getNome().equals(user3.getNome()));
		Assert.assertEquals(false, user2.getNome().equals(user4.getNome()));
		Assert.assertEquals(false, user2.getNome().equals(user5.getNome()));
		Assert.assertEquals(false, user3.getNome().equals(user4.getNome()));
		Assert.assertEquals(false, user3.getNome().equals(user5.getNome()));
		Assert.assertEquals(false, user4.getNome().equals(user5.getNome()));

	}

	@Test
	public void TestaSetNome() {
		Assert.assertEquals("Lucas Albuquerque", user1.getNome());
		Assert.assertNotSame("Fulano", user1.getNome());
		user1.setNome("Fulano");
		Assert.assertSame("Fulano", user1.getNome());
		Assert.assertNotSame("Coisinha", user2.getNome());
		user2.setNome("Coisinha");
		Assert.assertSame("Coisinha", user2.getNome());
	}

	@Test
	public void TestaGetEmail() {
		Assert.assertTrue(user1.getEmail().equals("lucas.ufcg@gmail.com"));
		Assert.assertTrue(user2.getEmail().equals("jey@gmail.com"));
		Assert.assertTrue(user3.getEmail().equals("jordao@gmail.com"));
		Assert.assertTrue(user4.getEmail().equals("thiago@gmail.com"));
		Assert.assertTrue(user5.getEmail().equals("irvile@gmail.com"));

		Assert.assertFalse(user5.getEmail().equals(null));
		Assert.assertFalse(user4.getEmail().equals(""));
		Assert.assertFalse(user3.getEmail().equals("asas"));

		Assert.assertEquals(false, user1.getEmail().equals(user2.getEmail()));
		Assert.assertEquals(false, user1.getEmail().equals(user3.getEmail()));
		Assert.assertEquals(false, user1.getEmail().equals(user4.getEmail()));
		Assert.assertEquals(false, user1.getEmail().equals(user5.getEmail()));
		Assert.assertEquals(false, user2.getEmail().equals(user3.getEmail()));
		Assert.assertEquals(false, user2.getEmail().equals(user4.getEmail()));
		Assert.assertEquals(false, user2.getEmail().equals(user5.getEmail()));
		Assert.assertEquals(false, user3.getEmail().equals(user4.getEmail()));
		Assert.assertEquals(false, user3.getEmail().equals(user5.getEmail()));
		Assert.assertEquals(false, user4.getEmail().equals(user5.getEmail()));

	}

	@Test
	public void TestaSetEmail() {
		Assert.assertNotSame("Fulano@gmail", user1.getEmail());
		user1.setEmail("Fulano@gmail");
		Assert.assertSame("Fulano@gmail", user1.getEmail());
		Assert.assertNotSame("Coisinha@gmail", user2.getEmail());
		user2.setEmail("Coisinha@gmail");
		Assert.assertSame("Coisinha@gmail", user2.getEmail());
	}

	/*
	 * @Test public void TestaGetSenha(){
	 * Assert.assertTrue(user1.getSenha().equals("1"));
	 * Assert.assertTrue(user2.getSenha().equals("12"));
	 * Assert.assertTrue(user3.getSenha().equals("123"));
	 * Assert.assertTrue(user4.getSenha().equals("1234"));
	 * Assert.assertTrue(user5.getSenha().equals("12345"));
	 * 
	 * Assert.assertFalse(user5.getSenha().equals(null));
	 * Assert.assertFalse(user4.getSenha().equals(""));
	 * Assert.assertFalse(user3.getSenha().equals("asas"));
	 * 
	 * 
	 * Assert.assertEquals(false, user1.getSenha().equals(user2.getSenha()));
	 * Assert.assertEquals(false, user1.getSenha().equals(user3.getSenha()));
	 * Assert.assertEquals(false, user1.getSenha().equals(user4.getSenha()));
	 * Assert.assertEquals(false, user1.getSenha().equals(user5.getSenha()));
	 * Assert.assertEquals(false, user2.getSenha().equals(user3.getSenha()));
	 * Assert.assertEquals(false, user2.getSenha().equals(user4.getSenha()));
	 * Assert.assertEquals(false, user2.getSenha().equals(user5.getSenha()));
	 * Assert.assertEquals(false, user3.getSenha().equals(user4.getSenha()));
	 * Assert.assertEquals(false, user3.getSenha().equals(user5.getSenha()));
	 * Assert.assertEquals(false, user4.getSenha().equals(user5.getSenha()));
	 * 
	 * }
	 * 
	 * 
	 * @Test public void TestaSetSenhal(){ Assert.assertNotSame("casa",
	 * user1.getSenha()); user1.setSenha("casa"); Assert.assertSame("casa",
	 * user1.getSenha()); Assert.assertNotSame("si", user2.getSenha());
	 * user2.setSenha("si"); Assert.assertSame("si", user2.getSenha()); }
	 */
	@Test
	public void TestaEndereco() {
		Assert.assertTrue(user1.getEndereco().equals("Campina Grande - centro"));
		Assert.assertTrue(user2.getEndereco().equals(
				"Campina Grande - bodocongó"));
		Assert.assertTrue(user3.getEndereco().equals(
				"Campina Grande - bodocongó"));
		Assert.assertTrue(user4.getEndereco().equals("Campina Grande - fofex"));
		Assert.assertTrue(user5.getEndereco().equals("Campina Grande - centro"));

		Assert.assertFalse(user5.getEndereco().equals(null));
		Assert.assertFalse(user4.getEndereco().equals(""));
		Assert.assertFalse(user3.getEndereco().equals("asas"));

		Assert.assertEquals(false,
				user1.getEndereco().equals(user2.getEndereco()));
		Assert.assertEquals(false,
				user1.getEndereco().equals(user3.getEndereco()));
		Assert.assertEquals(false,
				user1.getEndereco().equals(user4.getEndereco()));
		Assert.assertEquals(true,
				user1.getEndereco().equals(user5.getEndereco()));
		Assert.assertEquals(true,
				user2.getEndereco().equals(user3.getEndereco()));
		Assert.assertEquals(false,
				user2.getEndereco().equals(user4.getEndereco()));
		Assert.assertEquals(false,
				user2.getEndereco().equals(user5.getEndereco()));
		Assert.assertEquals(false,
				user3.getEndereco().equals(user4.getEndereco()));
		Assert.assertEquals(false,
				user3.getEndereco().equals(user5.getEndereco()));
		Assert.assertEquals(false,
				user4.getEndereco().equals(user5.getEndereco()));

	}

	@Test
	public void TestaSetEndereco() {
		Assert.assertNotSame("casa", user1.getEndereco());
		user1.setEndereco("casa");
		Assert.assertSame("casa", user1.getEndereco());
		Assert.assertNotSame("quintal", user2.getEndereco());
		user2.setEndereco("quintal");
		Assert.assertSame("quintal", user2.getEndereco());
	}

	@Test
	public void TestaEquals() throws Exception {
		Usuario userTeste = new Usuario("luucas", "1", "Lucas Albuquerque",
				"Campina Grande - centro", "lucas.ufcg@gmail.com");
		Usuario userTeste2 = new Usuario("fulano", "1", "Lucas",
				"Campina Grande - centro", "lucas.ufcg@gmail.com");
		Usuario userTeste3 = new Usuario("luucas", "123", "Lucas",
				"Campina Grande - centro", "lucas.ufcg@gmail.com");
		Usuario userTeste4 = new Usuario("luucas", "1", "Cigrano",
				"Campina Grande - centro", "lucas.ufcg@gmail.com");
		Usuario userTeste5 = new Usuario("luucas", "1", "Lucas",
				"Itabaiana - centro", "lucas.ufcg@gmail.com");
		Usuario userTeste6 = new Usuario("luucas", "1", "Lucas",
				"Campina Grande - centro", "lucas.ufcg@gmail.com");
		Usuario userTeste7 = new Usuario("luucas", "1", "Lucas",
				"Campina Grande - centro", "seila@gmail.com");
		Usuario userTeste8 = new Usuario("chaves", "asasasas", "kakaka",
				"Terra do nunca", "google@gmail.com");

		Assert.assertTrue(userTeste.equals(user1));
		Assert.assertFalse(userTeste2.equals(user1));
		Assert.assertTrue(userTeste3.equals(user1));
		Assert.assertTrue(userTeste4.equals(user1));
		Assert.assertTrue(userTeste5.equals(user1));
		Assert.assertTrue(userTeste6.equals(user1));
		Assert.assertTrue(userTeste7.equals(user1));
		Assert.assertFalse(userTeste8.equals(user1));
		Assert.assertFalse(user2.equals(user1));
		Assert.assertFalse(user3.equals(user4));
		Assert.assertTrue(user2.equals(user2));
	}

	@Test
	public void TestaToString() {
		Assert.assertEquals(
				"Login: luucas, nome: Lucas Albuquerque, endereco: Campina Grande - centro, email: lucas.ufcg@gmail.com",
				user1.toString());
	}

}
