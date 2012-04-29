package Grupo3Si1.testes;

import java.util.*;

import junit.framework.TestCase;

import org.junit.Test;

import Grupo3Si1.controller.SigaBemController;


import easyaccept.EasyAcceptFacade;

public class EasyAcceptTests extends TestCase {

	@Test
	public void testarEasyAcceptScript() {

		List<String> files = new ArrayList<String>();

		files.add("src/test/resources/Grupo3Si1/US01.txt");
		files.add("src/test/resources/Grupo3Si1/US02.txt");
		files.add("src/test/resources/Grupo3Si1/US03.txt");
		files.add("src/test/resources/Grupo3Si1/US04.txt");
		files.add("src/test/resources/Grupo3Si1/US05.txt");
		files.add("src/test/resources/Grupo3Si1/US06.txt");
		files.add("src/test/resources/Grupo3Si1/US07.txt");
		files.add("src/test/resources/Grupo3Si1/US08.txt");

		// Instantiate the Monopoly Game facade
		SigaBemController control = new SigaBemController();


		EasyAcceptFacade eaFacade = new EasyAcceptFacade(control, files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());


		assertTrue(eaFacade.getTotalNumberOfNotPassedTests() == 0);
	}
}
