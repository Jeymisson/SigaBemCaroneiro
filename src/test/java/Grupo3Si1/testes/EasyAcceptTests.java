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

		files.add("scripts/US01.txt");
		files.add("scripts/US02.txt");
		files.add("scripts/US03.txt");
		files.add("scripts/US04.txt");
		files.add("scripts/US05.txt");
		files.add("scripts/US06.txt");
		files.add("scripts/US07.txt");
		files.add("scripts/US08.txt");

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
