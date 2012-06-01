package grupo3si.testes;

import java.util.*;

import junit.framework.TestCase;

import org.junit.Test;

import easyaccept.EasyAcceptFacade;
import grupo3si.server.controller.FacedEasy;
import grupo3si.server.controller.SigaBemController;

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
		files.add("scripts/US09.txt");
		files.add("scripts/US10.txt");
		files.add("scripts/US11.txt");
		files.add("scripts/US12.txt");
		// Instantiate the Monopoly Game facade
		FacedEasy control = new FacedEasy(SigaBemController.getInstance());

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(control, files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());

		assertTrue(eaFacade.getTotalNumberOfNotPassedTests() == 0);
	}
}
