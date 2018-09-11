package LG;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	public Logic logic;
	int row,col;
	int[][] test;
	@Before
	public void setUp() throws Exception {
		logic=new Logic();
		row=20;
		col=20;
		test=new int[row+2][col+2];
	}

	@Test
	public void testSetMap() {
		logic.setMap(test, row, col);
		//fail("Not yet implemented");
	}

	@Test
	public void testRandom() {
		logic.random(test, row, col);
	}

	@Test
	public void testRun() {
		logic.run(test, row, col);
		
		//fail("Not yet implemented");
	}

}
